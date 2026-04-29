package me.angeschossen.lands.api.flags.enums;

/**
 * Identifies the plugin module to which a flag belongs.
 * Used to categorise flags by their domain (land management, nations, or per-player settings).
 */
public enum FlagModule {
    /**
     * Players will use these flags for roles.
     */
    LAND,
    /**
     * This flag is related to nations.
     */
    NATION,
    /**
     * This flag only applies to a player's personal settings.
     */
    PLAYER
}
