package me.angeschossen.lands.api.player;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import org.jetbrains.annotations.NotNull;

/**
 * Enums represent the permission nodes to limit certain aspects of the plugin.
 */
public enum PlayerLimit {
    /**
     * Limits the maximum amount of lands the player can own.
     */
    OWN_LANDS("lands.ownlands", false),
    /**
     * Limits the maximum amount of chunks the player can claim for each land they own.
     */
    LAND_CHUNKS("lands.chunks", true),
    /**
     * Limits the maximum amount of trusted players the player can trust to each land they own.
     */
    LAND_MEMBERS("lands.members", true),
    /**
     * Limits the maximum amount of disconnected parts the player can claim for each land they own.
     */
    LAND_PARTS("lands.parts", true),
    /**
     * Limits the maximum amount of sub areas for each land the player owns.
     */
    LAND_AREAS("lands.areas", true),
    /**
     * Limits the maximum amount of lands the player can be trusted in. This does not include lands that the player owns.
     */
    TRUSTED_LANDS("lands.lands", false),
    /**
     * Sets the amount of chunks that the player contributes to each land they are trusted to.
     */
    SUPPORT_CHUNKS("lands.chunks.support", false),
    /**
     * Sets the amount of areas a player can rent in each land they're trusted in.
     */
    RENTALS("lands.rentals", false);

    private final @NotNull String permission;
    private final boolean isLand;

    /**
     * Constructor
     *
     * @param permission permission node associated with the limit
     * @param isLand     if the limit can be related to specific lands
     */
    PlayerLimit(@NotNull String permission, boolean isLand) {
        this.permission = Checks.requireNonNull(permission, "permission");
        this.isLand = isLand;
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
}
