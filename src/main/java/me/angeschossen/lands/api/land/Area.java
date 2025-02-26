package me.angeschossen.lands.api.land;

import com.github.angeschossen.applicationframework.api.util.ULID;
import com.github.angeschossen.pluginframework.api.blockutil.impl.Position;
import com.github.angeschossen.pluginframework.api.events.ExpressionEntity;
import com.github.angeschossen.pluginframework.api.exceptions.PlayerUntrustedException;
import me.angeschossen.lands.api.flags.type.NaturalFlag;
import me.angeschossen.lands.api.flags.type.RoleFlag;
import me.angeschossen.lands.api.land.rental.offer.base.RentalOfferBase;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.player.invite.Invite;
import me.angeschossen.lands.api.role.Role;
import me.angeschossen.lands.api.role.RoleHolder;
import me.angeschossen.lands.api.role.system.SystemFlagStates;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

/**
 * This might be a default area ({@link Land#getDefaultArea()} or a sub area ({@link LandArea}).
 */
public interface Area extends ExpressionEntity, TaxHolder, SystemFlagStatesHolder, RoleHolder {
    /**
     * Get applied system flag states for player. See {@link SystemFlagStatesHolder#setSystemFlagStates(LandPlayer, SystemFlagStates)} for more info.
     *
     * @param player the player to look for
     * @return null, if none set or the player is trusted in this area
     */
    @Nullable
    SystemFlagStates getSystemFlagStates(@NotNull Player player);

    /**
     * Ban a player.
     *
     * @param uuid The player to be banned.
     * @return false, if was already banned
     */
    boolean banPlayer(@NotNull UUID uuid);

    /**
     * Check if two players can fight each other in this area. This also takes wars into account.
     *
     * @param attacker    the attacker
     * @param target      the target
     * @param sendMessage if true, sends denied message if they can't fight each other in this area
     * @return false, if they can't fight each other
     */
    boolean canPvP(@NotNull LandPlayer attacker, LandPlayer target, boolean sendMessage);

    /**
     * Check if a player can enter this area. This depends on {@link #isBanned(UUID)} and on the land enter flag state for the player's role.
     *
     * @param landPlayer  The player which wants to enter
     * @param sendMessage If true, it will send a enter faiure message to the player, if they're not allowed to enter
     * @return false, if the player isn't allowed to enter
     */
    boolean canEnter(@NotNull LandPlayer landPlayer, boolean sendMessage);

    /**
     * Set the spawn position of the area.
     *
     * @param position new position or reset to default
     */
    void setSpawn(@Nullable Position position);

    /**
     * Get the spawn position of the area.
     *
     * @return null, if area isn't setup yet or no spawn set manually ({@link #setSpawn(Position)}) for the default area.
     */
    @Nullable
    Position getSpawn();


    /**
     * Get the tenant.
     *
     * @return player which rented or bought this area. If null, no player rented or bought this area.
     * Will always be null for the default area of a land ({@link #isDefault()} = true).
     */
    @Nullable
    UUID getTenant();

    /**
     * Get the entry role.
     *
     * @return New players receive this role
     */
    @NotNull
    Role getEntryRole();

    /**
     * Get all natural flags.
     *
     * @return active natural flags
     */
    @NotNull
    Set<NaturalFlag> getNaturalFlags();

    /**
     * Get the numerical ID of this area.
     *
     * @return The ID is unique for the land it belongs to. Areas of other lands may have the same id. Areas of the same land have all different IDs.
     * @deprecated Use {@link #getULID()} instead
     */
    @Deprecated
    int getId();

    /**
     * Get invite that was sent to a specific player.
     *
     * @param receiverUUID The receiver
     * @return null, if the player was never invited, already accepted the invite or the invite expired.
     */
    @Nullable
    Invite getInvite(@NotNull UUID receiverUUID);

    /**
     * Get the land of this area.
     *
     * @return The land to which this area belongs
     */
    @NotNull
    Land getLand();

