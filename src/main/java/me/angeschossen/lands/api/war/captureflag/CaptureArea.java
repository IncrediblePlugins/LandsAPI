package me.angeschossen.lands.api.war.captureflag;

import me.angeschossen.lands.api.land.LandWorld;
import org.jetbrains.annotations.NotNull;

public interface CaptureArea {

    boolean contains(@NotNull LandWorld world, int x, int y, int z);
}
