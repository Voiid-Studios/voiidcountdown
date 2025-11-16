package voiidstudios.vct.configs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.configuration.file.FileConfiguration;
import voiidstudios.vct.VoiidCountdownTimer;
import voiidstudios.vct.configs.model.CustomConfig;
import voiidstudios.vct.utils.Formatter;

public class MainConfigManager {
    private CustomConfig configFile;

    private boolean auto_update;
    private boolean update_notification;
    private int ticks_hide_after_ending;
    private String text_format;
    private int refresh_ticks;
    private boolean save_state_timers;

    public MainConfigManager(VoiidCountdownTimer plugin){
        configFile = new CustomConfig("config.yml", plugin, null, false);
        configFile.registerConfig();
        checkConfigsUpdate();
    }

    public void configure(){
        FileConfiguration config = configFile.getConfig();

        auto_update = config.getBoolean("Config.auto_update");
        update_notification = config.getBoolean("Config.update_notification");
        ticks_hide_after_ending = config.getInt("Config.ticks_hide_after_ending");
        text_format = config.getString("Config.text_format");
        refresh_ticks = config.getInt("Config.refresh_ticks");
        save_state_timers = config.getBoolean("Config.save_state_timers");
    }

    public void reloadConfig(){
        configFile.reloadConfig();
        configure();
    }

    public FileConfiguration getConfig(){
        return configFile.getConfig();
    }

    public CustomConfig getConfigFile(){
        return this.configFile;
    }

    public void saveConfig(){
        configFile.saveConfig();
    }

