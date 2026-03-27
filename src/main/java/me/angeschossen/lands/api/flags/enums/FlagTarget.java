package me.angeschossen.lands.api.flags.enums;

/**
 * Some flags are not accessible for player or even admins.
 */
public enum FlagTarget {
    /**
     * Accessible for all players.
     */
    PLAYER,
    /**
     * Only for admin lands.
     */
    ADMIN,
    /**
     * Only used by Lands itself. Not accessible for players of admin lands.
     */
    SYSTEM
}
