package me.angeschossen.lands.api.limits;

import com.github.angeschossen.pluginframework.api.limit.LimitModifier;
import com.github.angeschossen.pluginframework.api.limit.holder.LimitHolder;
import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.github.angeschossen.pluginframework.api.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Enums represent the permission nodes to limit certain aspects of the plugin.
 */
public enum Limit implements com.github.angeschossen.pluginframework.api.limit.Limit {
    /**
     * Limits the maximum amount of lands the player can own.
     */
    PLAYER_LANDS_OWNED("lands.ownlands", "ownlands", LimitTarget.PLAYER),
    /**
     * Amount of lands a player can create for free.
     */
    PLAYER_LANDS_OWNED_FREE("lands.free.lands", null, LimitTarget.PLAYER),
    /**
     * Amount of temporary camps a player can have at the same time.
     */
    PLAYER_CAMPS_OWNED("lands.camps", null, LimitTarget.PLAYER),
    /**
     * Limits the maximum amount of chunks the player can claim for each land they own.
     */
    LAND_CHUNKS("lands.chunks", "chunks", LimitTarget.PLAYER, LimitTarget.LAND),
    /**
     * Limits the maximum amount of trusted players the player can trust to each land they own.
     */
    LAND_MEMBERS("lands.members", "members", LimitTarget.LAND),
    /**
     * Limits the maximum amount of disconnected parts the player can claim for each land they own.
     */
    LAND_PARTS("lands.parts", "parts", LimitTarget.LAND),
    /**
     * Limits the maximum amount of sub areas for each land the player owns.
     */
    LAND_AREAS("lands.areas", "areas", LimitTarget.LAND),
    /**
     * Amount of roles a land owner can create for each area inside their land.
     */
    AREA_ROLES("lands.roles", null, LimitTarget.LAND),
    /**
     * Amount of allies a land owner can add to each land they own.
     */
    LAND_ALLIES("lands.allies", null, LimitTarget.LAND, LimitTarget.NATION),
    /**
     * Amount of enemies a land owner can add to each land they own.
     */
    LAND_ENEMIES("lands.enemies", null, LimitTarget.LAND, LimitTarget.NATION),
    /**
     * Limits the maximum amount of lands the player can be trusted in. This does not include lands that the player owns.
     */
    PLAYER_LANDS_TRUSTED("lands.lands", "lands", LimitTarget.PLAYER),
    /**
     * Sets the amount of areas a player can rent in each land they're trusted in.
     */
    PLAYER_RENTALS("lands.rentals", null, LimitTarget.PLAYER),
    /**
     * Total amount of chunks a player can claim across a lands (owned and trusted).
     */
    PLAYER_CHUNKS("lands.player.chunnks", null, LimitTarget.PLAYER),
    /**
     * Amount of chunks a player can claim for free across all lands (owned and trusted).
     */
    PLAYER_CHUNKS_FREE("lands.free.chunks", null, LimitTarget.PLAYER),
    /**
     * Amount of lands a player can add to each nation they own.
     */
    NATION_LANDS("nations.lands", null, LimitTarget.NATION);

    private final @NotNull String permission;
    private final @Nullable String oldName;
    private static final Map<String, Limit> permissionToLimitMap = new HashMap<>();
    private static final Map<String, Limit> oldNameToLimitMap = new HashMap<>();
    private LimitMode mode = LimitMode.PERMISSION;
    private final Set<com.github.angeschossen.pluginframework.api.limit.holder.LimitTarget> targets;
    private final Map<String, LimitModifier> modifiers = new HashMap<>();

    static {
        for (Limit value : Limit.values()) {
            String oldName = value.getOldName();
            if (oldName != null) {
                Limit.oldNameToLimitMap.put(StringUtils.toLowerCase(oldName), value);
            }

            Limit.permissionToLimitMap.put(StringUtils.toLowerCase(value.getPermission()), value);
        }
    }


    @Nullable
    public static Limit getByPermission(@NotNull String permission) {
        return Limit.permissionToLimitMap.get(StringUtils.toLowerCase(Checks.requireNonNull(permission, "permission")));
    }

    @Nullable
    public static Limit getByOldName(@NotNull String oldName) {
        return Limit.oldNameToLimitMap.get(StringUtils.toLowerCase(Checks.requireNonNull(oldName, "oldName")));
    }

    /**
     * Constructor
     *
     * @param permission permission node associated with the limit
     * @param oldName    previous name of the limit
     */
    Limit(@NotNull String permission, @Nullable String oldName, @NotNull LimitTarget... targets) {
        this.permission = Checks.requireNonNull(permission, "permission");
        this.oldName = oldName;
        this.targets = Set.of(Checks.requireNonNull(targets, "targets"));
    }

    public final boolean hasTarget(@NotNull com.github.angeschossen.pluginframework.api.limit.holder.LimitTarget target) {
        return targets.contains(target); // some limits should only be saved to lands or nations
    }

    @Override
    public int applyModifiers(@NotNull LimitHolder limitHolder, int limit) {
        for (LimitModifier modifier : modifiers.values()) {
            limit += modifier.getModifier(limitHolder);
        }

        return limit;
    }

    @Override
    public void registerModifier(@NotNull LimitModifier... limitModifiers) {
        for (LimitModifier modifier : limitModifiers) {
            modifiers.put(StringUtils.toLowerCase(modifier.getId()), modifier);
        }
    }

    @Override
    public void unregisterModifier(@NotNull LimitModifier... limitModifiers) {
        for (LimitModifier modifier : limitModifiers) {
            modifiers.remove(StringUtils.toLowerCase(modifier.getId()));
        }
    }

    @Override
    public @NotNull Collection<@NotNull LimitModifier> getModifiers() {
        return modifiers.values();
    }

    @Override
    public @NotNull String getId() {
        return toString();
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
     * Get the permission node asssociated with the limit.
     *
     * @return never null
     */
    public @NotNull String getPermission() {
        return permission;
    }

    @NotNull
    public final LimitMode getMode() {
        return mode;
    }

    public final void setMode(@NotNull LimitMode mode) {
        this.mode = Objects.requireNonNull(mode);
    }
}
