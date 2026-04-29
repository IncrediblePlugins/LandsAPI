package me.angeschossen.lands.api.flags.type;

import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.flags.enums.FlagTarget;
import me.angeschossen.lands.api.flags.enums.RoleFlagCategory;
import me.angeschossen.lands.api.flags.type.parent.DefaultStateFlag;
import me.angeschossen.lands.api.flags.type.parent.Flag;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.role.Role;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

/**
 * Role flags are used to control what player's which belong to a role, can or can't do.
 */
public interface RoleFlag extends Flag<RoleFlag>, DefaultStateFlag<RoleFlag> {

    /**
     * Create a new role flag instance.
     *
     * @param landsIntegration the integration that owns this flag
     * @param flagTarget       the audience that can toggle this flag
     * @param category         the category of this flag
     * @param name             unique name of the flag
     * @return the created flag instance
     */
    @NotNull
    static RoleFlag of(@NotNull LandsIntegration landsIntegration, @NotNull FlagTarget flagTarget, @NotNull RoleFlagCategory category, @NotNull String name) {
      return APIHandler.getFlagFactory().roleFlagOf(landsIntegration, flagTarget, category, name);
    }

    /**
     * Specifies which existing roles should have this flag enabled when it is first encountered on the server.
     * The predicate is evaluated once per role of every existing land at startup.
     *
     * @param predicate return {@code true} to enable the flag for the given role
     * @return this flag instance
     */
    @NotNull RoleFlag setUpdatePredicate(@NotNull Predicate<Role> predicate);

    /**
     * Check whether this flag can only be toggled by the nation that owns the land, rather than by the land itself.
     * Only applies to the nation role.
     *
     * @return true if only the nation can toggle this flag for the nation role
     */
    boolean isToggleableByNation();

    /**
     * Set whether this flag should only be toggleable by the nation for the nation role.
     * When {@code true}, the nation controls whether its members have this flag across all of its lands;
     * individual lands cannot override the state.
     *
     * @param toggleable {@code true} to restrict toggling to the nation
     * @return this flag instance
     */
    @NotNull RoleFlag setToggleableByNation(boolean toggleable);

    /**
     * Send the player a message explaining that they are not allowed to perform the action governed by this flag.
     *
     * @param landPlayer the player to notify
     * @param area       the specific area where the action was attempted, or {@code null} if the restriction applies to the whole land
     */
    void sendDenied(@NotNull LandPlayer landPlayer, @Nullable Area area);

    /**
     * Send the player a message explaining that they are not allowed to perform the action governed by this flag during a war.
     *
     * @param landPlayer the player to notify
     * @param land       the land currently in war, or {@code null} if the restriction applies to wars in general
     */
    void sendDeniedInWar(@NotNull LandPlayer landPlayer, @Nullable Land land);

    /**
     * Get the predicate that determines which existing roles should have this flag enabled on first startup.
     *
     * @return predicate applied to each role; never null
     */
    @NotNull Predicate<Role> getUpdatePredicate();

    /**
     * Get the permission node that allows players to bypass restrictions imposed by this flag (e.g. breaking blocks despite {@link Flags#BLOCK_BREAK} being disabled).
     * For the equivalent wilderness bypass, use {@link #getBypassPermissionWilderness()}.
     *
     * @return the bypass permission node; never null
     */
    @NotNull String getBypassPermission();

    /**
     * Get the permission node that allows players to bypass restrictions imposed by this flag in the wilderness.
     *
     * @return the wilderness bypass permission node; never null
     */
    @NotNull String getBypassPermissionWilderness();

    /**
     * Get the category of this flag.
     *
     * @return never null
     */
    @NotNull RoleFlagCategory getCategory();
}
