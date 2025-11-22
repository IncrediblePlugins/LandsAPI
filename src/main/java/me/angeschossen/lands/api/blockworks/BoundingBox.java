package me.angeschossen.lands.api.blockworks;

import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

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
