package me.angeschossen.lands.api.land;

/**
 * Represents coordinates of a chunk.
 */
public interface ChunkCoordinate {

    /**
     * Get block x.
     *
     * @return Block x
     */
    int getBlockX();


    /**
     * Get block z.
     *
     * @return Block z
     */
    int getBlockZ();

    /**
     * Get chunk x.
     *
     * @return Chunk x
     */
    int getX();

    /**
     * Get chunk z.
     *
     * @return Chunk z
     */
    int getZ();

    /**
     * Get milliseconds timestamp when chunk was claimed.
     *
     * @return millis
     */
    long getClaimedAtMillis();
}
