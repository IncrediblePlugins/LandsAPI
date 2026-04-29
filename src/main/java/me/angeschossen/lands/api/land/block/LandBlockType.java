package me.angeschossen.lands.api.land.block;

/**
 * Defines the types of special blocks that can be placed inside a land.
 */
public enum LandBlockType {
    /**
     * The main block, which opens the land menu and land storage upon interaction.
     */
    MAIN,
    /**
     * The main block for a temporary camp land.
     */
    MAIN_CAMP,
    /**
     * A capture flag placed by a player during a war.
     */
    CAPTURE_FLAG,
    /**
     * A capture flag placed at a fixed KoTH arena location.
     */
    CAPTURE_FLAG_KOTH,
    /**
     * A rental sign or hologram advertising an area for rent or sale.
     */
    RENTAL
}
