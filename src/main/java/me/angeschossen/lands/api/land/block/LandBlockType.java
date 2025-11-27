package me.angeschossen.lands.api.land.block;

public enum LandBlockType {
    /**
     * The mainblock, which opens the land menu and land storage upon interaction.
     */
    MAIN,
    /**
     * The mainblock for camps.
     */
    MAIN_CAMP,
    /**
     * Capture flag during war.
     */
    CAPTURE_FLAG,
    /**
     * Capture flag for the KoTH gamemode.
     */
    CAPTURE_FLAG_KOTH,
    /**
     * Rental sign or rental hologram
     */
    RENTAL
}
