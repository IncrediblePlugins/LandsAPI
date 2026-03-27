package me.angeschossen.lands.api.flags.types;

import me.angeschossen.lands.api.flags.DefaultStateFlag;
import me.angeschossen.lands.api.flags.Flag;
import me.angeschossen.lands.api.flags.enums.FlagModule;
import me.angeschossen.lands.api.flags.enums.RoleFlagCategory;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.role.Role;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Use {@link RoleFlag} instead.
 */
@Deprecated
public class RoleFlag extends DefaultStateFlag<me.angeschossen.lands.api.flags.type.RoleFlag> implements me.angeschossen.lands.api.flags.type.RoleFlag {

    /** The category of this flag (ACTION or MANAGEMENT). */
    protected final RoleFlagCategory category;
    private Predicate<@Nullable Role> predicate;
    private boolean toggleableByNation = false;

    /**
     * This flag needs to be used for actions that involve players.
     *
     * @param plugin                  Your plugin.
     * @param category                There are two categories of RoleFlags:
     *                                ACTION: This should be used for physical actions, like block breaking etc.
     *                                MANAGEMENT: This should be used for administrational actions, like trusting players etc.
     * @param name                    Name of the flag.
     * @param applyInSubAreas         Should this flag also be available in sub areas, not just the land in general?
     * @param alwaysAllowInWilderness Should this flag always be true in wilderness?
     * @param predicate               You can specify to which roles this flag should be applied for already existing lands. The role will be null if the target is wilderness.
     * @param target                  Only admin lands or all lands.
     */
    public RoleFlag(@NotNull Plugin plugin, @NotNull Flag.Target target, @NotNull Category category, @NotNull String name, boolean applyInSubAreas, boolean alwaysAllowInWilderness, @NotNull Predicate<Role> predicate) {
        super(plugin, target, name, applyInSubAreas, alwaysAllowInWilderness);

        this.category = RoleFlagCategory.valueOf(category.toString());
        this.predicate = predicate;
    }

    /**
     * @deprecated Use {@link #RoleFlag(Plugin, Flag.Target, Category, String, boolean, boolean, Predicate)} instead.
     * @param plugin                  your plugin
     * @param category                the flag category
     * @param name                    the flag name
     * @param applyInSubAreas         whether the flag applies in sub-areas
     * @param alwaysAllowInWilderness whether this flag is always allowed in wilderness
     * @param predicate               predicate used to determine which roles receive this flag by default
     */
    @Deprecated
    public RoleFlag(@NotNull Plugin plugin, @NotNull Category category, @NotNull String name, boolean applyInSubAreas, boolean alwaysAllowInWilderness, @NotNull Predicate<Role> predicate) {
        super(plugin, Target.PLAYER, name, applyInSubAreas, alwaysAllowInWilderness);

        this.category = RoleFlagCategory.valueOf(category.toString());
        this.predicate = predicate;
    }

    /**
     * Look up an existing role flag by name and wrap it as a {@link RoleFlag}.
     *
     * @param name the flag name
     * @return the wrapped flag, never null
     * @throws NullPointerException if no flag with the given name exists
     */
    public static RoleFlag of(String name) {
        me.angeschossen.lands.api.flags.type.RoleFlag flag = Objects.requireNonNull(APIHandler.getFlagRegistry().getRole(name), "legacy flag: " + name);
        return new RoleFlag(flag.getPlugin(), Flag.Target.valueOf(flag.getTarget().toString()), Category.valueOf(flag.getCategory().toString()), flag.getName(), flag.isApplyInSubareas(), flag.isAlwaysAllowInWilderness(), flag.getUpdatePredicate());
    }

    /**
     * Create a role flag that applies to player lands.
     *
     * @param plugin                  your plugin
     * @param category                the flag category
     * @param name                    the flag name
     * @param applyInSubAreas         whether the flag applies in sub-areas
     * @param alwaysAllowInWilderness whether this flag is always allowed in wilderness
     */
    public RoleFlag(@NotNull Plugin plugin, @NotNull Category category, @NotNull String name, boolean applyInSubAreas, boolean alwaysAllowInWilderness) {
        this(plugin, Target.PLAYER, category, name, applyInSubAreas, alwaysAllowInWilderness, role -> true);
    }

    /**
     * Create a role flag that applies to player lands and all sub-areas, with wilderness always allowed.
     *
     * @param plugin   your plugin
     * @param category the flag category
     * @param name     the flag name
     */
    public RoleFlag(@NotNull Plugin plugin, @NotNull Category category, @NotNull String name) {
        this(plugin, Target.PLAYER, category, name, true, false, role -> true);
    }

    @Override
    public me.angeschossen.lands.api.flags.type.@NotNull RoleFlag setUpdatePredicate(@NotNull Predicate<Role> predicate) {
        this.predicate = predicate;
        return self();
    }

    public boolean isToggleableByNation() {
        return toggleableByNation;
    }

    public @NotNull RoleFlag setToggleableByNation(boolean toggleable) {
        this.toggleableByNation = toggleable;
        return this;
    }

    @Override
    public void sendDenied(@NotNull LandPlayer landPlayer, @Nullable Area area) {

    }

    @Override
    public void sendDeniedInWar(@NotNull LandPlayer landPlayer, @Nullable Land land) {

    }

    /**
     * Get the predicate used to determine which roles should have this flag enabled by default.
     *
     * @return never null
     */
    @NotNull
    public Predicate<Role> getPredicate() {
        return predicate;
    }

    @Override
    protected me.angeschossen.lands.api.flags.type.RoleFlag self() {
        return this;
    }

    @Override
    public @NotNull String getTogglePerm() {
        return "lands.role.setting." + name;
    }


    @Override
    public boolean shouldDisplay(@Nullable Area area, @Nullable LandPlayer landPlayer) {
        return false;
    }

    @Override
    public @NotNull String getTogglePermission() {
        return getTogglePerm();
    }

    @Override
    public @NotNull Predicate<Role> getUpdatePredicate() {
        return predicate;
    }

    @Override
    public @NotNull String getBypassPermission() {
        return getBypassPerm();
    }

    @Override
    public @NotNull String getBypassPermissionWilderness() {
        return "lands.bypass.wilderness." + name;
    }

    /**
     * Get the permission node that allows a player to bypass this flag.
     *
     * @return bypass permission node
     */
    @NotNull
    public String getBypassPerm() {
        return "lands.bypass." + name;
    }

    @NotNull
    @Override
    public FlagModule getModule() {
        return FlagModule.LAND;
    }

    /**
     * @deprecated Use {@link #getBypassPermissionWilderness()} instead.
     * @return wilderness bypass permission node
     */
    @Deprecated
    @NotNull
    public String getBypassPermWild() {
        return getBypassPermissionWilderness();
    }

    @NotNull
    public RoleFlagCategory getCategory() {
        return category;
    }

    /**
     * The category of a role flag, determining whether it is used for physical actions or administrative management.
     */
    public enum Category {
        /** Physical actions such as block breaking, placing, etc. */
        ACTION,
        /** Administrative actions such as trusting players, managing roles, etc. */
        MANAGEMENT
    }
}
