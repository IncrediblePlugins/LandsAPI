package me.angeschossen.lands.api.land.enums;

/**
 * Used to evaluate which land to use for commands etc.
 * This is needed if a player is part of multiple lands.
 */
public enum LandGetMode {
    /**
     * The land at the player's position is used for the command execution.
     */
    POSITION,
    /**
     * The "/lands edit" land is used.
     */
    EDIT,
    /**
     * Depending on the server's config, either {@link #POSITION} or {@link #EDIT} is used.
     */
    CONFIG
}
