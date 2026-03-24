package me.angeschossen.lands.api.flags.types;

import me.angeschossen.lands.api.flags.DefaultStateFlag;
import me.angeschossen.lands.api.flags.enums.FlagModule;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Use {@link me.angeschossen.lands.api.flags.type.PlayerFlag} instead.
 */
@Deprecated
public class PlayerFlag extends DefaultStateFlag<me.angeschossen.lands.api.flags.type.PlayerFlag> implements me.angeschossen.lands.api.flags.type.PlayerFlag {

    /**
     * Create a new personal player flag.
     *
     * @param plugin your plugin
     * @param name   the flag name
     */
    public PlayerFlag(@NotNull Plugin plugin, @NotNull String name) {
        super(plugin, Target.PLAYER, name, true, false);
    }

    @Override
    public @NotNull FlagModule getModule() {
        return FlagModule.PLAYER;
    }

    @Override
    protected PlayerFlag self() {
        return this;
    }

    /**
     * Look up an existing player flag by name and wrap it as a {@link PlayerFlag}.
     *
     * @param name the flag name
     * @return the wrapped flag, never null
     * @throws NullPointerException if no flag with the given name exists
     */
    public static PlayerFlag of(String name) {
        me.angeschossen.lands.api.flags.type.PlayerFlag flag = Objects.requireNonNull(APIHandler.getFlagRegistry().getPlayer(name), "legacy flag: " + name);
        return new PlayerFlag(flag.getPlugin(), flag.getName());
    }

    @Override
    public @NotNull String getTogglePerm() {
        return "lands.player.setting." + name;
    }

    @Override
    public boolean shouldDisplay(@Nullable Area area, @Nullable LandPlayer landPlayer) {
        return false;
    }

    @Override
    public @NotNull String getTogglePermission() {
        return getTogglePerm();
    }
}
