package me.angeschossen.lands.api.blockworks;

import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an axis-aligned bounding box defined by two {@link BlockCoordinate} corners.
 */
public interface BoundingBox {
    /**
     * Get the upper corner.
     *
     * @return upper corner
     */
    @NotNull
    BlockCoordinate getMax();

    /**
     * Eval the middle of the bounding box.
     *
     * @return never null
     */
    @NotNull BlockCoordinate getMiddle();

    /**
     * Get the lower corner.
     *
     * @return never null
     */
    @NotNull
    BlockCoordinate getMin();

    /**
     * Check if the bounding box contains the given block coordinates.
     *
     * @param x block coordinate X
     * @param y block coordinate Y
     * @param z block coordinate Z
     * @return true if the coordinates are within this bounding box
     */
    boolean contains(int x, int y, int z);

    /**
     * Same as {@link #contains(int, int, int)}, but ignores y values.
     *
     * @param x Block coordinate X
     * @param z Block coordinate Z
     * @return true, if the boundingBox contains these coordinates.
     */
    boolean contains(int x, int z);

    /**
     * Get the world.
     *
     * @return world in which the bounding box is located
     */
    @NotNull
    World getWorld();
}
