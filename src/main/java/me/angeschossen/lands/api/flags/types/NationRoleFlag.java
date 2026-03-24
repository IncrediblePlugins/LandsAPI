package me.angeschossen.lands.api.flags.types;

import me.angeschossen.lands.api.flags.enums.FlagModule;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * No longer needed.
 */
@Deprecated
public class NationRoleFlag extends RoleFlag {

    /**
     * Create a new nation-scoped role flag.
     *
     * @param plugin the plugin registering this flag
     * @param name   the unique name of the flag
     */
    public NationRoleFlag(@NotNull Plugin plugin, @NotNull String name) {
        super(plugin, Category.MANAGEMENT, name, false, true);
    }

    @Override
    public final @NotNull String getTogglePerm() {
        return "nations.setting." + name;
    }

    @Override
    public final @NotNull String getBypassPerm() {
        return "nations.bypass." + name;
    }

    @Override
    public final @NotNull String getBypassPermWild() {
        return "nations.bypass.wilderness." + name;
    }

    @Override
    @NotNull
    public final FlagModule getModule() {
        return FlagModule.NATION;
    }
}
