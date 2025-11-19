package voiidstudios.vct.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import voiidstudios.vct.VoiidCountdownTimer;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TranslationManager {

    private final VoiidCountdownTimer plugin;

    private FileConfiguration langBase;
    private FileConfiguration langSelected;
    private FileConfiguration langCustom;

    private String currentLang = "en_US";

    public TranslationManager(VoiidCountdownTimer plugin) {
        this.plugin = plugin;
    }

    public void loadLanguage(String langCode) {
        this.currentLang = langCode;

        langBase     = loadYaml("core/messages/origins/en_US.yml");
        langSelected = loadYaml("core/messages/origins/" + langCode + ".yml");
        langCustom   = loadYaml("core/messages/custom/custom.yml");

        syncMissingKeys();
    }

    private FileConfiguration loadYaml(String path) {
        File file = new File(plugin.getDataFolder(), path);
        if (!file.exists()) {
            return new YamlConfiguration();
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public void syncMissingKeys() {
        if (langBase == null || langSelected == null) return;

        File selectedFile = new File(plugin.getDataFolder(),
                "core/messages/origins/" + currentLang + ".yml");

        boolean changed = false;

        for (String key : langBase.getKeys(true)) {
            if (!langSelected.contains(key)) {
                langSelected.set(key, langBase.get(key));
                changed = true;

                plugin.getLogger().warning(
                        "Added missing translation key to " + currentLang + ": " + key
                );
            }
        }

        if (changed) {
            try {
                langSelected.save(selectedFile);
            } catch (IOException e) {
                plugin.getLogger().severe("Failed to save language file: " + selectedFile.getName());
                e.printStackTrace();
            }
        }
    }

    public String get(String key) {
        if (langCustom != null && langCustom.contains(key)) {
            return langCustom.getString(key);
        }
        if (langSelected != null && langSelected.contains(key)) {
            return langSelected.getString(key);
        }
        if (langBase != null && langBase.contains(key)) {
            return langBase.getString(key);
        }
        return null;
    }

    public List<String> getStringList(String key) {
        if (langCustom != null && langCustom.isList(key)) {
            return langCustom.getStringList(key);
        }

        if (langSelected != null && langSelected.isList(key)) {
            return langSelected.getStringList(key);
        }

        if (langBase != null && langBase.isList(key)) {
            return langBase.getStringList(key);
        }

        String raw = get(key);
        if (raw == null) {
            return Collections.emptyList();
        }

        if (!raw.contains("\n")) {
            return Collections.singletonList(raw);
        }

        return Arrays.asList(raw.split("\n"));
    }

    public String formatKey(String key, Map<String, String> placeholders) {
        String raw = get(key);
        if (raw == null) return null;
        return formatRaw(raw, placeholders);
    }

    public String formatRaw(String raw, Map<String, String> placeholders) {
        if (raw == null) return null;
        if (placeholders != null) {
            for (Map.Entry<String, String> e : placeholders.entrySet()) {
                raw = raw.replace(e.getKey(), e.getValue());
            }
        }
        return raw;
    }

    public void ensureExists(String key, String defaultValue) {
        boolean changed = false;

        if (langBase != null && !langBase.contains(key)) {
            langBase.set(key, defaultValue);
            changed = true;
        }

        if (langSelected != null && !langSelected.contains(key)) {
            langSelected.set(key, defaultValue);
            changed = true;
        }

        if (changed && langSelected != null) {
            try {
                langSelected.save(new File(plugin.getDataFolder(),
                        "core/messages/origins/" + currentLang + ".yml"));
            } catch (IOException ignored) {}
        }
    }
}