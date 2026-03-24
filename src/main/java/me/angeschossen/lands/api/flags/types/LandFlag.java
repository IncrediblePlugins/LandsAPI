package me.angeschossen.lands.api.flags.types;

import me.angeschossen.lands.api.flags.DefaultStateFlag;
import me.angeschossen.lands.api.flags.Flag;
import me.angeschossen.lands.api.flags.enums.FlagModule;
import me.angeschossen.lands.api.flags.type.NaturalFlag;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Use {@link NaturalFlag} instead.
 */
@Deprecated
public class LandFlag extends DefaultStateFlag<NaturalFlag> implements NaturalFlag {

    @Override
    protected LandFlag self() {
        return this;
    }

    /**
     * Create a new natural flag.
     *
     * @param plugin                  Your plugin.
     * @param target                  Only admin lands or all lands.
     * @param name                    The name of the flag.
     * @param applyInSubAreas         Should this flag also be available in sub areas, not just the land in general?
     * @param alwaysAllowInWilderness Should this flag always be true in wilderness?
     */
    public LandFlag(@NotNull Plugin plugin, @NotNull Target target, @NotNull String name, boolean applyInSubAreas, boolean alwaysAllowInWilderness) {
        super(plugin, target, name, applyInSubAreas, alwaysAllowInWilderness);
    }

    /**
     * Look up an existing natural flag by name and wrap it as a {@link LandFlag}.
     *
     * @param name the flag name
     * @return the wrapped flag, never null
     * @throws NullPointerException if no flag with the given name exists
     */
    public static LandFlag of(String name) {
        NaturalFlag flag = Objects.requireNonNull(APIHandler.getFlagRegistry().getNatural(name), "legacy flag");
        return new LandFlag(flag.getPlugin(), Flag.Target.valueOf(flag.getTarget().toString()), flag.getName(), flag.isApplyInSubareas(), flag.isAlwaysAllowInWilderness());
    }

    /**
     * @deprecated Use {@link #LandFlag(Plugin, Flag.Target, String, boolean, boolean)} instead.
     * @param plugin          your plugin
     * @param name            the flag name
     * @param applyInSubAreas whether the flag applies in sub-areas
     */
    @Deprecated
    public LandFlag(@NotNull Plugin plugin, @NotNull String name, boolean applyInSubAreas) {
        this(plugin, Target.PLAYER, name, applyInSubAreas, false);
    }

    /**
     * Create a flag that applies to player lands and all sub-areas, with wilderness always allowed.
     *
     * @param plugin your plugin
     * @param name   the flag name
     */
    public LandFlag(@NotNull Plugin plugin, @NotNull String name) {
        this(plugin, Target.PLAYER, name, true, false);
    }


    @NotNull
    @Override
    public final FlagModule getModule() {
        return FlagModule.LAND;
    }

    @Override
    public final @NotNull String getTogglePerm() {
        return "lands.setting." + name;
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
