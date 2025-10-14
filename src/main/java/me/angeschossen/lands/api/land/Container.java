package me.angeschossen.lands.api.land;

import me.angeschossen.lands.api.land.block.LandBlock;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * A container includes claiming information of a land for a specific world.
 * This mainly includes the claimed chunks and sub areas.
 */
public interface Container {
    /**
     * Get minimum Y value. Depends on the servers config and land type.
     *
     * @return In bound with the world's min. height.
     */
    int getMinY();

    /**
     * Get maximum Y value. Depends on the servers config and land type.
     *
     * @return In bound with the world's max. height.
     */
    int getMaxY();

    /**
     * Get the world.
     *
     * @return Never null
     */
    @NotNull LandWorld getWorld();

    /**
     * Get landblock by its coordinate and block type.
     *
     * @param material if provided, it speeds of the lookup by checking if the block type
     *                 belongs to any landblock.
     * @param x        Block x
     * @param y        Block y
     * @param z        Block z
     * @return null, if no landblock with this material type exits or no landblock at the coordinates
     */
    @Nullable LandBlock getLandBlock(@Nullable Material material, int x, int y, int z);

    /**
     * Get all landblocks that are placed inside the land.
     *
     * @return only blocks that are in the world of the container
     */
    @NotNull
    Collection<? extends LandBlock> getLandBlocks();

    /**
     * Get sub areas of this land in this world.
     *
     * @return Collection of sub areas. Doesn't include the default area ({@link Land#getDefaultArea()}.
     */
    @NotNull Collection<? extends LandArea> getAreas();


    /**
     * Get claimed chunks of this land in this world.
     *
     * @return Collection of claimed chunks.
     */
    @NotNull Collection<? extends ChunkCoordinate> getChunks();

    /**
     * Get all areas that contain a specific chunk.
     *
     * @param x                 chunk X
     * @param z                 chunk Z
     * @param allowIntersection if false, it will only return one area that contains the chunk fully and doesn't just partially overlaps with the chunk
     * @return will contain the lands default area ({@link Land#getDefaultArea()}, if the areas inside this chunk only overalapped and didn't contain the chunk fully.
     */
    @NotNull Collection<? extends Area> getAreasInChunk(int x, int z, boolean allowIntersection);

    /**
     * Check if chunk is claimed by this land.
     *
     * @param x Chunk x identifier
     * @param z Chunk z identifier
     * @return false, if not claimed by this land
     */
    boolean hasChunk(int x, int z);

    /**
     * Checks if y is in bounds with {@link #getMinY()} and {@link #getMaxY()}.
     *
     * @param y Y value
     * @return false, if out of bounds
     */
    boolean isInYLevel(int y);

    /**
     * Get the land to which the container belongs.
     *
     * @return land of the container
     */
    @NotNull
    Land getLand();
}
