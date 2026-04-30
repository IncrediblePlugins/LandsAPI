package me.angeschossen.lands.api.limits;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.github.angeschossen.pluginframework.api.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Represents the permission nodes to limit certain aspects of the plugin.
 */
public class Limit extends com.github.angeschossen.pluginframework.api.limit.Limit {
    private static final Map<String, Limit> permissionToLimitMap = new HashMap<>();
    private static final Map<String, Limit> oldNameToLimitMap = new HashMap<>();

    /**
     * Limits the maximum amount of lands the player can own.
     */
    public static Limit PLAYER_LANDS_OWNED;
    /**
     * Amount of lands a player can create for free.
     */
    public static Limit PLAYER_LANDS_OWNED_FREE;
    /**
     * Amount of temporary camps a player can have at the same time.
     */
    public static Limit PLAYER_CAMPS_OWNED;
    /**
     * Max amount of chunks the player can select at once when creating a selection.
     */
    public static Limit PLAYER_SELECTION_SIZE;
    /**
     * Limits the maximum amount of chunks the player can claim for each land they own.
     */
    public static Limit LAND_SIZE;
    /**
     * Limits the maximum amount of trusted players the player can trust to each land they own.
     */
    public static Limit LAND_MEMBERS;
    /**
     * Limits the maximum amount of disconnected parts the player can claim for each land they own.
     */
    public static Limit LAND_PARTS;
    /**
     * Limits the maximum amount of sub areas for each land the player owns.
     */
    public static Limit LAND_AREAS;
    /**
     * Amount of roles a land owner can create for each area inside their land.
     */
    public static Limit AREA_ROLES;
    /**
     * Amount of allies a land owner can add to each land they own.
     */
    public static Limit LAND_ALLIES;
    /**
     * Amount of enemies a land owner can add to each land they own.
     */
    public static Limit LAND_ENEMIES;
    /**
     * Limits the maximum amount of lands the player can be trusted in. This does not include lands that the player owns.
     */
    public static Limit PLAYER_LANDS_TRUSTED;
    /**
     * Sets the amount of areas a player can rent in each land they're trusted in.
     */
    public static Limit PLAYER_RENTALS;
    /**
     * Total amount of chunks a player can claim across a lands (owned and trusted).
     */
    public static Limit PLAYER_CHUNKS;
    /**
     * Amount of chunks a player can claim for free across all lands (owned and trusted).
     */
    public static Limit PLAYER_CHUNKS_FREE;
    /**
     * Amount of lands a player can add to each nation they own.
     */
    public static Limit NATION_LANDS;

    private final @NotNull String permission;
    private final @Nullable String oldName;
    private LimitMode mode = LimitMode.PERMISSION;


    public Limit(@NotNull String id, @NotNull String permission, @Nullable String oldName, @NotNull LimitTarget... targets) {
        super(id, targets);

        this.permission = StringUtils.toLowerCase(Checks.requireNonNull(permission, "permission"));
        this.oldName = oldName;
    }

    public static Limit register(Limit limit) {
        com.github.angeschossen.pluginframework.api.limit.Limit.register(limit);

        permissionToLimitMap.put(limit.getPermission(), limit);

        final String oldName = limit.getOldName();
        if (oldName != null) {
            oldNameToLimitMap.put(oldName, limit);
        }

        return limit;
    }

    /**
     * Get a limit by its permission node.
     *
     * @param permission the permission node string
     * @return the matching limit, or {@code null} if none found
     */
    @Nullable
    public static Limit getByPermission(@NotNull String permission) {
        return Limit.permissionToLimitMap.get(StringUtils.toLowerCase(Checks.requireNonNull(permission, "permission")));
    }

    /**
     * Get a limit by its legacy name.
     *
     * @param oldName the legacy name of the limit
     * @return the matching limit, or {@code null} if none found
     */
    @Nullable
    public static Limit getByOldName(@NotNull String oldName) {
        return Limit.oldNameToLimitMap.get(StringUtils.toLowerCase(Checks.requireNonNull(oldName, "oldName")));
    }

    @Override
    public final boolean hasTarget(@NotNull com.github.angeschossen.pluginframework.api.limit.holder.LimitTarget target) {
        return target == LimitTarget.PLAYER || super.hasTarget(target); // some limits should only be saved to lands or nations, but if the target is player it should always be saved or merged since player should be point of truth
    }

    /**
     * Get the legacy name of the limit.
     *
     * @return legacy name
     */
    @Deprecated
    public @Nullable String getOldName() {
        return oldName;
    }

    /**
     * Get the permission node associated with the limit.
     *
     * @return never null
     */
    public @NotNull String getPermission() {
        return permission;
    }

    /**
     * Get the current mode that determines how this limit's value is resolved.
     *
     * @return the limit mode; never null
     */
    @NotNull
    public final LimitMode getMode() {
        return mode;
    }

    /**
     * Set the mode that determines how this limit's value is resolved.
     *
     * @param mode the new limit mode; must not be null
     */
    public final void setMode(@NotNull LimitMode mode) {
        this.mode = Objects.requireNonNull(mode);
    }
}
