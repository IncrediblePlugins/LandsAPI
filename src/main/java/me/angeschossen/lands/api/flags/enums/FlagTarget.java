package me.angeschossen.lands.api.flags.enums;

/**
 * Controls which audience can view and toggle a flag.
 * {@link #PLAYER} flags are visible to regular land members, {@link #ADMIN} flags only to admin-land owners,
 * and {@link #SYSTEM} flags are reserved for internal Lands use and hidden from all players.
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
