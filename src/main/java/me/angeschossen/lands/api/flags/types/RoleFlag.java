package me.angeschossen.lands.api.flags.types;

import me.angeschossen.lands.api.flags.Flag;
import me.angeschossen.lands.api.role.Role;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class RoleFlag extends Flag {

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
     * @param predicate               You can specify to which roles this flag should be applied for already existing lands. The role will be null if the target is the wilderness.
     */
    public RoleFlag(@NotNull Plugin plugin, @NotNull Category category, @NotNull String name, boolean applyInSubAreas, boolean alwaysAllowInWilderness, @NotNull Predicate<Role> predicate) {
        super(plugin, name, applyInSubAreas, alwaysAllowInWilderness);
    }

    public RoleFlag(@NotNull Plugin plugin, @NotNull Category category, @NotNull String name, boolean applyInSubAreas, boolean alwaysAllowInWilderness) {
        this(plugin, category, name, applyInSubAreas, alwaysAllowInWilderness, role -> true);
    }

    public RoleFlag(@NotNull Plugin plugin, @NotNull Category category, @NotNull String name, boolean applyInSubAreas) {
        this(plugin, category, name, applyInSubAreas, false);
    }

    public RoleFlag(@NotNull Plugin plugin, @NotNull Category category, @NotNull String name) {
        this(plugin, category, name, true, false);
    }

    @NotNull
    public Predicate<Role> getPredicate() {
        return null;
    }


    @Override
    public @NotNull String getTogglePerm() {
        return null;
    }

    @Override
    public @NotNull Type getType() {
        return Type.ROLE;
    }

    @NotNull
    public String getBypassPerm() {
        return null;
    }

    @NotNull
    @Override
    public Module getModule() {
        return Module.LAND;
    }

    @NotNull
    public String getBypassPermWild() {
        return null;
    }

    @NotNull
    public Category getCategory() {
        return null;
    }

    public enum Category {
        ACTION, MANAGEMENT
    }
}
