package me.angeschossen.lands.api.blockworks;

import java.util.Objects;

public class ChunkCoordinate implements me.angeschossen.lands.api.land.ChunkCoordinate {

    public final int x, z;
    private final int hashcode;

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
