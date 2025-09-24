package voiidstudios.vct.api;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import voiidstudios.vct.VoiidCountdownTimer;
import voiidstudios.vct.managers.TimerManager;
import voiidstudios.vct.utils.Formatter;
import voiidstudios.vct.configs.model.TimerConfig;

public class Timer implements Runnable {
    private int seconds;
    private final BossBar bossbar;
    private BukkitTask task;
    private boolean hasSound;
    private String soundFinalName;
    public float soundVolume;
    public float soundPitch;
    private String timerText;
    private int initialSeconds;
    private int refreshInterval;
    private final int maxValue = 359999;
    private final int minValue = 0;
    private final String timerId;

    public Timer(int seconds, String timeText, String timeSound, BarColor barcolor, BarStyle barstyle, String timerId, boolean hasSoundd, float soundVolumee, float soundPitchh) {
        this.seconds = seconds;
        this.initialSeconds = seconds;
        this.timerId = timerId;

        this.refreshInterval = VoiidCountdownTimer.getConfigsManager().getMainConfigManager().getRefresh_ticks();
        this.hasSound = hasSoundd;

        this.timerText = timeText;
        this.soundFinalName = timeSound;
        this.soundVolume = soundVolumee;
        this.soundPitch = soundPitchh;

        this.bossbar = Bukkit.createBossBar("", barcolor, barstyle, new org.bukkit.boss.BarFlag[0]);
    }

    public int getInitialSeconds() {
        return this.initialSeconds;
    }

    public int getRemainingSeconds() {
        return this.seconds;
    }

    public String getTimertext() {
        return this.timerText;
    }

    public String getTimertextFormated() {
        return this.timerText
                .replace("%HH%", formatTimeHH(this.seconds))
                .replace("%MM%", formatTimeMM(this.seconds))
                .replace("%SS%", formatTimeSS(this.seconds));
    }

    public static void playSound(Player player, String actionLine) {
        // playsound: sound;volume;pitch
        String[] sep = actionLine.split(";");
        String soundName = sep[0];
        float volume = Float.parseFloat(sep[1]);
        float pitch = Float.parseFloat(sep[2]);

        boolean success = false;

        try {
            Sound sound = Sound.valueOf(soundName.toUpperCase());
            player.playSound(player.getLocation(), sound, volume, pitch);
            success = true;
        } catch (IllegalArgumentException ignored) {
            try {
                player.playSound(player.getLocation(), soundName, volume, pitch);
                success = true;
            } catch (Exception e) { /* ignore */ }
        }

        if (!success) {
            Bukkit.getLogger().warning("[TuPlugin] No se pudo reproducir el sonido: " + soundName);
        }
    }

    private void updateBossBarTitle(String phasesText) {
        Formatter formatter = VoiidCountdownTimer.getConfigsManager().getMainConfigManager().getFormatter();
        Object formatted = formatter.format(
                VoiidCountdownTimer.getInstance(),
                Bukkit.getConsoleSender(),
                phasesText
        );

        try {
            Class<?> componentClass = null;
            try {
                componentClass = Class.forName("net.kyori.adventure.text.Component");
            } catch (ClassNotFoundException ignored) {
                componentClass = null;
            }

            if (componentClass != null && componentClass.isInstance(formatted)) {
                try {
                    this.bossbar.getClass().getMethod("setTitle", componentClass).invoke(this.bossbar, formatted);
                    return;
                } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException e) {}

                try {
                    Class<?> legacyCls = Class.forName("net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer");

                    Object serializer;
                    try {
                        serializer = legacyCls.getMethod("legacySection").invoke(null);
                    } catch (NoSuchMethodException nsme) {
                        Class<?> builderPublicClass = Class.forName("net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer$Builder");
                        Object builder = legacyCls.getMethod("builder").invoke(null);
                        builderPublicClass.getMethod("character", char.class).invoke(builder, '&');
                        try { builderPublicClass.getMethod("hexColors").invoke(builder); } catch (NoSuchMethodException ignored) {}
                        serializer = builderPublicClass.getMethod("build").invoke(builder);
                    }

                    String legacyTitle = (String) legacyCls.getMethod("serialize", componentClass).invoke(serializer, formatted);
                    this.bossbar.setTitle(legacyTitle);
                    return;
                } catch (Throwable t) {}
            }
        } catch (Throwable t) {}

