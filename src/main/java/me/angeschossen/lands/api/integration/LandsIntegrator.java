package me.angeschossen.lands.api.integration;

import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.LandArea;
import me.angeschossen.lands.api.land.LandWorld;
import me.angeschossen.lands.api.land.enums.SortMode;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


public interface LandsIntegrator {

    /**
     * Get cached landPlayer
     *
     * @param playerUUID UUID of player
     * @return LandPlayer or null, if not cached
     * @since 2.5.7
     */
    @Nullable
    LandPlayer getLandPlayer(@NotNull UUID playerUUID);

    /**
     * Get world
     *
     * @param world World
     * @return Will return null if is not a lands world
     */
    @Nullable
    LandWorld getLandWorld(@NotNull World world);

    /**
     * Is claimed land?
     *
     * @param location Location
     * @return Is claimed
     */
    boolean isClaimed(@NotNull Location location);

    @Deprecated
    CompletableFuture<Boolean> isClaimed(@NotNull String worldName, int chunkX, int chunkZ);

    /**
     * Is claimed?
     *
     * @param world  World
     * @param chunkX Chunk x
     * @param chunkZ Chunk z
     * @return true if claimed
     */
    boolean isClaimed(@NotNull World world, int chunkX, int chunkZ);

    /**
     * Get land.
     *
     * @param worldName Name of world, where land is located
     * @param landName  Name or displayname of land
     * @return Land or null, if not exists.
     * @since 2.5.7
     */
    @Deprecated
    Land getLand(@NotNull String worldName, @NotNull String landName);

    /**
     * Get landWorld.
     *
     * @param worldName Name of world.
     * @return LandWorld or null, if it's not an landWorld.
     */
    @Deprecated
    LandWorld getLandWorld(@NotNull String worldName);

    /**
     * Get land by name
     * Name is not case sensitive
     *
     * @param name Name
     * @return Land
     */
    Land getLand(@NotNull String name);

    /**
     * Get land by location
     * For permission you can use getArea instead.
     *
     * @param location Location
     * @return Land or null if wilderness
     */
    @Nullable Land getLand(@NotNull Location location);

    /**
     * Do not use this. World parameter is no longer valid.
     * Use {@link #getLand(Location)} instead.
     *
     * @param world  World
     * @param chunkX Chunk x
     * @param chunkZ Chunk z
     * @return Land or null if wilderness
     */
    @Deprecated
    @Nullable Land getLand(@NotNull World world, int chunkX, int chunkZ);

    /**
     * Get the sub area of the land at this location. This does not include the default area.
     * It is recommended to use {@link #getAreaByLoc(Location)} instead, if you want to cover the default area too.
     *
     * @param location Location
     * @return null, if not claimed or the area is not a sub area (default area)
     */
    @Deprecated
    @Nullable LandArea getArea(@NotNull Location location);

    /**
     * Get the sub or default area of the land at this location. It is recommended to use this instead of {@link #getArea(Location)}
     * @param location Location
     * @return null, if not claimed.
     */
    @Nullable Area getAreaByLoc(@NotNull Location location);

    @NotNull List<Land> getTopLands(SortMode sortMode);

    @NotNull List<Land> getTopLands(@NotNull SortMode sortMode, int page);

    @NotNull List<Land> getTopLand(@NotNull SortMode sortMode, int place);

    @NotNull List<String> printTopLands(SortMode sortMode, int page);

    /**
     * Get's lands wich hooks Lands.
     *
     * @return Plugin
     */
    @Nullable
    Plugin getPlugin();

    /**
     * Get name of integration.
     *
     * @return Name
     */
    @NotNull
    String getName();

    /**
     * Disables the APIHook
     */
    void disable();

    /**
     * Not needed anymore.
     */
    @Deprecated
    void disable(@Nullable String hookKey);

    /**
     * This method is not longer needed.
     */
    @NotNull
    @Deprecated
    String initialize();

    /**
     * Check if hook is enabled.
     *
     * @return Status
     */
    boolean isEnabled();

    /**
     * This method is no longer needed.
     */
    @Deprecated
    boolean getAccess(@NotNull String hookKey);

    /**
     * This method is no longer needed.
     */
    @Deprecated
    boolean isPublic();
}
