package me.angeschossen.lands.api.limits;

import com.github.angeschossen.pluginframework.api.limit.Limit;
import com.github.angeschossen.pluginframework.api.limit.LimitModifier;
import com.github.angeschossen.pluginframework.api.limit.holder.LimitHolder;
import com.github.angeschossen.pluginframework.api.limit.holder.LimitTarget;
import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.github.angeschossen.pluginframework.api.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Enums represent the permission nodes to limit certain aspects of the plugin.
 */
public enum Limitation implements Limit {
    /**
     * Limits the maximum amount of lands the player can own.
     */
    OWN_LANDS("lands.ownlands", "ownlands", LimitationTarget.PLAYER),
    OWN_LANDS_FREE("lands.free.lands", null, LimitationTarget.PLAYER),
    OWN_CAMPS("lands.camps", null, LimitationTarget.PLAYER),
    /**
     * Limits the maximum amount of chunks the player can claim for each land they own.
     */
    LAND_CHUNKS("lands.chunks", "chunks", LimitationTarget.PLAYER, LimitationTarget.LAND),
    /**
     * Limits the maximum amount of trusted players the player can trust to each land they own.
     */
    LAND_MEMBERS("lands.members", "members", LimitationTarget.LAND),
    /**
     * Limits the maximum amount of disconnected parts the player can claim for each land they own.
     */
    LAND_PARTS("lands.parts", "parts", LimitationTarget.LAND),
    /**
     * Limits the maximum amount of sub areas for each land the player owns.
     */
    LAND_AREAS("lands.areas", "areas", LimitationTarget.LAND),
    LAND_ROLES("lands.roles", null, LimitationTarget.LAND),
    LAND_CHUNKS_FREE("lands.free.chunks", null, LimitationTarget.PLAYER),
    LAND_ALLIES("lands.allies", null, LimitationTarget.LAND, LimitationTarget.NATION),
    LAND_ENEMIES("lands.enemies", null, LimitationTarget.LAND, LimitationTarget.NATION),
    /**
     * Limits the maximum amount of lands the player can be trusted in. This does not include lands that the player owns.
     */
    TRUSTED_LANDS("lands.lands", "lands", LimitationTarget.PLAYER),
    /**
     * Sets the amount of areas a player can rent in each land they're trusted in.
     */
    RENTALS("lands.rentals", null, LimitationTarget.PLAYER),
    PLAYER_CHUNKS("lands.player.chunnks", null, LimitationTarget.PLAYER),
    NATION_LANDS("nations.lands", null, LimitationTarget.NATION);

    private final @NotNull String permission;
    private final @Nullable String oldName;
    private static final Map<String, Limitation> permissionToLimitMap = new HashMap<>();
    private static final Map<String, Limitation> oldNameToLimitMap = new HashMap<>();
    private LimitationMode mode = LimitationMode.DATABASE;
    private final Set<LimitTarget> targets;
    private final Map<String, LimitModifier> modifiers = new HashMap<>();

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
     */
    Limitation(@NotNull String permission, @Nullable String oldName, @NotNull LimitationTarget... targets) {
        this.permission = Checks.requireNonNull(permission, "permission");
        this.oldName = oldName;
        this.targets = Set.of(Checks.requireNonNull(targets, "targets"));
    }

    public final boolean hasTarget(@NotNull LimitTarget target) {
        return targets.contains(target);
    }

    @Override
    public int applyModifiers(@NotNull LimitHolder limitHolder, int limit) {
        for (LimitModifier modifier : modifiers.values()) {
            limit += modifier.getModifier(limitHolder);
        }

        return limit;
    }

    @Override
    public void registerModifier(@NotNull LimitModifier limitModifier) {
        modifiers.put(StringUtils.toLowerCase(limitModifier.getId()), limitModifier);
    }

    @Override
    public void unregisterModifier(@NotNull LimitModifier limitModifier) {
        modifiers.remove(StringUtils.toLowerCase(limitModifier.getId()));
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
    public final LimitationMode getMode() {
        return mode;
    }

    public final void setMode(@NotNull LimitationMode mode) {
        this.mode = Objects.requireNonNull(mode);
    }
}
