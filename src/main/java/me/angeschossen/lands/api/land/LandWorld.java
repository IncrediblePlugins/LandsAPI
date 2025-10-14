package me.angeschossen.lands.api.land;

import com.github.angeschossen.applicationframework.api.util.ULID;
import me.angeschossen.lands.api.flags.type.NaturalFlag;
import me.angeschossen.lands.api.flags.type.RoleFlag;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * A world contain claim information for the specific world.
 */
public interface LandWorld {
    /**
     * Get the globally unique ID. This ID is unique across all servers and is mainly used for the database.
     *
     * @return Universally unique lexicographically sortable identifier
     */
    @NotNull ULID getULID();

    /**
     * Check if players are allowed to do certain actions in the wilderness.
     *
     * @param player      The player
     * @param location    The location of the action
     * @param roleFlag    The flag
     * @param sendMessage If true, this will send a message to the player is they're not allowed to do this
     * @return false, if the player isn't allowed to do this in the wilderness
     */
    boolean hasWildernessRoleFlag(@NotNull LandPlayer player, @NotNull Location location, @NotNull RoleFlag roleFlag, boolean sendMessage);

    /**
     * Use {@link #getArea(int, int, int)} instead.
     * Get area at the specified coordinate.
     * If you need to retrieve an area that is located insice an unloaded chunk, use {@link #getAreaByUnloaded(int, int, int)} instead.
     * However, this method should always be prefered if possible.
     *
     * @param location The location
     * @return null, if the target chunk isn't claimed or loaded
     */
    @Deprecated
    @Nullable
    Area getArea(@NotNull Location location);

    /**
     * Get information about world related claim data of a land that claimed a specific chunk.
     *
     * @param chunkX chunk X
     * @param chunkZ chunk Z
     * @return null, if the chunk isn't claimed
     */
    @Nullable
    Container getContainer(int chunkX, int chunkZ);

    /**
     * Get land claimed land from a loaded chunk.
     * This shouldn't be used, if you want to check flag states. Use {@link #getArea(Location)} instead.
     *
     * @param chunkX Chunk x value
     * @param chunkZ Chunk z value
     * @return null, if the chunk isn't claimed or not loaded. If you want to get the land from an unloaded chunk, use {@link #getLandByUnloadedChunk(int, int)} instead.
     * However, this method should always prefered, if possible. Most bukkit events etc. usually have chunks already loaded
     */
    @Nullable
    Land getLandByChunk(int chunkX, int chunkZ);

    /**
     * Get land claimed land from a unloaded or loaded chunk. If you're using this method in an event handler or something else
     * where the chunk is loaded anyway, make sure to use {@link #getLandByChunk(int, int)} instead.
     * This shouldn't be used, if you want to check flag states. Use {@link #getArea(Location)} instead.
     * This method can be called async. It doesn't make any database calls, but requires a deep lookup in cached data.
     *
     * @param chunkX Chunk x value
     * @param chunkZ Chunk z value
     * @return null, if the chunk isn't claimed
     */
    @Nullable
    Land getLandByUnloadedChunk(int chunkX, int chunkZ);

    /**
     * Get an area at the specified coordinate, even if the chunk isn't loaded.
     * This method can be called async. It doesn't make any database calls, but requires a deep lookup in cached data.
     *
     * @param x Block x
     * @param y Block y
     * @param z Block z
     * @return null, if the target chunk isn't claimed
     */
    @Nullable Area getAreaByUnloaded(int x, int y, int z);

    /**
     * Get area at the specified coordinate.
     * If you need to retrieve an area that is located insice an unloaded chunk, use {@link #getAreaByUnloaded(int, int, int)} instead.
     * However, this method should always be prefered if possible.
     *
     * @param x Block x
     * @param y Block y
     * @param z Block z
     * @return null, if the target chunk isn't claimed or loaded
     */
    @Nullable
    Area getArea(int x, int y, int z);

    /**
     * Check if a player has a role flag set through their role.
     *
     * @param player      The player
     * @param location    The location of the action
     * @param flag        The flag to check
     * @param material    The blocks material. Useful for flags like {@link me.angeschossen.lands.api.flags.type.Flags#BLOCK_BREAK} etc.
     *                    This parameter is used if the land is engaged in a war and the server configured that in wars specific blocks are allowed to break etc.
     *                    For flags that don't include any blocks you can provide null here.
     * @param sendMessage If the player should get a message, if their role doesn't have this flag
     * @return false, if their role doesn't have this flag
     */
    boolean hasRoleFlag(@NotNull LandPlayer player, @NotNull Location location, @NotNull RoleFlag flag, @Nullable Material material, boolean sendMessage);

    /**
     * Use {@link #hasRoleFlag(LandPlayer, Location, RoleFlag, Material, boolean)} instead.
     * Check if a player has a role flag set through their role.
     *
     * @param player      The player
     * @param location    The location of the action
     * @param flag        The flag to check
     * @param material    The blocks material. Useful for flags like {@link me.angeschossen.lands.api.flags.type.Flags#BLOCK_BREAK} etc.
     *                    This parameter is used if the land is engaged in a war and the server configured that in wars specific blocks are allowed to break etc.
     *                    For flags that don't include any blocks you can provide null here.
     * @param sendMessage If the player should get a message, if their role doesn't have this flag
     * @return false, if their role doesn't have this flag
     */
    @Deprecated
    boolean hasRoleFlag(@NotNull Player player, @NotNull Location location, @NotNull RoleFlag flag, @Nullable Material material, boolean sendMessage);

    /**
     * Check if a player has a role flag enabled through their role.
     *
     * @param playerUID The player
     * @param location  The location of the action
     * @param flag      The flag
     * @return false, their role doesn't have this flag
     */
    boolean hasRoleFlag(@NotNull UUID playerUID, @NotNull Location location, @NotNull RoleFlag flag);

    /**
     * Use {@link #hasRoleFlag(LandPlayer, Location, RoleFlag, Material, boolean)} instead.
     *
     * @param player      The player
     * @param location    The location of the action
     * @param flag        The flag
     * @param material    The blocks material. Useful for flags like {@link me.angeschossen.lands.api.flags.type.Flags#BLOCK_BREAK} etc.
     *                    This parameter is used if the land is engaged in a war and the server configured that in wars specific blocks are allowed to break etc.
     *                    For flags that don't include any blocks you can provide null here.
     * @param sendMessage If the player should get a message, if their role doesn't have this flag
     * @return false, if their role doesn't have this flag
     */
    @Deprecated
    boolean hasFlag(@NotNull Player player, @NotNull Location location, @Nullable Material material, @NotNull me.angeschossen.lands.api.flags.types.RoleFlag flag, boolean sendMessage);

    /**
     * Check if area has a natural flag enabled.
     *
     * @param location The location to check
     * @param flag     The flag to check
     * @return false, if not set
     */
    boolean hasNaturalFlag(@NotNull Location location, @NotNull NaturalFlag flag);

    /**
     * Get name of world.
     *
     * @return Name of world
     */
    @NotNull
    String getName();

    /**
     * Get the Bukkit world.
     *
     * @return Bukkit world
     */
    @NotNull
    World getWorld();

    /**
     * Check if claimed chunk is loaded.
     *
     * @param x Chunk X identifier
     * @param z Chunk Z identifier
     * @return true, if loaded by Bukkit world
     */
    boolean isChunkLoaded(int x, int z);
}