    /**
     * Get the name of this area. To include color codes, use {@link #getColorName()} instead.
     *
     * @return Name of the land without color codes
     */
    @NotNull
    String getName();

    /**
     * Get the owner of this area. Might differ to {@link Land#getOwnerUID()}, if the area is sold or rented.
     *
     * @return The current owner of this area
     */
    @NotNull
    UUID getOwnerUID();

    /**
     * Get the rolre of a player.
     *
     * @param playerUID The player
     * @return Visitor role, if the player is untrusted
     */
    @NotNull
    Role getRole(@NotNull UUID playerUID);

    /**
     * Get a role by its name.
     *
     * @param name Color codes are stripped for the lookup
     * @return null, if no role with this name found
     */
    @Nullable
    Role getRole(@NotNull String name);

    /**
     * Get the roles.
     *
     * @return Collection of all roles
     */
    @NotNull
    Collection<? extends Role> getRoles();

    /**
     * Get all trusted players.
     *
     * @return Collection of trusted players, including owner
     */
    @NotNull
    Collection<UUID> getTrustedPlayers();

    /**
     * Get the visitor role.
     *
     * @return Players which aren't trusted have this role automatically
     */
    @NotNull
    Role getVisitorRole();

    /**
     * Check if area has a natural flag enabled.
     *
     * @param flag The flag to check
     * @return false, if not set
     */
    boolean hasNaturalFlag(@NotNull NaturalFlag flag);

    /**
     * Check if a player has a role flag enabled through their role.
     *
     * @param playerUUID The player
     * @param flag       The flag
     * @return false, their role doesn't have this flag
     */
    boolean hasRoleFlag(@NotNull UUID playerUUID, @NotNull RoleFlag flag);

    /**
     * Use {@link #hasRoleFlag(LandPlayer, RoleFlag, Material, boolean)} instead.
     * Check if a player has a role flag set through their role.
     *
     * @param player      The player
     * @param flag        The flag to check
     * @param material    The blocks material. Useful for flags like {@link me.angeschossen.lands.api.flags.type.Flags#BLOCK_BREAK} etc.
     *                    This parameter is used if the land is engaged in a war and the server configured that in wars specific blocks are allowed to break etc.
     *                    For flags that don't include any blocks you can provide null here.
     * @param sendMessage If the player should get a message, if their role doesn't have this flag
     * @return false, if their role doesn't have this flag
     */
    @Deprecated
    boolean hasRoleFlag(@NotNull Player player, @NotNull RoleFlag flag, @Nullable Material material, boolean sendMessage);

    /**
     * Check if a player has a role flag set through their role.
     *
     * @param player      The player
     * @param flag        The flag to check
     * @param material    The blocks material. Useful for flags like {@link me.angeschossen.lands.api.flags.type.Flags#BLOCK_BREAK} etc.
     *                    This parameter is used if the land is engaged in a war and the server configured that in wars specific blocks are allowed to break etc.
     *                    For flags that don't include any blocks you can provide null here.
     * @param sendMessage If the player should get a message, if their role doesn't have this flag
     * @return false, if their role doesn't have this flag
     */
    boolean hasRoleFlag(@NotNull LandPlayer player, @NotNull RoleFlag flag, @Nullable Material material, boolean sendMessage);

    /**
     * Use {@link #hasRoleFlag(UUID, RoleFlag)} instead.
     *
     * @param playerUUID The player
     * @param flag       The flag
     * @return false, if their role doesn't have this flag
     */
    @Deprecated
    boolean hasFlag(@NotNull UUID playerUUID, @NotNull me.angeschossen.lands.api.flags.types.RoleFlag flag);

