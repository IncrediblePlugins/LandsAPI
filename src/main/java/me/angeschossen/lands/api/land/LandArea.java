package me.angeschossen.lands.api.land;

import me.angeschossen.lands.api.blockworks.BoundingBox;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A land area is a sub area inside a land.
 * This isn't a {@link Land#getDefaultArea()}.
 */
public interface LandArea extends Area {

    /**
     * Get the world.
     *
     * @return The world in which this sub area is located in
     */
    @Nullable
    World getWorld();

    /**
     * Get the bounding box of the sub area.
     *
     * @return Will return null, if the area isn't setup yet ({@link #isSetup()}).
     */
    @Nullable
    BoundingBox getBoundingBox();

    /**
     * Check if the subarea contains a specific chunk.
     *
     * @param chunkX            chunk X
     * @param chunkZ            chunk Z
     * @param allowIntersection if true, the chunk can be overlapped and doesn't need to be contained fully
     * @return false, if the area doesn't contain the chunk
     */
    boolean containsChunk(int chunkX, int chunkZ, boolean allowIntersection);

    /**
     * Set a name for this area.
     *
     * @param name The new land. You can include color codes
     * @return false, if the land already has an area witht that name
     */
    boolean setName(@NotNull String name);

    /**
     * Use {@link #getBoundingBox()} instead.
     *
     * @param x Block x
     * @param y Block y
     * @param z Block z
     * @return false, if the area doesn't contain this coordinate.
     */
    @Deprecated
    boolean contains(int x, int y, int z);

    /**
     * Check if this area has both corners set.
     *
     * @return false, if the area isn't setup yet
     */
    boolean isSetup();

    /**
     * Get the claim information of this area.
     *
     * @return null, if {@link #isSetup()} returns false
     */
    @Nullable Container getContainer();

}
