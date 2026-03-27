package me.angeschossen.lands.api.blockworks;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a block-level coordinate in a world.
 */
public interface BlockCoordinate {

    /**
     * Get the world this coordinate is located in.
     *
     * @return the world; never null
     */
    @NotNull World getWorld();

    /**
     * Get the block X coordinate.
     *
     * @return block X
     */
    int getX();

    /**
     * Get the block Y coordinate.
     *
     * @return block Y
     */
    int getY();

    /**
     * Get the block Z coordinate.
     *
     * @return block Z
     */
    int getZ();

    /**
     * Get the chunk X coordinate that contains this block.
     *
     * @return chunk X
     */
    int getChunkX();

    /**
     * Get the chunk Z coordinate that contains this block.
     *
     * @return chunk Z
     */
    int getChunkZ();

    /**
     * Calculate the distance between this coordinate and a player's location.
     *
     * @param player the player to measure the distance to
     * @return the distance in blocks
     */
    double getDistance(@NotNull Player player);

    /**
     * Convert this coordinate to a Bukkit {@link Location}.
     *
     * @return the location corresponding to this coordinate
     */
    Location toLocation();
}
