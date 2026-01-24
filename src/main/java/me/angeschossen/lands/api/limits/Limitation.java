package me.angeschossen.lands.api.limits;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.github.angeschossen.pluginframework.api.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Enums represent the permission nodes to limit certain aspects of the plugin.
 */
public enum Limitation implements com.github.angeschossen.pluginframework.api.limits.Limitation {
    /**
     * Limits the maximum amount of lands the player can own.
     */
    OWN_LANDS("lands.ownlands", "ownlands", false, LimitationTarget.PLAYER),
    /**
     * Limits the maximum amount of chunks the player can claim for each land they own.
     */
    LAND_CHUNKS("lands.chunks", "chunks", true, LimitationTarget.PLAYER, LimitationTarget.LAND) {
        @Override
        public boolean isLandRelated() {
            return getMode() == LimitationMode.PERMISSION;
        }
    },
    /**
     * Limits the maximum amount of trusted players the player can trust to each land they own.
     */
    LAND_MEMBERS("lands.members", "members", true, LimitationTarget.LAND),
    /**
     * Limits the maximum amount of disconnected parts the player can claim for each land they own.
     */
    LAND_PARTS("lands.parts", "parts", true, LimitationTarget.LAND),
    /**
     * Limits the maximum amount of sub areas for each land the player owns.
     */
    LAND_AREAS("lands.areas", "areas", true, LimitationTarget.LAND),
    /**
     * Limits the maximum amount of lands the player can be trusted in. This does not include lands that the player owns.
     */
    TRUSTED_LANDS("lands.lands", "lands", false, LimitationTarget.PLAYER),
    /**
     * Sets the amount of chunks that the player contributes to each land they are trusted to.
     */
    SUPPORT_CHUNKS("lands.chunks.support", null, false, LimitationTarget.PLAYER),
    /**
     * Sets the amount of areas a player can rent in each land they're trusted in.
     */
    RENTALS("lands.rentals", null, false, LimitationTarget.PLAYER),
    LAND_ROLES("lands.roles", null, true, LimitationTarget.LAND),
    LAND_CHUNKS_FREE("lands.free.chunks", null, false, LimitationTarget.PLAYER),
    OWN_LANDS_FREE("lands.free.lands", null, false, LimitationTarget.PLAYER),
    OWN_CAMPS("lands.camps", null, false, LimitationTarget.PLAYER),
    LAND_ALLIES("lands.allies", null, false, LimitationTarget.LAND, LimitationTarget.NATION),
    LAND_ENEMIES("lands.enemies", null, false, LimitationTarget.LAND, LimitationTarget.NATION),
    NATION_LANDS("nations.lands", null, false, LimitationTarget.NATION);

    private final @NotNull String permission;
    private final @Nullable String oldName;
    private final boolean isLand;
    private static final Map<String, Limitation> permissionToLimitMap = new HashMap<>();
    private static final Map<String, Limitation> oldNameToLimitMap = new HashMap<>();
    private LimitationMode mode = LimitationMode.PERMISSION;
    private final Set<com.github.angeschossen.pluginframework.api.limits.LimitationTarget> targets;

    static {
        for (Limitation value : Limitation.values()) {
            String oldName = value.getOldName();
            if (oldName != null) {
                Limitation.oldNameToLimitMap.put(StringUtils.toLowerCase(oldName), value);
            }

            Limitation.permissionToLimitMap.put(StringUtils.toLowerCase(value.getPermission()), value);
        }
    }


    @Nullable
    public static Limitation getByPermission(@NotNull String permission) {
        return Limitation.permissionToLimitMap.get(StringUtils.toLowerCase(Checks.requireNonNull(permission, "permission")));
    }

    @Nullable
    public static Limitation getByOldName(@NotNull String oldName) {
        return Limitation.oldNameToLimitMap.get(StringUtils.toLowerCase(Checks.requireNonNull(oldName, "oldName")));
    }

    /**
     * Constructor
     *
     * @param permission permission node associated with the limit
     * @param oldName    previous name of the limit
     * @param isLand     if the limit can be related to specific lands
     */
    Limitation(@NotNull String permission, @Nullable String oldName, boolean isLand, @NotNull LimitationTarget... targets) {
        this.permission = Checks.requireNonNull(permission, "permission");
        this.isLand = isLand;
        this.oldName = oldName;
        this.targets = Set.of(Checks.requireNonNull(targets, "targets"));
    }

    public final boolean hasTarget(@NotNull com.github.angeschossen.pluginframework.api.limits.LimitationTarget target) {
        return targets.contains(target);
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
     * Check if the limit can be related to specific lands.
     *
     * @return false, if the limit is to be associated with a specific player and not a specific land.
     */
    public boolean isLandRelated() {
        return isLand;
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
    public final LimitationMode getMode() {
        return mode;
    }

    public final void setMode(@NotNull LimitationMode mode) {
        this.mode = Objects.requireNonNull(mode);
    }
}
