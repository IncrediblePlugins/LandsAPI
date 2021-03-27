package me.angeschossen.lands.api.flags.types;

import me.angeschossen.lands.api.flags.Flag;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class RoleFlag extends Flag {


    public RoleFlag(@NotNull Plugin plugin, @NotNull Category category, @NotNull String name, boolean applyInSubAreas, boolean alwaysAllowInWilderness) {
        super(plugin, name, applyInSubAreas, alwaysAllowInWilderness);

    }

    public RoleFlag(@NotNull Plugin plugin, @NotNull Category category, @NotNull String name, boolean applyInSubAreas) {
        this(plugin, category, name, applyInSubAreas, false);
    }

    public RoleFlag(@NotNull Plugin plugin, @NotNull Category category, @NotNull String name) {
        this(plugin, category, name, true, false);
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