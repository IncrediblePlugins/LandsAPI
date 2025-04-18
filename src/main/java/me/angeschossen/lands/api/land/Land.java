package me.angeschossen.lands.api.land;

import com.github.angeschossen.pluginframework.api.blockutil.UnloadedPosition;
import me.angeschossen.lands.api.events.land.DeleteReason;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.land.enums.LandType;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.nation.Nation;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.player.TrustedPlayer;
import me.angeschossen.lands.api.war.War;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * A land can be of different types ({@link #getLandType()}).
 */
public interface Land extends MemberHolder, SystemFlagStatesHolder {
    /**
     * Create a new land.
     *
     * @param name     The name. Depending on the servers settings, this may not include numbers or other non alphabetical characters
     * @param landType The type
     * @param location The location of the new land. Should be located in a loaded chunk
     * @param owner    Owner of this new land
     * @param claim    If true, the new land creation will claim an initial area depending on your servers configuration.
     * @param msg      If true, sends the owner failure and success messages during creation
     * @return the result of the future is null, if the land creation failed at this location
     */
    static CompletableFuture<? extends Land> of(@NotNull String name, @NotNull LandType landType, @NotNull Location location, @NotNull LandPlayer owner, boolean claim, boolean msg) {
        return APIHandler.getLandsIntegrationFactory().landOf(name, landType, location, owner, claim, msg);
    }

    /**
     * Get information about claims and sub areas inside a specific world.
     *
     * @param world The world
     * @return null, if there are no claims in the provided world
     */
    @Nullable
    Container getContainer(@NotNull World world);

    /**
     * Get information about claims and sub areas in all worlds, that have at least one claim of this land.
     *
     * @return Collection of worlds that have claims of this land inside it
     */
    @NotNull
    Collection<? extends Container> getContainers();

    /**
     * Get the inventory of the storage.
     * @return never null
     */
    @NotNull Inventory getItemStorage();

    /**
     * Get the default areas of this land. Parts of the land that aren't part of a sub areas, are automatically part of this area.
     *
     * @return Each land has a default area
     */
    @NotNull
    Area getDefaultArea();

    /**
     * Claim a chunk. It's recommended to call {@link #calculateLevel(boolean)}, if this method returns true (successful claim), in order to calculate the level as it might has changed after the claim.
     *
     * @param landPlayer The player that is claiming the chunk.
     * @param world      The world
     * @param x          Chunk x
     * @param z          Chunk z
     * @return false, if the chunk a 3rd party plugin cancels the claiming or the chunk is already claimed.
     */
    CompletableFuture<Boolean> claimChunk(@Nullable LandPlayer landPlayer, @NotNull World world, int x, int z);

    /**
     * Delete this land.
     *
     * @param landPlayer The player that initiates the deletion.
     *                   This is mainly used to prevent the player from receiving a message from your plugin and additionally a inbox message in lands of the nation.
     *                   Just to prevent spam. Also this player will receive cashback, if enabled by the server.
     * @return false, if a 3rd party plugin cancelled the deletion.
     */
    CompletableFuture<Boolean> delete(@Nullable LandPlayer landPlayer);

    /**
     * Deprecated. Use {@link #delete(LandPlayer)} instead.
     */
    @Deprecated
    CompletableFuture<Boolean> delete(@Nullable LandPlayer landPlayer, @NotNull DeleteReason reason);

    /**
     * Deprecated. Use {@link #delete(LandPlayer)} instead.
     */
    @Deprecated
    void delete(@Nullable Player deleter);

    /**
     * Get area by its name.
     *
     * @param name Not case sensitive. Chat colors will be removed for the lookup
     * @return null, if there's no sub area with this name. This also checks the default area ({@link #getDefaultArea()}).
     */
    Area getArea(@NotNull String name);

    /**
     * Get a sub area from a location.
     *
     * @param location The location
     * @return null, if the part of the land doesn't belong to a sub area. If null, it belongs to the default area ({@link #getDefaultArea()}).
     */
    @Nullable
    Area getArea(@NotNull Location location);

    /**
     * Use {@link #getContainer(World)} instead.
     *
     * @param world The world
     * @return null, if no claims in this world
     */
    @Deprecated
    @Nullable
    Collection<ChunkCoordinate> getChunks(@NotNull World world);

    /**
     * Use {@link #getContainer(World)} instead.
     *
     * @param world The world
     * @return null, if no claims in this world
     */
    @Deprecated
    Collection<? extends LandArea> getSubAreas(@NotNull World world);

    /**
     * Use {@link #getArea(String)} instead.
     *
     * @param name Area name
     * @return false, if no such area exists.
     */
    @Deprecated
    boolean hasArea(@NotNull String name);

    /**
     * Ban a player from the whole land. This automatically untrusts the player as well.
     *
     * @param playerUID The player to be banned
     * @return false, if the player was already banned in all parts of the land
     */
    boolean banPlayer(@NotNull UUID playerUID);

    /**
     * Unban a player from the whole land.
     *
     * @param playerUID The player to be unbanned
     */
    void unbanPlayer(@NotNull UUID playerUID);

    /**
     * Get the enter title that would be sent to a entering player.
     *
     * @param player The player that enters the land.
     * @return Messages with placeholders replaced with the values.
     */
    @NotNull
    String getTitleMessage(@Nullable LandPlayer player);

    /**
     * Get the current war the land is currently engaged in.
     *
     * @return null, if the land isn't involded in an active war or the war is still in preparation and not yet active.
     */
    @Nullable
    War getWar();

    /**
     * Get the nation that this land belongs to.
     *
     * @return null, if the land doesn't belong to any nation
     */
    @Nullable
    Nation getNation();

    /**
     * Get upkeep costs of this land.
     *
     * @return Never negative
     */
    double getUpkeepCosts();

    /**
     * Get the land name with color codes that a player set in the name.
     *
     * @return Just like {@link #getName()}, but with color codes included
     */
    @NotNull
    String getColorName();

    /**
     * Get name of the land. To retrieve the name with color codes, use {@link #getColorName()}.
     *
     * @return Name of the land without color.
     */
    @NotNull
    String getName();

    /**
     * Get the owners UUID.
     *
     * @return UUID of the owner
     */
    @NotNull
    UUID getOwnerUID();

    /**
     * Unclaim a specific chunk.
     *
     * @param landPlayer null, if no player initiated this action
     * @param world      world in which the chunk is located
     * @param x          Chunk x
     * @param z          Chunk z
     * @return null, if the land didn't claim this chunk or a 3rd party plugin cancels the {@link me.angeschossen.lands.api.events.ChunkDeleteEvent}
     */
    @NotNull CompletableFuture<ChunkCoordinate> unclaimChunk(@NotNull World world, int x, int z, @Nullable LandPlayer landPlayer);

    /**
     * Untrust a player in the whole land.
     *
     * @param playerUID The player to untrust
     * @return false, if the player was already untrusted in the whole land
     */
    boolean untrustPlayer(@NotNull UUID playerUID);

    /**
     * Get the spawn location.
     *
     * @return null, if no spawn set, world isn't loaded or destination is on another server.
     */
    @Nullable
    Location getSpawn();

    /**
     * Get the spawn location.
     *
     * @return null, if no spawn set
     */
    @Nullable
    UnloadedPosition getSpawnPosition();

    /**
     * Set spawn location.
     *
     * @param location Location of the new spawn
     */
    void setSpawn(@Nullable Location location);

    /**
     * Use {@link #getChunksAmount()} instead.
     *
     * @return Amount of claimed chunks
     */
    @Deprecated
    int getSize();

    /**
     * Trust a player to the whole land.
     *
     * @return false, if the player was already trusted in the whole land
     */
    boolean trustPlayer(@NotNull UUID playerUID);

    /**
     * Get max amount of members. May depend on the numbered permission
     * lands.members.number, level and other factors.
     *
     * @return Max amount of members. Never negative
     */
    int getMaxMembers();

    /**
     * Get the land type
     *
     * @return Never null
     */
    @NotNull
    LandType getLandType();

    /**
     * Get amount of claimable chunks.
     *
     * @return Max chunk claims. Never negative
     */
    int getMaxChunks();

    /**
     * Use {@link #getMaxChunks()} instead.
     *
     * @param countNation If rewards from nation level should be counted
     * @return Never negative
     */
    @Deprecated
    int getMaxChunks(boolean countNation);

    /**
     * Set title message, which appears if a player enters the land.
     * The player itself must have enter titles enabled in their personal settings menu.
     *
     * @param title If title is null, it will set the default title instead.
     */
    void setTitleMessage(@Nullable String title);

    /**
     * Set a new owner.
     *
     * @param playerUID The new owner
     */
    void setOwner(@NotNull UUID playerUID);

    /**
     * Check if land has claimed a specific chunk.
     *
     * @param world World
     * @param x     X of chunk
     * @param z     Z of chunk
     * @return true, if claimed by this land
     */
    boolean hasChunk(@NotNull World world, int x, int z);

    /**
     * Get a collection of all online land members
     *
     * @return Collection of online land members
     */
    @NotNull
    Collection<Player> getOnlinePlayers();

    /**
     * Get trusted information of a trusted player.
     *
     * @param playerUUID The trusted player
     * @return null, if player isn't trusted anywhere in the land
     */
    @Nullable
    TrustedPlayer getTrustedPlayer(@NotNull UUID playerUUID);

    /**
     * Does land exist?
     *
     * @return false, if meanwhile the land has been deleted
     */
    boolean exists();

    /**
     * Get the bank balance. Some servers might have banks disabled.
     *
     * @return Never negative
     */
    double getBalance();

    /**
     * Set land bank balance.
     *
     * @param balance Minimum value is 0
     */
    boolean setBalance(double balance);

    /**
     * Get the category of this land. Players can change the category of their land.
     * Categories help lands to present themselves as a shop focused land etc.
     *
     * @return null, if the land doesn't have any category set
     */
    @Nullable
    LandCategory getCategory();

    /**
     * Add or remove money from the bank.
     *
     * @param value If negative, it will remove the amount from the balance.
     * @return If value was negative and result smaller then 0, false. If false, this method has no effect.
     */
    boolean modifyBalance(double value);

}