    /**
     * Use {@link #hasRoleFlag(LandPlayer, RoleFlag, Material, boolean)} instead.
     *
     * @param player      The player
     * @param flag        The flag
     * @param material    The blocks material. Useful for flags like {@link me.angeschossen.lands.api.flags.type.Flags#BLOCK_BREAK} etc.
     *                    This parameter is used if the land is engaged in a war and the server configured that in wars specific blocks are allowed to break etc.
     *                    For flags that don't include any blocks you can provide null here.
     * @param sendMessage If the player should get a message, if their role doesn't have this flag
     * @return false, if their role doesn't have this flag
     */
    @Deprecated
    boolean hasFlag(@NotNull Player player, @NotNull me.angeschossen.lands.api.flags.types.RoleFlag flag, @Nullable Material material, boolean sendMessage);

    /**
     * Use {@link #hasRoleFlag(LandPlayer, RoleFlag, Material, boolean)} instead.
     *
     * @param player      The player
     * @param roleFlag    The flag
     * @param sendMessage If the player should get a message, if their role doesn't have this flag
     * @return false, if their role doesn't have this flag
     */
    @Deprecated
    boolean hasFlag(@NotNull Player player, @NotNull me.angeschossen.lands.api.flags.types.RoleFlag roleFlag, boolean sendMessage);

    /**
     * Check if a player is banned.
     *
     * @param playerUID The player
     * @return false, if the player isn't banned
     */
    boolean isBanned(@NotNull UUID playerUID);

    /**
     * Check if this area is the defeault area ({@link Land#getDefaultArea()}.
     *
     * @return true, if this area is the default area
     */
    boolean isDefault();

    /**
     * Check if a player is trusted.
     *
     * @param playerUID The player
     * @return true, if the player is trusted
     */
    boolean isTrusted(@NotNull UUID playerUID);


    /**
     * Untrust a player from this area.
     *
     * @param playerUID The player to untrust
     * @return true, if the player was previously trusted
     * @throws IllegalArgumentException If the target player owns this area.
     *                                  Use {@link Land#setOwner(UUID)} to set a new owner instead.
     */
    boolean untrustPlayer(@NotNull UUID playerUID) throws IllegalArgumentException;

    /**
     * Trust a player to this area. If executed for a player that is already trusted,
     * this will set the entry role ({@link me.angeschossen.lands.api.role.enums.RoleType#ENTRY}) for this player.
     * Using this method will also automatically unban the player from this area
     * and remove the pending received invite for this area.
     *
     * @param playerUID The player to trust
     * @return true, if the player was previously untrusted
     * @throws IllegalArgumentException If the target player is the owner of this area
     */
    boolean trustPlayer(@NotNull UUID playerUID) throws IllegalArgumentException;

    /**
     * Get the globally unique ID. This ID is unique across all lands and servers.
     *
     * @return Universally unique lexicographically sortable identifier
     */
    @NotNull
    ULID getULID();

    /**
     * Get the current rental or purchase offer.
     *
     * @return null, if area isn't setup to be rented or sold
     */
    @Nullable
    RentalOfferBase getRentalOffer();

    /**
     * Set role for a trusted player.
     *
     * @param targetUID The trusted player
     * @param role      The role to set
     * @throws IllegalArgumentException If the role doesn't exist in this area, the role is of type {@link me.angeschossen.lands.api.role.enums.RoleType#VISITOR} or the current role of this player is of type {@link me.angeschossen.lands.api.role.enums.RoleType#OWNER}.
     *                                  If you want to untrust a player, use {@link #untrustPlayer(UUID)}
     * @throws PlayerUntrustedException If the player isn't trusted
     */
    void setRole(@NotNull UUID targetUID, @NotNull Role role) throws IllegalArgumentException, PlayerUntrustedException;

    /**
     * Toggle a natural flag.
     *
     * @param flag The flag
     * @return The new flag state
     */
    boolean toggleNaturalFlag(@NotNull NaturalFlag flag);

    /**
     * Unban a player.
     *
     * @param playerUID The player to be unbanned
     */
    void unbanPlayer(@NotNull UUID playerUID);
}
