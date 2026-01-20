package me.angeschossen.lands.api.player;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.github.angeschossen.pluginframework.api.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Enums represent the permission nodes to limit certain aspects of the plugin.
 */
public enum PlayerLimit {
    /**
     * Limits the maximum amount of lands the player can own.
     */
    OWN_LANDS("lands.ownlands", "ownlands", false),
    /**
     * Limits the maximum amount of chunks the player can claim for each land they own.
     */
    LAND_CHUNKS("lands.chunks", "chunks", true) {
        @Override
        public boolean isLandRelated() {
            return getMode() == PlayerLimitMode.PERMISSION;
        }
    },
    /**
     * Limits the maximum amount of trusted players the player can trust to each land they own.
     */
    LAND_MEMBERS("lands.members", "members", true),
    /**
     * Limits the maximum amount of disconnected parts the player can claim for each land they own.
     */
    LAND_PARTS("lands.parts", "parts", true),
    /**
     * Limits the maximum amount of sub areas for each land the player owns.
     */
    LAND_AREAS("lands.areas", "areas", true),
    /**
     * Limits the maximum amount of lands the player can be trusted in. This does not include lands that the player owns.
     */
    TRUSTED_LANDS("lands.lands", "lands", false),
    /**
     * Sets the amount of chunks that the player contributes to each land they are trusted to.
     */
    SUPPORT_CHUNKS("lands.chunks.support", null, false),
    /**
     * Sets the amount of areas a player can rent in each land they're trusted in.
     */
    RENTALS("lands.rentals", null, false);

    private final @NotNull String permission;
    private final @Nullable String oldName;
    private final boolean isLand;
    private static Map<String, PlayerLimit> permissionToLimitMap = new HashMap<>(), oldNameToLimitMap = new HashMap<>();
    private PlayerLimitMode mode = PlayerLimitMode.PERMISSION;

    static {
        for (PlayerLimit value : PlayerLimit.values()) {
            String oldName = value.getOldName();
            if (oldName != null) {
                PlayerLimit.oldNameToLimitMap.put(StringUtils.toLowerCase(oldName), value);
            }

            PlayerLimit.permissionToLimitMap.put(StringUtils.toLowerCase(value.getPermission()), value);
        }
    }

    @Nullable
    public static PlayerLimit getByPermission(@NotNull String permission) {
        return PlayerLimit.permissionToLimitMap.get(StringUtils.toLowerCase(Checks.requireNonNull(permission, "permission")));
    }

    @Nullable
    public static PlayerLimit getByOldName(@NotNull String oldName) {
        return PlayerLimit.oldNameToLimitMap.get(StringUtils.toLowerCase(Checks.requireNonNull(oldName, "oldName")));
    }

    /**
     * Constructor
     *
     * @param permission permission node associated with the limit
     * @param oldName    previous name of the limit
     * @param isLand     if the limit can be related to specific lands
     */
    PlayerLimit(@NotNull String permission, @Nullable String oldName, boolean isLand) {
        this.permission = Checks.requireNonNull(permission, "permission");
        this.isLand = isLand;
        this.oldName = oldName;
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
    public final PlayerLimitMode getMode() {
        return mode;
    }

    public final void setMode(@NotNull PlayerLimitMode mode) {
        this.mode = Objects.requireNonNull(mode);
    }
}
