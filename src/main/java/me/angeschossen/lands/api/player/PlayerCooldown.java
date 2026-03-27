package me.angeschossen.lands.api.player;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import org.jetbrains.annotations.NotNull;

/**
 * Stores information about cooldowns.
 */
public enum PlayerCooldown {

    /**
     * Cooldown for /lands rename.
     */
    RENAME_LAND("land.name.rename.cooldown_rename"),
    /**
     * Cooldown for /nations rename.
     */
    RENAME_NATION("nation.name.rename.cooldown_rename", ConfigType.NATIONS),
    /**
     * Cooldown for land spawn teleportation.
     */
    SPAWN("land.spawn.teleport.spawn-cooldown"),
    /**
     * Cooldown for /lands wild (random teleportation).
     */
    WILD("random-teleport.rtp-cooldown"),
    /**
     * Cooldown for /lands unstuck.
     */
    UNSTUCK("unstuck.unstuck-cooldown"),
    /**
     * Cooldown for chunk teleportation via /lands claimlist.
     */
    CHUNK("chunk.teleport.chunk-tp-cooldown"),
    /**
     * Cooldown for upcoming taxes reminder.
     */
    TAXES_REMINDER("taxes.taxes-announcement"),
    /**
     * Cooldown for teleportation to areas in /lands rent list.
     */
    RENTLIST("land.rent.teleport.rent-tp-cooldown"),
    /**
     * Cooldown for upcoming upkeep reminder.
     */
    UPKEEP_REMINDER("upkeep.upkeep-reminder"),
    /**
     * Cooldown for sub area teleportation.
     */
    TELEPORT_AREA("sub-area.teleport-cooldown");

    /** The config key used to look up the cooldown duration. */
    public final String optionKey;
    /** The config file that contains the cooldown setting. */
    public final ConfigType type;
    private long time;

    PlayerCooldown(@NotNull String optionKey) {
        this(optionKey, ConfigType.LANDS);
    }

    PlayerCooldown(@NotNull String optionKey, @NotNull ConfigType type) {
        Checks.requireNonNull(optionKey, "optionKey");
        Checks.requireNonNull(type, "configType");

        this.optionKey = optionKey;
        this.type = type;
    }

    /**
     * Get the type of config.
     *
     * @return never null
     */
    @NotNull
    public ConfigType getConfigType() {
        return type;
    }

    /**
     * Get the cooldown.
     *
     * @return cooldown time in seconds
     */
    public long getTime() {
        return time;
    }

    /**
     * Set the cooldown time.
     *
     * @param time if 0, disables this cooldown
     */
    public void setTime(long time) {
        this.time = Math.max(time, 0); // all lower than 1 disables it
    }

    /**
     * Identifies which configuration file contains the cooldown setting.
     */
    public enum ConfigType {
        /**
         * config.yml
         */
        LANDS,
        /**
         * wars.yml
         */
        WARS,
        /**
         * nations.yml
         */
        NATIONS
    }

    /**
     * Get the bypass permission of this cooldown. Players that have this permission will ignore this cooldown.
     *
     * @return never null
     */
    @NotNull
    public String getBypass() {
        return getConfigType().toString() + ".bypass.cooldown." + toString();
    }
}
