package voiidstudios.vct.expansions;


import voiidstudios.vct.VoiidCountdownTimer;
import voiidstudios.vct.expansions.command.ExpansionCommandRegistry;
import voiidstudios.vct.expansions.exceptions.InvalidExpansionException;
import voiidstudios.vct.managers.MessagesManager;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;

public class ExpansionManager {
    private final VoiidCountdownTimer plugin;
    private final ExpansionCommandRegistry commandRegistry = new ExpansionCommandRegistry();
    private final Map<String, ScriptExpansion> loadedExpansions = new LinkedHashMap<>();
    private final Map<String, ExpansionHandle> discoveredExpansions = new LinkedHashMap<>();
    private final File expansionsDirectory;

    private static final String METADATA_FILE = "expansion.yml";
    private static final String[] BUNDLED_EXPANSIONS = new String[] {"stopwatch"};

    public ExpansionManager(VoiidCountdownTimer plugin) {
        this.plugin = plugin;
        this.expansionsDirectory = new File(plugin.getDataFolder(), "expansions");
    }

    public void loadExpansions() {
        if (!plugin.getDataFolder().exists() && !plugin.getDataFolder().mkdirs()) {
            plugin.getLogger().warning("Unable to create the plugin data folder. Expansions will be disabled.");
            return;
        }

        if (!expansionsDirectory.exists() && !expansionsDirectory.mkdirs()) {
            plugin.getLogger().warning("Unable to create the expansions directory. Expansions will be disabled.");
            return;
        }

        exportBundledExamples();

        refreshDiscoveredExpansions();

        if (discoveredExpansions.isEmpty()) {
            plugin.getLogger().info("No expansions were found to load.");
            return;
        }

        for (ExpansionHandle handle : discoveredExpansions.values()) {
            loadExpansion(handle);
        }
    }

    private void loadExpansion(ExpansionHandle handle) {
        if (handle == null) {
            return;
        }

        if (loadedExpansions.containsKey(handle.key)) {
            return;
        }

        try {
            ScriptExpansion expansion = new ScriptExpansion(plugin, handle.metadata, handle.directory, commandRegistry);
            if (expansion.load()) {
                loadedExpansions.put(handle.key, expansion);
                Bukkit.getConsoleSender().sendMessage(
                    MessagesManager.getColoredMessage(
                        VoiidCountdownTimer.prefix +
                        String.format(Locale.ROOT, "&aLoaded expansion %s &ev%s", handle.metadata.getName(), handle.metadata.getVersion())
                    )
                );
            }
        } catch (InvalidExpansionException exception) {
            plugin.getLogger().log(
                Level.WARNING,
                String.format(Locale.ROOT, "Failed to initialize expansion %s", handle.metadata.getName()),
                exception
            );

            Bukkit.getConsoleSender().sendMessage(
                MessagesManager.getColoredMessage(
                    VoiidCountdownTimer.prefix +
                    String.format(Locale.ROOT, "&cUnable to load expansion %s", handle.metadata.getName())
                )
            );
        }
    }

    private void exportBundledExamples() {
        for (String expansionId : BUNDLED_EXPANSIONS) {
            File targetDirectory = new File(expansionsDirectory, expansionId);
            if (!targetDirectory.exists() && !targetDirectory.mkdirs()) {
                plugin.getLogger().warning(String.format(Locale.ROOT, "Unable to create folder for example expansion %s", expansionId));
                continue;
            }

            copyResourceIfAbsent("expansions/" + expansionId + "/" + METADATA_FILE, new File(targetDirectory, METADATA_FILE));
            copyResourceIfAbsent("expansions/" + expansionId + "/main.js", new File(targetDirectory, "main.js"));
        }
    }

    private void copyResourceIfAbsent(String resourcePath, File destination) {
        if (destination.exists()) {
            return;
        }

        try (InputStream inputStream = plugin.getResource(resourcePath)) {
            if (inputStream == null) {
                return;
            }

            try (FileOutputStream outputStream = new FileOutputStream(destination)) {
                byte[] buffer = new byte[4096];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
            }
        } catch (IOException exception) {
            plugin.getLogger().log(Level.WARNING, String.format(Locale.ROOT, "Unable to export resource %s", resourcePath), exception);
        }
    }

    public void shutdown() {
        for (ScriptExpansion expansion : new ArrayList<>(loadedExpansions.values())) {
            try {
                expansion.disable();
            } catch (Exception exception) {
                plugin.getLogger().log(
                    Level.WARNING,
                    String.format(Locale.ROOT, "Error while disabling expansion %s", expansion.getMetadata().getName()),
                    exception
                );

                Bukkit.getConsoleSender().sendMessage(
                    MessagesManager.getColoredMessage(
                        VoiidCountdownTimer.prefix +
                        String.format(Locale.ROOT, "&cUnable to disable expansion %s", expansion.getMetadata().getName())
                    )
                );
            }
        }

        loadedExpansions.clear();
        discoveredExpansions.clear();
    }

    public ExpansionCommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public List<String> getHelpLines() {
        return commandRegistry.getHelpLines();
    }

    public List<String> getRootSuggestions(String partial) {
        return commandRegistry.getRootSuggestions(partial);
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        List<String> result = commandRegistry.tabComplete(sender, args);
        return result == null ? null : new ArrayList<>(result);
    }

    public boolean executeCommand(CommandSender sender, String[] args) {
        return commandRegistry.execute(sender, args);
    }

