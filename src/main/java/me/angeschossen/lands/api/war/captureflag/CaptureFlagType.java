package me.angeschossen.lands.api.war.captureflag;

/**
 * Capture flags can be placed by players or server admins for arenas.
 */
public enum CaptureFlagType {
    /**
     * Normal capture flags placed by a player during war.
     */
    NORMAL,
    /**
     * Capture flag placed by a server adminstrator at a point of interest.
     */
    KOTH
}
