package me.angeschossen.lands.api.legacy;

import me.angeschossen.lands.api.exceptions.FlagConflictException;
import me.angeschossen.lands.api.flags.Flag;
import me.angeschossen.lands.api.flags.FlagRegistry;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.LandArea;
import me.angeschossen.lands.api.land.LandWorld;
import me.angeschossen.lands.api.levels.LevelsHandler;
import me.angeschossen.lands.api.nation.Nation;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.player.OfflinePlayer;
import me.angeschossen.lands.api.sorting.SortingContext;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


/**
 * Use {@link me.angeschossen.lands.api.LandsIntegration} instead.
 */
@Deprecated
public interface LandsIntegrator {

    /**
     * Check if two players can attack each other at the given location.
     *
     * @param attacker     The attacker
     * @param target       The defender
     * @param location     Location of the fight
     * @param setCombatTag Should Lands set a combat tag at these two players, if they are allowed to fight at the given location?
     *                     Depending on the servers configuration this will result in players beeing allowed to fight for a configured period, even if they move into a safe claim.
     *                     This prevents players running away into their claim to escape a fight, which they have started.
     * @param sendMessage  Should Lands send a deny message if result is false?
     * @return This will return true, if the world is not a Lands world or the players are allowed to fight at the given location.
     */
    boolean canPvP(@NotNull Player attacker, @NotNull Player target, @NotNull Location location, boolean setCombatTag, boolean sendMessage);

    boolean isChunkClaimed(World w, int x, int z);

    boolean isChunkClaimedUnloaded(World w, int x, int z);

    @Deprecated
    void disable();

    @Deprecated
    void disable(@Nullable String hookKey);

    @Deprecated
    boolean getAccess(@NotNull String hookKey);

    /**
     * Use {@link #getArea(World, int, int, int)} or {@link #getAreaByLoc(Location)} instead.
     * Get the sub area of the land at this location. This does not include the default area.
     * It is recommended to use {@link #getAreaByLoc(Location)} instead, if you want to cover the default area too.
     *
     * @param location Location
     * @return null, if not claimed or the area is not a sub area (default area)
     */
    @Deprecated
    @Nullable
    LandArea getArea(@NotNull Location location);

    /**
     * Get area at the specified coordinate.
     *
     * @param world World
     * @param x     Block x
     * @param y     Block y
     * @param z     Block z
     * @return The area at the specific coordinate. Might return null, if the coordinate is unloaded.
     */
    @Nullable
    Area getArea(@NotNull World world, int x, int y, int z);

    /**
     * Get the sub or default area of the land at this location. It is recommended to use this instead of {@link #getArea(Location)}
     *
     * @param location Location
     * @return Will return null if the location is not claimed or the location is unloaded.
     */
    @Nullable
    Area getAreaByLoc(@NotNull Location location);

    /**
     * The flag registry allows you to make some more specific actions than in the Flags class.
     *
     * @return The flag registry
     */
    @NotNull FlagRegistry getFlagRegistry();

    /**
     * Get land by name
     * Name is not case sensitive
     *
     * @param name Name
     * @return Land
     */
    Land getLand(@NotNull String name);

    /**
     * Use {@link #getLand(World, int, int)} instead.
     */
    @Deprecated
    Land getLand(@NotNull Location location);

