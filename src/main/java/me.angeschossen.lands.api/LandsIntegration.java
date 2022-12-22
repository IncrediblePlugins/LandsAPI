package me.angeschossen.lands.api;

import me.angeschossen.lands.api.flags.FlagRegistry;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
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

public interface LandsIntegration {

    @NotNull
    static LandsIntegration of(@NotNull Plugin plugin) {
        return APIHandler.getLandsIntegrationFactory().of(plugin);
    }

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


    @Nullable Area getAreaUnloaded(@NotNull World world, int x, int y, int z);

    /**
     * The flag registry allows you to make some more specific actions than in the Flags class.
     *
     * @return The flag registry
     */
    @NotNull FlagRegistry getFlagRegistry();

    /**
     * Get land by its id.
     * @param id The id of the land.
     * @return null, if no land with this id exists.
     */
    @Nullable
    Land getLandById(int id);

    /**
     * Get land by name
     * Name is not case sensitive
     *
     * @param name Name
     * @return Land
     */
    @Nullable
    Land getLandByName(@NotNull String name);

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
    LandWorld getWorld(@NotNull World world);

    /**
     * Get all lands.
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
     * Get a nation by its ID.
     * @param id ID of the nation
     * @return null, if no nation with this id exists
     */
    @Nullable Nation getNationById(int id);

    /**
     * Get a nation by its name.
     * @param name The name without color codes
     * @return null, if no nation with this name exists.
     */
    @Nullable Nation getNationByName(@NotNull String name);

    /**
     * Get all nations.
     *
     * @return Includes nations owned by the server
     */
    @NotNull Collection<Nation> getNations();

    /**
     * Get data for an player that is offline.
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
    @NotNull
    Plugin getPlugin();

    /**
     * Get a sorted context.
     *
     * @param id The context id. Default: land, nation
     * @return null, if the sorting context does not exist.
     */
    @Nullable SortingContext<?> getSortingContext(@NotNull String id);

    /**
     * Checks if the given chunk is claimed. This does not take unloaded chunks into account.
     * If you need this method for unloaded chunks, use {@link #isChunkClaimedUnloaded(World, int, int)} instead.
     *
     * @param world  World
     * @param chunkX Chunk x
     * @param chunkZ Chunk z
     * @return true, if the chunk of the location is claimed.
     * Works also in unloaded regions.
     */
    boolean isChunkClaimed(@NotNull World world, int chunkX, int chunkZ);

    /**
     * Same as {@link #isChunkClaimed(World, int, int)}, but also takes unloaded regions into account.
     * If you call this method in situations where the chunk is loaded, it's recommended to use
     * {@link #isChunkClaimed(World, int, int)} instead. This method is backed by a TTL cache.
     * This method is backed by a TTL cache.
     *
     * @param world World
     * @param x     Chunk x
     * @param z     Chunk z
     * @return false, if not claimed
     */
    boolean isChunkClaimedUnloaded(@NotNull World world, int x, int z);

    /**
     * Execute actions once Lands is loaded.
     * This is not needed in most use cases.
     *
     * @param runnable The runnable that will be executed.
     * @since 5.13.0
     */
    void executeOnPluginLoaded(@NotNull Runnable runnable);

    /**
     * Randomly teleport a player in the given world.
     *
     * @param landPlayer The player
     * @param world      The destination world
     */
    void randomTeleport(@NotNull LandPlayer landPlayer, @NotNull World world);
}
