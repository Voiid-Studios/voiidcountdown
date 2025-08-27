package voiidstudios.vct.configs.model;

import org.bukkit.boss.BarColor;

public class TimerConfig {
    private String id;
    private String text;
    private String sound;
    private float soundVolume;
    private float soundPitch;
    private BarColor color;
    private boolean enabled;
    private boolean soundEnabled;

    public TimerConfig(String id, String text, String sound, BarColor color, boolean enabled, boolean soundEnabled, float soundVolume, float soundPitch) {
        this.id = id;
        this.text = text;
        this.sound = sound;
        this.soundVolume = soundVolume;
        this.soundPitch = soundPitch;
        this.color = color;
        this.enabled = enabled;
        this.soundEnabled = soundEnabled;
    }

    public String getId() { return this.id; }
    public String getText() { return this.text; }
    public String getSound() { return this.sound; }
    public float getSoundVolume() { return this.soundVolume; }
    public float getSoundPitch() { return this.soundPitch; }
    public BarColor getColor() { return this.color; }
    public boolean isEnabled() { return this.enabled; }
    public boolean isSoundEnabled() { return this.soundEnabled; }
    public void setText(String text) { this.text = text; }
    public void setSound(String sound) { this.sound = sound; }
    public void setSoundVolume(float volume) { this.soundVolume = volume; }
    public void setSoundPitch(float pitch) { this.soundPitch = pitch; }
    public void setColor(BarColor color) { this.color = color; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public void setSoundEnabled(boolean enabled) { this.soundEnabled = enabled; }
}