    public Map<String, ScriptExpansion> getLoadedExpansions() {
        return Collections.unmodifiableMap(loadedExpansions);
    }

    public boolean enableExpansion(String name) {
        refreshDiscoveredExpansions();
        ExpansionHandle handle = discoveredExpansions.get(normalizeName(name));
        if (handle == null) {
            return false;
        }

        if (loadedExpansions.containsKey(handle.key)) {
            return false;
        }

        loadExpansion(handle);
        return loadedExpansions.containsKey(handle.key);
    }

    public boolean disableExpansion(String name) {
        String key = normalizeName(name);
        ScriptExpansion expansion = loadedExpansions.get(key);
        if (expansion == null) {
            return false;
        }

        boolean success = true;
        try {
            expansion.disable();
        } catch (Exception exception) {
            plugin.getLogger().log(
                Level.WARNING, 
                String.format(Locale.ROOT, "Error while disabling expansion %s", expansion.getMetadata().getName()),
                exception
            );

            Bukkit.getConsoleSender().sendMessage(
                MessagesManager.getColoredMessage(
                    VoiidCountdownTimer.prefix +
                    String.format(Locale.ROOT, "&cUnable to disable expansion %s", expansion.getMetadata().getName())
               )
            );

            success = false;
        }

        if (success) {
            loadedExpansions.remove(key);
        }

        return success;
    }

    public boolean reloadExpansion(String name) {
        String key = normalizeName(name);
        boolean wasLoaded = loadedExpansions.containsKey(key);
        if (wasLoaded && !disableExpansion(name)) {
            return false;
        }

        refreshDiscoveredExpansions();
        ExpansionHandle handle = discoveredExpansions.get(key);
        if (handle == null) {
            return false;
        }

        loadExpansion(handle);
        return loadedExpansions.containsKey(key);
    }

    public int reloadAllExpansions() {
        List<String> toDisable = new ArrayList<>(loadedExpansions.keySet());
        for (String key : toDisable) {
            disableExpansion(key);
        }

        refreshDiscoveredExpansions();

        int loadedCount = 0;
        for (ExpansionHandle handle : discoveredExpansions.values()) {
            int before = loadedExpansions.size();
            loadExpansion(handle);
            if (loadedExpansions.size() > before) {
                loadedCount++;
            }
        }

        return loadedCount;
    }

    public ExpansionMetadata getExpansionMetadata(String name) {
        String key = normalizeName(name);
        refreshDiscoveredExpansions();

        ExpansionHandle handle = discoveredExpansions.get(key);
        if (handle != null) {
            return handle.metadata;
        }

        ScriptExpansion expansion = loadedExpansions.get(key);
        return expansion == null ? null : expansion.getMetadata();
    }

    public boolean isExpansionLoaded(String name) {
        return loadedExpansions.containsKey(normalizeName(name));
    }

    public List<String> getKnownExpansionNames() {
        refreshDiscoveredExpansions();

        java.util.LinkedHashSet<String> names = new java.util.LinkedHashSet<>();
        for (ExpansionHandle handle : discoveredExpansions.values()) {
            names.add(handle.metadata.getName());
        }

        for (ScriptExpansion expansion : loadedExpansions.values()) {
            names.add(expansion.getMetadata().getName());
        }

        return new ArrayList<>(names);
    }

    private void refreshDiscoveredExpansions() {
        discoveredExpansions.clear();

        File[] children = expansionsDirectory.listFiles(File::isDirectory);
        if (children == null) {
            return;
        }

        for (File child : children) {
            File metadataFile = new File(child, METADATA_FILE);
            if (!metadataFile.exists()) {
                Bukkit.getConsoleSender().sendMessage(
                    MessagesManager.getColoredMessage(
                        VoiidCountdownTimer.prefix +
                        String.format(Locale.ROOT, "&3Skipping expansion in %s because %s is missing", child.getName(), METADATA_FILE)
                    )
                );

                continue;
            }

            ExpansionMetadata metadata;
            try {
                metadata = ExpansionMetadata.fromFile(metadataFile);
            } catch (InvalidExpansionException exception) {
                plugin.getLogger().log(
                    Level.WARNING,
                    String.format(Locale.ROOT, "Invalid expansion metadata in %s", child.getName()),
                    exception
                );

                Bukkit.getConsoleSender().sendMessage(
                    MessagesManager.getColoredMessage(
                        VoiidCountdownTimer.prefix +
                        String.format(Locale.ROOT, "&cInvalid expansion metadata in %s", child.getName(), METADATA_FILE)
                    )
                );

                continue;
            }

            String key = metadata.getName().toLowerCase(Locale.ROOT);
            if (discoveredExpansions.containsKey(key)) {
                plugin.getLogger().warning(String.format(Locale.ROOT, "Multiple expansions share the name %s. Skipping directory %s.", metadata.getName(), child.getName()));
                continue;
            }

            discoveredExpansions.put(key, new ExpansionHandle(key, child, metadata));
        }
    }

    private static String normalizeName(String name) {
        return name == null ? "" : name.toLowerCase(Locale.ROOT);
    }

    private static final class ExpansionHandle {
        private final String key;
        private final File directory;
        private final ExpansionMetadata metadata;

        private ExpansionHandle(String key, File directory, ExpansionMetadata metadata) {
            this.key = key;
            this.directory = directory;
            this.metadata = metadata;
        }
    }
}