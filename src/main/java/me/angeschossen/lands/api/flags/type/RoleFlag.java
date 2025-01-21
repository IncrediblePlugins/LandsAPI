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
     * Create an flag instance.
     * @param landsIntegration the integration
     * @param flagTarget the target
     * @param category category of the flag
     * @param name unique name of the flag
     * @return created instance of this flag
     */
    @NotNull
    static RoleFlag of(@NotNull LandsIntegration landsIntegration, @NotNull FlagTarget flagTarget, @NotNull RoleFlagCategory category, @NotNull String name) {
      return APIHandler.getFlagFactory().roleFlagOf(landsIntegration, flagTarget, category, name);
    }

    /**
     * When this flag is new on this server, specify whether or not a flag should be applied to roles of existing lands.
     * @param predicate if you return true, this flag is enabled for the role
     * @return instance of this flag
     */
    @NotNull RoleFlag setUpdatePredicate(@NotNull Predicate<Role> predicate);

    /**
     * Check whether or not this flag is toggleable by the nation of the land.
     * This only applied to the nation role.
     * @return true, if this flag is only toggleable by the nation.
     */
    boolean isToggleableByNation();

    /**
     * Set whether or not this flag should be toggleable by the nation for the nation role.
     * @param toggleable If true, nations can decide if player's of the nation have this flag in all lands of the nation. The lands itself won't be able the change the state for this flag.
     * @return instance of this flag
     */
    @NotNull RoleFlag setToggleableByNation(boolean toggleable);

    /**
     * Send message to the player that makes clear that they're not allowed to execute the action related to this flag.
     * @param landPlayer the receiver
     * @param area if null, the action affets the whole land
     */
    void sendDenied(@NotNull LandPlayer landPlayer, @Nullable Area area);

    /**
     * Send message to the player that makes clear that they're not allowed to execute an action related to this flag in the war.
     * @param landPlayer the receiver
     * @param land If null, this action affects wars in general and not a specific land that is in war.
     */
    void sendDeniedInWar(@NotNull LandPlayer landPlayer, @Nullable Land land);

    /**
     * Get update predicate.
     * @return predicate that defines, if this flag should be applied to an role of existing lands, when this flag is available for the first time on the server.
     */
    @NotNull Predicate<Role> getUpdatePredicate();

    /**
     * Get permission that allows players to bypass restrictions that this flag imposes, like breaking blocks ({@link Flags#BLOCK_BREAK}) etc.
     * To get the bypass permission for the wilderness, use {@link #getBypassPermissionWilderness()}.
     * @return bypass permission that allows players to bypass this flag
     */
    @NotNull String getBypassPermission();

    /**
     * Get permission that allows players to bypass restrictions that this flag imposes in the wilderness, like breaking blocks ({@link Flags#BLOCK_BREAK}) etc.
     * @return bypass permission that allows players to bypass this flag in the wilderness
     */
    @NotNull String getBypassPermissionWilderness();

    /**
     * Get the category of this flag.
     * @return never null
     */
    @NotNull RoleFlagCategory getCategory();
}