    /**
     * Get the land of the given chunk. If you need to get the land for an unloaded
     * chunk, use {@link #getLandUnloaded(World, int, int)} instead.
     *
     * @param world  World
     * @param chunkX Chunk x
     * @param chunkZ Chunk z
     * @return null, if wilderness
     */
    @Nullable
    Land getLand(@NotNull World world, int chunkX, int chunkZ);

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
     * Same as {@link #getLand(World, int, int)}, but takes unloaded chunks into account.
     * If you need to get the land in situations where the chunk is loaded, its recommended
     * to use {@link #getLand(World, int, int)} instead.
     *
     * @param world World
     * @param x     Chunk x
     * @param z     Chunk z
     * @return null, if not claimed
     */
    @Nullable Land getLandUnloaded(@NotNull World world, int x, int z);

    /**
     * Get world
     *
     * @param world World
     * @return Will return null if is not a lands world
     */
    @Nullable
    LandWorld getLandWorld(@NotNull World world);

    @Deprecated
    LandWorld getLandWorld(@NotNull String worldName);

    /**
     * Get all lands.
     *
     * @return Includes camps and admin lands
     */
    @NotNull
    Collection<Land> getLands();

    /**
     * Manage levels.
     *
     * @return LevelsHandler
     * @since 5.14.0
     */
    @NotNull LevelsHandler getLevelsHandler();

    /**
     * Get name of integration.
     *
     * @return Name of the plugin.
     */
    @NotNull
    String getName();

    /**
     * Get a nation by its name.
     *
     * @param name The name without color codes
     * @return null, if no nation with this name exists.
     */
    @Nullable Nation getNation(@NotNull String name);

    /**
     * Get all nations.
     *
     * @return Includes nations owned by the server
     */
    @NotNull Collection<Nation> getNations();

    /**
     * Get data for an player that is offline.
     *
     * @param playerUID UUID of the player
     * @return Offline player or instance of the loaded player, if the player is online
     */
    @NotNull
    CompletableFuture<OfflinePlayer> getOfflineLandPlayer(@NotNull UUID playerUID);

    /**
     * Get plugin which hooks into Lands.
     *
     * @return Plugin
     */
    @Nullable
    Plugin getPlugin();

    /**
     * Get a sorted context.
     *
     * @param id The context id. Default: land, nation
     * @return null, if the sorting context does not exist.
     */
    @Nullable SortingContext<?> getSortingContext(@NotNull String id);

    @NotNull
    @Deprecated
    String initialize();

    /**
     * Use {@link #isClaimed(World, int, int)} instead.
     */
    @Deprecated
    boolean isClaimed(@NotNull Location location);

    /**
     * Use {@link #isClaimed(World, int, int)} instead.
     */
    @Deprecated
    CompletableFuture<Boolean> isClaimed(@NotNull String worldName, int chunkX, int chunkZ);

    /**
     * Checks if the given chunk is claimed. This does not take unloaded chunks into account.
     * If you need this method for unloaded chunks, use {@link #isClaimedUnloaded(World, int, int)} instead.
     *
     * @param world  World
     * @param chunkX Chunk x
     * @param chunkZ Chunk z
     * @return true, if the chunk of the location is claimed.
     * Works also in unloaded regions.
     */
    boolean isClaimed(@NotNull World world, int chunkX, int chunkZ);

    /**
     * Same as {@link #isClaimed(World, int, int)}, but also takes unloaded regions into account.
     * If you call this method in situations where the chunk is loaded, it's recommended to use
     * {@link #isClaimed(World, int, int)} instead. This method is backed by a TTL cache.
     * This method is backed by a TTL cache.
     *
     * @param world World
     * @param x     Chunk x
     * @param z     Chunk z
     * @return false, if not claimed
     */
    boolean isClaimedUnloaded(@NotNull World world, int x, int z);

    @Deprecated
    boolean isEnabled();

    @Deprecated
    boolean isPublic();

    /**
     * Execute actions once Lands is loaded.
     * This is not needed in most use cases.
     *
     * @param runnable The runnable that will be executed.
     * @since 5.13.0
     */
    void onLoad(@NotNull Runnable runnable);

    /**
     * Register your owns flags into Lands. They will also be toggleable in the GUI menus if you set display to true.
     *
     * @param flag RoleFlag or LandFlag
     *             RoleFlags are "playerflags" and LandFlags are natural flags.
     *             For roleflags you need to specify the Category type ACTION or MANAGEMENT.
     * @throws FlagConflictException    A flag with this name already exists.
     * @throws IllegalArgumentException The name is invalid. It contains illegal characters or is too long (greater than 20 chars).
     */
    void registerFlag(@NotNull Flag flag) throws FlagConflictException, IllegalArgumentException;

    /**
     * Randomly teleport a player in the given world.
     *
     * @param landPlayer The player
     * @param world      The destination world
     */
    void wild(@NotNull LandPlayer landPlayer, @NotNull World world);
}
