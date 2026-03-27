package me.angeschossen.lands.api.war.enums;

/**
 * Represents the current phase of a war or war declaration.
 */
public enum WarStatus {
    /**
     * The war is in the preparation phase and hasn't started yet. This is usually the case after the declaration has been sent.
     */
    PREPARATION,
    /**
     * The war is in the active phase.
     */
    FIGHT
}
