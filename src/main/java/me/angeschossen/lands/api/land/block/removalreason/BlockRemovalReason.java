package me.angeschossen.lands.api.land.block.removalreason;

/**
 * Reasons for landblock removal.
 */
public enum BlockRemovalReason implements LandBlockRemovalReason {
    /**
     * The landblock doesn't exist any longer in the world.
     */
    BLOCK_INVALID,
    /**
     * The land got unclaimed in which the landblock stood.
     */
    LAND_UNCLAIM,
    /**
     * The landblock got broken by a player.
     */
    BLOCK_BREAK
}
