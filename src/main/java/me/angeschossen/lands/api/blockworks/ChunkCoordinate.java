package me.angeschossen.lands.api.blockworks;

import java.util.Objects;

/**
 * Immutable value object representing a chunk coordinate (chunk X and Z).
 * Implements {@link me.angeschossen.lands.api.land.ChunkCoordinate} for use within the Lands API.
 */
public class ChunkCoordinate implements me.angeschossen.lands.api.land.ChunkCoordinate {

    /** Chunk X coordinate. */
    public final int x;
    /** Chunk Z coordinate. */
    public final int z;
    private final int hashcode;

    /**
     * Create a new chunk coordinate.
     *
     * @param x chunk X coordinate
     * @param z chunk Z coordinate
     */
    public ChunkCoordinate(int x, int z) {
        this.x = x;
        this.z = z;
        this.hashcode = Objects.hash(x, z);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof me.angeschossen.lands.api.land.ChunkCoordinate)) {
            return false;
        }

        me.angeschossen.lands.api.land.ChunkCoordinate c = (me.angeschossen.lands.api.land.ChunkCoordinate) object;
        return c.getX() == x && c.getZ() == z;
    }

    public int getBlockX() {
        return getX() << 4;
    }

    public int getBlockZ() {
        return getZ() << 4;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    @Override
    public long getClaimedAtMillis() {
        return 0;
    }

    @Override
    public int hashCode() {
        return hashcode;
    }

    @Override
    public String toString() {
        return "ChunkCoordinate{x=" + x + ",z=" + z + "}";
    }
}
