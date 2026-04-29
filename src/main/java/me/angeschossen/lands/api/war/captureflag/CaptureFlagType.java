package me.angeschossen.lands.api.war.captureflag;

/**
 * Describes how a capture flag was placed during a war.
 * {@link #NORMAL} flags are planted by players mid-battle; {@link #KOTH} flags are set up by server administrators at fixed arena locations.
 */
public enum CaptureFlagType {
    /**
     * Normal capture flags placed by a player during war.
     */
    NORMAL,
    /**
     * Capture flag placed by a server administrator at a predefined point of interest (KoTH arena).
     */
    KOTH
}
