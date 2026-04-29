package me.angeschossen.lands.api.flags.enums;

/**
 * Classifies a role flag by its purpose within a land.
 * {@link #ACTION} covers physical in-world interactions (e.g. breaking blocks),
 * while {@link #MANAGEMENT} covers administrative operations (e.g. trusting players).
 */
public enum RoleFlagCategory {
    /**
     * Physical actions, such as block breaking etc.
     */
    ACTION,
    /**
     * Management operations such as trusting a player etc.
     */
    MANAGEMENT
}