    public void checkConfigsUpdate(){
        Path pathConfig = Paths.get(configFile.getRoute());
        try {
            String text = new String(Files.readAllBytes(pathConfig));
            
            if(!text.contains("auto_update:")){
                getConfig().set("Config.auto_update", false);
                saveConfig();
            }
            if(!text.contains("update_notification:")){
                getConfig().set("Config.update_notification", true);
                saveConfig();
            }
            if(!text.contains("ticks_hide_after_ending:")){
                getConfig().set("Config.ticks_hide_after_ending", 60);
                saveConfig();
            }
            if(!text.contains("text_format:")){
                getConfig().set("Config.text_format", "LEGACY");
                saveConfig();
            }
            if(!text.contains("refresh_ticks:")){
                getConfig().set("Config.refresh_ticks", 10);
                saveConfig();
            }
            if(!text.contains("save_state_timers:")){
                getConfig().set("Config.save_state_timers", true);
                saveConfig();
            }

            if(!text.contains("timerSetError:")){
                getConfig().set("Messages.timerSetError", "&cUse: /vct set <HH:MM:SS>");
                getConfig().set("Messages.timerSetFormatIncorrect", "&cIncorrect format. Please use HH:MM:SS");
                getConfig().set("Messages.timerSetFormatInvalid", "&cThe format does not contain a valid number.");
                getConfig().set("Messages.timerSetFormatOutRange", "&cThe timer must be greater than 0 seconds.");
                saveConfig();
            }
            if(!text.contains("timerModifyInvalid:")){
                getConfig().set("Messages.timerModifyInvalid", "&cUse: /vct modify <modifier>");
                saveConfig();
            }
            if(!text.contains("timerModifyAddError:")){
                getConfig().set("Messages.timerModifyAddError", "&cUse: /vct modify add <HH:MM:SS>");
                getConfig().set("Messages.timerModifyAdd", "&a%HH%:%MM%:%SS% has been added to the timer.");
                saveConfig();
            }
            if(!text.contains("timerModifySetError:")){
                getConfig().set("Messages.timerModifySetError", "&cUse: /vct modify set <HH:MM:SS>");
                getConfig().set("Messages.timerModifySet", "&aThe timer was set to %HH%:%MM%:%SS%.");
                saveConfig();
            }
            if(!text.contains("timerModifyTakeError:")){
                getConfig().set("Messages.timerModifyTakeError", "&cUse: /vct modify take <HH:MM:SS>");
                getConfig().set("Messages.timerModifyTake", "&a%HH%:%MM%:%SS% has been removed from the timer.");
                saveConfig();
            }
            if(!text.contains("timerModifyBarcolorError:")){
                getConfig().set("Messages.timerModifyBarcolorError", "&cUse: /vct modify barcolor <color>. &eYou can use these colors: BLUE, GREEN, PINK, PURPLE, RED, WHITE, or YELLOW.");
                getConfig().set("Messages.timerModifyBarcolorInvalid", "&cThe color \"%COLOR%\" of the timer boss bar is invalid. Use BLUE, GREEN, PINK, PURPLE, RED, WHITE, or YELLOW.");
                getConfig().set("Messages.timerModifyBarcolor", "&aThe color of the timer %TIMER% has been changed to \"%COLOR%\".");
                saveConfig();
            }
            if(!text.contains("timerModifyBarstyleError:")){
                getConfig().set("Messages.timerModifyBarstyleError", "&cUse: /vct modify bossbar_style <style>. &eYou can use these styles: SOLID, SEGMENTED_6, SEGMENTED_10, SEGMENTED_12, or SEGMENTED_20.");
                getConfig().set("Messages.timerModifyBarstyleInvalid", "&cThe style \"%STYLE%\" of the timer boss bar is invalid. Use SOLID, SEGMENTED_6, SEGMENTED_10, SEGMENTED_12, or SEGMENTED_20.");
                getConfig().set("Messages.timerModifyBarstyle", "&aThe style of the timer %TIMER% has been changed to \"%STYLE%\".");
                saveConfig();
            }
            if(!text.contains("timerModifySoundError:")){
                getConfig().set("Messages.timerModifySoundError", "&cUse: /vct modify sound <\"sound in quotes\">");
                getConfig().set("Messages.timerModifySoundRequireQuotes", "&cThe sound needs to be enclosed in quotation marks (\") in order to change it.");
                getConfig().set("Messages.timerModifySound", "&aThe sound of the timer %TIMER% has been changed to \"%SOUND%\" &e(%TYPE%)&a.");
                saveConfig();
            }
            if(!text.contains("timerModifySoundenableError:")){
                getConfig().set("Messages.timerModifySoundenableError", "&cUse: /vct modify soundenable <true|false>");
                getConfig().set("Messages.timerModifySoundenableInvalid", "&cThe boolean is invalid. Use TRUE or FALSE.");
                getConfig().set("Messages.timerModifySoundenable", "&aThe sound enable of the timer %TIMER% has been changed to %SOUNDENABLE%.");
                saveConfig();
            }
            if(!text.contains("timerModifySoundvolumeError:")){
                getConfig().set("Messages.timerModifySoundvolumeError", "&cUse: /vct modify sound_volume <0.1 - 2.0>");
                getConfig().set("Messages.timerModifySoundvolumeInvalid", "&cThe number is invalid. Please use a number between &e0.1 &cand &e2.0.");
                getConfig().set("Messages.timerModifySoundvolumeOutRange", "&cThe volume must be between &e0.1 &cand &e2.0&c.");
                getConfig().set("Messages.timerModifySoundvolume", "&aThe sound_volume of the timer %TIMER% has been changed to &e%VOLUME%&a.");
                saveConfig();
            }
            if(!text.contains("timerModifySoundpitchError:")){
                getConfig().set("Messages.timerModifySoundpitchError", "&cUse: /vct modify sound_pitch <0.1 - 2.0>");
                getConfig().set("Messages.timerModifySoundpitchInvalid", "&cThe number is invalid. Please use a number between &e0.1 &cand &e2.0.");
                getConfig().set("Messages.timerModifySoundpitchOutRange", "&cThe pitch must be between &e0.1 &cand &e2.0&c.");
                getConfig().set("Messages.timerModifySoundpitch", "&aThe sound_pitch of the timer %TIMER% has been changed to &e%PITCH%&a.");
                saveConfig();
            }
            if(!text.contains("timerModifyTextError:")){
                getConfig().set("Messages.timerModifyTextError", "&cUse: /vct modify text <\"text in quotes\">");
                getConfig().set("Messages.timerModifyTextRequireQuotes", "&cThe text needs to be enclosed in quotation marks (\") in order to change it.");
                getConfig().set("Messages.timerModifyText", "&aThe text of the timer %TIMER% has been changed to \"&r%TEXT%&a\".");
                saveConfig();
            }
            if(!text.contains("timerExpansionInvalid:")){
                getConfig().set("Messages.timerExpansionInvalid", "&cUse: /vct expansion <info|enable|disable|reload|reloadall> <expansion_id>");
                getConfig().set("Messages.timerExpansionDisabled", "&cExpansions are not enabled on this server.");
                getConfig().set("Messages.timerExpansionNotSpecified", "&cYou must specify the id of the expansion.");
                getConfig().set("Messages.timerExpansionNotFound", "&cThe &e%EXPANSION% &cexpansion could not be found.");
                saveConfig();
            }
            if(!text.contains("timerExpansionEnableAlreadyEnabled:")){
                getConfig().set("Messages.timerExpansionEnableAlreadyEnabled", "&cThe &e%EXPANSION% &cextension is already enabled.");
                getConfig().set("Messages.timerExpansionEnableError", "&cAn error occurred while attempting to enable the &e%EXPANSION% &cextension. Check the console for more details.");
                getConfig().set("Messages.timerExpansionEnable", "&aThe &e%EXPANSION% &aexpansion has been enabled.");
                saveConfig();
            }
            if(!text.contains("timerExpansionDisableAlreadyDisabled:")){
                getConfig().set("Messages.timerExpansionDisableAlreadyDisabled", "&cThe &e%EXPANSION% &cextension is already disabled.");
                getConfig().set("Messages.timerExpansionDisableError", "&cAn error occurred while attempting to disable the &e%EXPANSION% &cextension. Check the console for more details.");
                getConfig().set("Messages.timerExpansionDisable", "&aThe &e%EXPANSION% &aexpansion has been disabled.");
                saveConfig();
            }
            if(!text.contains("timerExpansionReloadDisabled:")){
                getConfig().set("Messages.timerExpansionReloadDisabled", "&cThe &e%EXPANSION% &cextension is not enabled. Please enable it with /vct expansion enable to reload it.");
                getConfig().set("Messages.timerExpansionReloadError", "&cAn error occurred while attempting to reload the &e%EXPANSION% &cextension. Check the console for more details.");
                getConfig().set("Messages.timerExpansionReload", "&aThe &e%EXPANSION% &aexpansion has been reloaded.");
                saveConfig();
            }
            if(!text.contains("timerExpansionReloadallError:")){
                getConfig().set("Messages.timerExpansionReloadallError", "&cNo expansion was reloaded. Verify that valid expansions exist.");
                getConfig().set("Messages.timerExpansionReloadall", "&a%EXPANSIONS% expansions were reloaded.");
                saveConfig();
            }
            if(!text.contains("timerStart:")){
                getConfig().set("Messages.timerStart", "&aTimer started of %HH%:%MM%:%SS%!");
                saveConfig();
            }
            if(!text.contains("timerPause:")){
                getConfig().set("Messages.timerPause", "&6Timer paused!");
                saveConfig();
            }
            if(!text.contains("timerResume:")){
                getConfig().set("Messages.timerResume", "&6Timer resumed!");
                saveConfig();
            }
            if(!text.contains("timerStop:")){
                getConfig().set("Messages.timerStop", "&6Timer stopped!");
                saveConfig();
            }
            if(!text.contains("timerDontExists:")){
                getConfig().set("Messages.timerDontExists", "&cThe timer does not exist.");
                saveConfig();
            }
            if(!text.contains("timerConfigNotFound:")){
                getConfig().set("Messages.timerConfigNotFound", "&cThe timer configuration could not be found.");
                saveConfig();
            }

            if (getConfig().contains("Timers")) {
                for (String timerKey : getConfig().getConfigurationSection("Timers").getKeys(false)) {
                    String base = "Timers." + timerKey + ".";

                    if (!getConfig().contains(base + "text")) {
                        getConfig().set(base + "text", "%HH%:%MM%:%SS%");
                    }
                    if (!getConfig().contains(base + "sound")) {
                        getConfig().set(base + "sound", "UI_BUTTON_CLICK");
                    }
                    if (!getConfig().contains(base + "sound_volume")) {
                        getConfig().set(base + "sound_volume", 1.0);
                    }
                    if (!getConfig().contains(base + "sound_pitch")) {
                        getConfig().set(base + "sound_pitch", 1.0);
                    }
                    if (!getConfig().contains(base + "bossbar_color")) {
                        getConfig().set(base + "bossbar_color", "WHITE");
                    }
                    if (!getConfig().contains(base + "bossbar_style")) {
                        getConfig().set(base + "bossbar_style", "SOLID");
                    }
                    if (!getConfig().contains(base + "enabled")) {
                        getConfig().set(base + "enabled", true);
                    }
                    if (!getConfig().contains(base + "sound_enabled")) {
                        getConfig().set(base + "sound_enabled", false);
                    }
                }
                saveConfig();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isAuto_update() {
        return auto_update;
    }

    public boolean isUpdate_notification() {
        return update_notification;
    }

    public boolean isSave_state_timers() {
        return save_state_timers;
    }

    public int getTicks_hide_after_ending() {
        return ticks_hide_after_ending;
    }

    public Formatter getFormatter() {
        try {
            return Formatter.valueOf(text_format.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return Formatter.LEGACY;
        }
    }

    public int getRefresh_ticks() {
        return refresh_ticks;
    }
}
