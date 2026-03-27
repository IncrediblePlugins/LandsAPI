package me.angeschossen.lands.api.war.captureflag;

import me.angeschossen.lands.api.land.LandWorld;
import org.jetbrains.annotations.NotNull;

/**
 * A capture area can for example be a sub area of a land or a collection if chunks.
 */
public interface CaptureArea {

    /**
     * Checks if the location is inside the capture area.
     *
     * @param world the world
     * @param x     block X
     * @param y     block Y
     * @param z     block Z
     * @return true, if the location is inside the capture area
     */
    boolean contains(@NotNull LandWorld world, int x, int y, int z);
}
