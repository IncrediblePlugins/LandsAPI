package me.angeschossen.lands.api.limits;

/**
 * Determines how a {@link Limit}'s value is resolved.
 */
public enum LimitMode {
    /** Limit value is determined by the player's permission nodes. */
    PERMISSION,
    /** Limit value is stored in and read from the database. */
    DATABASE
}