        if (formatted instanceof String) {
            this.bossbar.setTitle(((String) formatted).replace('&', '§'));
        } else {
            this.bossbar.setTitle(phasesText.replace('&', '§'));
        }
    }

    private void startTask(int seconds) {
        final int increment = -1;
        this.task = Bukkit.getScheduler().runTaskTimer(
                VoiidCountdownTimer.getInstance(),
                new Runnable() {
                    private int tickCounter = 0;
                    private int refreshCounter = 0;

                    public void run() {
                        tickCounter++;
                        refreshCounter++;

                        if (tickCounter >= 20) {
                            tickCounter = 0;
                            Timer.this.seconds += increment;

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                Timer.this.bossbar.addPlayer(player);

                                if (Timer.this.hasSound && Timer.this.soundFinalName != null) {
                                    playSound(player, soundFinalName + ";" + soundVolume + ";" + soundPitch);
                                }
                            }

                            Bukkit.getPluginManager().callEvent(new VCTEvent(Timer.this, VCTEvent.VCTEventType.CHANGE, null));
                        }

                        if (refreshCounter >= Timer.this.refreshInterval) {
                            refreshCounter = 0;

                            String rawText = Timer.this.timerText
                                    .replace("%HH%", Timer.this.formatTimeHH(Timer.this.seconds))
                                    .replace("%MM%", Timer.this.formatTimeMM(Timer.this.seconds))
                                    .replace("%SS%", Timer.this.formatTimeSS(Timer.this.seconds));

                            String phasesText = VoiidCountdownTimer.getPhasesManager().formatPhases(rawText);

                            updateBossBarTitle(phasesText);

                            double progress = (double) Timer.this.seconds / (double) Timer.this.initialSeconds;
                            progress = Math.max(0.0, Math.min(1.0, progress));
                            Timer.this.bossbar.setProgress(progress);
                        }

                        if (Timer.this.seconds <= 0) {
                            if (Timer.this.task != null)
                                Timer.this.task.cancel();

                                Bukkit.getPluginManager().callEvent(new VCTEvent(Timer.this, VCTEvent.VCTEventType.FINISH, null));
                            
                                Bukkit.getScheduler().runTaskLater(VoiidCountdownTimer.getInstance(), () -> {
                                    if (Timer.this.bossbar != null)
                                        Timer.this.bossbar.removeAll();
                                }, VoiidCountdownTimer.getConfigsManager().getMainConfigManager().getTicks_hide_after_ending());
                        }
                    }
                },
                1L, 1L
        );
    }

    public static void refreshTimerText() {
        Timer current = TimerManager.getInstance().getTimer();
        if (current == null) return;

        current.refreshInterval = VoiidCountdownTimer.getConfigsManager().getMainConfigManager().getRefresh_ticks();

        if (current.timerId != null) {
            try {
                TimerConfig cfg = VoiidCountdownTimer.getConfigsManager().getTimerConfig(current.timerId);
                if (cfg != null && cfg.isEnabled()) {
                    current.timerText = cfg.getText();
                    current.hasSound = cfg.isSoundEnabled();
                    current.soundVolume = cfg.getSoundVolume();
                    current.soundPitch = cfg.getSoundPitch();
                    try { current.soundFinalName = cfg.getSound(); } catch (Exception ignored) { /* keep existing */ }
                    try { current.bossbar.setColor(cfg.getColor()); } catch (Exception ignored) {}
                    return;
                }
            } catch (Throwable t) {}
        }

        current.timerText = "%HH%:%MM%:%SS%";
        current.soundFinalName = "UI_BUTTON_CLICK";
        current.hasSound = false;
        current.soundVolume = 1.0f;
        current.soundPitch = 1.0f;

        try {
            BarColor color = BarColor.WHITE;
            current.bossbar.setColor(color);
        } catch (Exception ignored) {}
    }

    public void setBossBarColor(BarColor color) {
        this.bossbar.setColor(color);
    }

    public void setBossBarStyle(BarStyle style) {
        this.bossbar.setStyle(style);
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    private String formatTime(long time) {
        long hours = time / 3600L;
        long minutes = time % 3600L / 60L;
        long seconds = time % 60L;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private String formatTimeHH(long time) {
        long hours = time / 3600L;
        return String.format("%02d", hours);
    }

    private String formatTimeMM(long time) {
        long minutes = time % 3600L / 60L;
        return String.format("%02d", minutes);
    }

    private String formatTimeSS(long time) {
        long seconds = time % 60L;
        return String.format("%02d", seconds);
    }

    public String getTimerId() {
        return timerId;
    }

    public String getInitialTime() {
        return formatTime(this.initialSeconds);
    }

    public String getTimeLeft() {
        return formatTime(this.seconds);
    }

    public String getTimeLeftHH() {
        return formatTimeHH(this.seconds);
    }

    public String getTimeLeftMM() {
        return formatTimeMM(this.seconds);
    }

    public String getTimeLeftSS() {
        return formatTimeSS(this.seconds);
    }

    public boolean isActive() {
        return task != null;
    }

    public boolean isPaused() {
        return this.task == null && this.seconds > 0;
    }

    public void start() {
        startTask(this.seconds);
    }

    public void stop() {
        if (this.task != null){
            Bukkit.getPluginManager().callEvent(new VCTEvent(Timer.this, VCTEvent.VCTEventType.STOP, null));
            this.task.cancel();
        }

        if (this.bossbar != null)
            this.bossbar.removeAll();
    }

    public void add(int addSeconds) {
        if (this.seconds + addSeconds > this.maxValue) {
            this.seconds = this.maxValue;
            this.initialSeconds = this.maxValue;
        } else {
            this.seconds += addSeconds;
            this.initialSeconds += addSeconds;
        }
    }

    public void set(int setSeconds) {
        if (setSeconds > this.maxValue) {
            this.seconds = this.maxValue;
            this.initialSeconds = this.maxValue;
        } else if (setSeconds < this.minValue) {
            this.seconds = this.minValue;
            this.initialSeconds = this.minValue;
        } else {
            this.seconds = setSeconds;
            this.initialSeconds = setSeconds;
        }
    }

    public void take(int takeSeconds) {
        if (this.seconds - takeSeconds >= this.minValue) {
            this.seconds -= takeSeconds;
            this.initialSeconds -= takeSeconds;
        }
    }

    public void pause() {
        if (this.task != null) {
            this.task.cancel();
            this.task = null;
        }
    }

    public void resume() {
        if (this.task == null)
            startTask(this.seconds);
    }

    public void run() {
        this.seconds--;
        for (Player player : Bukkit.getOnlinePlayers())
            this.bossbar.addPlayer(player);
        if (this.seconds == 0) {
            if (this.task != null)
                Bukkit.getScheduler().cancelTask(this.task.getTaskId());
            this.bossbar.removeAll();
            TimerManager.getInstance().removeTimer();
        }
    }
}