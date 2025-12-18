package me.angeschossen.lands.api;

import com.github.angeschossen.applicationframework.api.util.ULID;
import me.angeschossen.lands.api.configuration.Configuration;
import me.angeschossen.lands.api.events.player.teleportation.PlayerRandomTeleportEvent;
import me.angeschossen.lands.api.flags.FlagRegistry;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.inbox.InboxMessageProvider;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Container;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.LandWorld;
import me.angeschossen.lands.api.levels.LevelsHandler;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.nation.Nation;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.player.OfflinePlayer;
import me.angeschossen.lands.api.player.PlayerCooldown;
import me.angeschossen.lands.api.player.PlayerLimit;
import me.angeschossen.lands.api.sorting.SortingContext;
import me.angeschossen.lands.api.war.storage.WarHandler;
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

    /**
     * Get an instance of the implementation.
     * You should assign this instance to a class variable, if possible.
     *
     * @param plugin Your plugin
     * @return Instance of your integration
     */
    @NotNull
    static LandsIntegration of(@NotNull Plugin plugin) {
        return APIHandler.getLandsIntegrationFactory().of(plugin);
    }

    /**
     * Allows you to add your own war elements to wars in Lands.
     * This needs to be set before Lands fully enabled. So after Lands loaded, but before it enables.
     *
     * @param warHandler if null, it will use the default war handler.
     */
    void setWarHandler(@Nullable WarHandler warHandler);

    /**
     * Get the current provider for raw inbox messages
     *
     * @return if null, none set
     */
    @Nullable
    InboxMessageProvider getInboxMessageProvider();

    /**
     * Set provider for raw inbox messages.
     *
     * @param inboxMessageProvider if null, current provider will be removed
     */
    void setInboxMessageProvider(@Nullable InboxMessageProvider inboxMessageProvider);

    /**
     * Get the configuration of Lands.
     *
     * @return never null
     */
    @NotNull
    Configuration getConfiguration();

    /**
     * Check if two players can attack each other at the given location.
     * Besides checking flags, this does also take combat tags into account.
     *
     * @param attacker     The attacker
     * @param target       The defender
     * @param location     Location of the fight
     * @param setCombatTag Should Lands set a combat tag at these two players, if they are allowed to fight at the given location?
     *                     Depending on the servers configuration this will result in players beeing allowed to fight for a configured period, even if they move into a safe claim.
     *                     The tag will only be set if the world is configured to be a combat tag world in the config file.
     *                     This prevents players running away into their claim to escape a fight, which they have started.
     * @param sendMessage  Should Lands send a deny message if result is false?
     * @return true, if the world is not a Lands world or the players are allowed to fight at the given location
     */
    boolean canPvP(@NotNull Player attacker, @NotNull Player target, @NotNull Location location, boolean setCombatTag, boolean sendMessage);

    /**
     * Get area at the specified coordinate.
     * If you need to retrieve an area that is located insice an unloaded chunk, use {@link #getUnloadedArea(Location)} instead.
     * However, this method should always be prefered if possible.
     *
     * @param location The location
     * @return null, if the target chunk isn't claimed or loaded
     */
    @Nullable
    Area getArea(@NotNull Location location);

    /**
     * Get an area at the specified coordinate, even if the chunk isn't loaded.
     * This method can be called async. It doesn't make any database calls, but requires a deep lookup in cached data.
     *
     * @param location The location
     * @return null, if the target chunk isn't claimed
     */
    @Nullable
    Area getUnloadedArea(@NotNull Location location);

    /**
     * The flag registry allows you to make some more specific actions than in the Flags class.
     *
     * @return The flag registry with more advanced methods for flag management
     */
    @NotNull
    FlagRegistry getFlagRegistry();

    /**
     * Get land claimed land from a loaded chunk.
     * This shouldn't be used, if you want to check flag states. Use {@link #getArea(Location)} instead.
     *
     * @param world  The world
     * @param chunkX Chunk x value
     * @param chunkZ Chunk z value
     * @return null, if the chunk isn't claimed or not loaded. If you want to get the land from an unloaded chunk, use {@link #getLandByUnloadedChunk(World, int, int)} instead.
     * However, this method should always prefered, if possible. Most bukkit events etc. usually have chunks already loaded
     */
    @Nullable
    Land getLandByChunk(@NotNull World world, int chunkX, int chunkZ);

    /**
     * Get land claimed land from a unloaded or loaded chunk. If you're using this method in an event handler or something else
     * where the chunk is loaded anyway, make sure to use {@link #getLandByChunk(World, int, int)} instead.
     * This shouldn't be used, if you want to check flag states. Use {@link #getArea(Location)} instead.
     * This method can be called async. It doesn't make any database calls, but requires a deep lookup in cached data.
     *
     * @param world  The world
     * @param chunkX Chunk x value
     * @param chunkZ Chunk z value
     * @return null, if the chunk isn't claimed
     */
    @Nullable
    Land getLandByUnloadedChunk(@NotNull World world, int chunkX, int chunkZ);

    /**
     * Get land by its id.
     *
     * @param id The id of the land.
     * @return null, if no land with this id exists
     * @deprecated Use {@link #getLandByULID(ULID)} instead
     */
    @Nullable
    @Deprecated
    Land getLandById(int id);

    /**
     * Get land by its ULID.
     *
     * @param ulid ULID of the land
     * @return null, if no land with this ULID exists
     */
    @Nullable
    Land getLandByULID(@NotNull ULID ulid);

    /**
     * Get land or nation by its ULID.
     *
     * @param ulid ULID of the land or nation
     * @return null, if no land and no nation with this ULID exists
     */
    @Nullable
    MemberHolder getMemberholderByULID(@NotNull ULID ulid);

    /**
     * Get a land by name.
     *
     * @param name Name of the land without color codes. Not case sensitive
     * @return null, if no land with this name exists.
     */
    @Nullable
    Land getLandByName(@NotNull String name);

    /**
     * Get cached player data.
     *
     * @param playerUUID UUID of the player
     * @return null, if the player is not online
     * @since 2.5.7
     */
    LandPlayer getLandPlayer(@NotNull UUID playerUUID);

    /**
     * Get world data.
     *
     * @param world The bukkit world
     * @return null, if this world is not a configured claim world
     */
    @Nullable
    LandWorld getWorld(@NotNull World world);

    /**
     * Get all lands on this server.
     *
     * @return all lands, including camps and admin lands
     */
    @NotNull
    Collection<Land> getLands();

    /**
     * Using the LevelsHandler, you can register your own level requirements.
     *
     * @return LevelsHandler
     * @since 5.14.0
     */
    @NotNull
    LevelsHandler getLevelsHandler();

    /**
     * Get the last time a cooldown was set for a player.
     * The cooldown will expire once the {@link PlayerCooldown#getTime()} is over.
     *
     * @param playerUUID the player
     * @param cooldown   the cooldown
     * @return null, if cooldown wasn't set yet or cooldown is expired. Never negative
     */
    @Nullable
    Long getPlayerCooldown(@NotNull UUID playerUUID, @NotNull PlayerCooldown cooldown);

    /**
     * Get the name of this integration.
     *
     * @return Identical with the plugin name
     */
    @NotNull
    String getName();

    /**
     * Get a nation by its ID.
     *
     * @param id ID of the nation
     * @return null, if no nation with this id exists
     * @deprecated Use {@link #getNationByULID(ULID)} instead
     */
    @Nullable
    @Deprecated
    Nation getNationById(int id);

    /**
     * Get nation by its ULID.
     *
     * @param ulid ULID of the nation
     * @return null, if no nation with this ULID exists
     */
    @Nullable
    Nation getNationByULID(@NotNull ULID ulid);

    /**
     * Get a nation by its name.
     *
     * @param name The name of the nation without color codes. Not case sensitive
     * @return null, if no nation with this name exists
     */
    @Nullable
    Nation getNationByName(@NotNull String name);

    /**
     * Get all nations
     *
     * @return Includes nations owned by an admin land
     */
    @NotNull
    Collection<Nation> getNations();

    /**
     * Get reduced data for an player that is offline or online.
     *
     * @param playerUID UUID of the player
     * @return Offline player or instance of the loaded player, if the player is online
     */
    @NotNull
    CompletableFuture<OfflinePlayer> getOfflineLandPlayer(@NotNull UUID playerUID);

    /**
     * Get plugin which hooks into Lands.
     *
     * @return Your plugin
     */
    @NotNull
    Plugin getPlugin();

    /**
     * Get a sorted context. This is used for land and nation sorting related to leaderboards.
     *
     * @param id The context id. Default: land, nation
     * @return null, if the sorting context does not exist.
     */
    @Nullable
    SortingContext<?> getSortingContext(@NotNull String id);

    /**
     * Execute actions once Lands is fully loaded.
     *
     * @param runnable The runnable that will be executed
     * @since 5.13.0
     */
    void onLoad(@NotNull Runnable runnable);

    /**
     * Randomly teleport a player in the given world. This can still be cancelled by 3rd party plugins.
     * This method calls the cancellable {@link PlayerRandomTeleportEvent}.
     *
     * @param landPlayer The player
     * @param world      The destination world
     * @return
     */
    CompletableFuture<Location> randomTeleport(@NotNull LandPlayer landPlayer, @NotNull World world);
}
