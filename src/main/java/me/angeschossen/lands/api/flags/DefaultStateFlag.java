package me.angeschossen.lands.api.flags;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Use {@link me.angeschossen.lands.api.flags.type.parent.DefaultStateFlag} instead.
 *
 * @param <T> the concrete flag type returned by builder-style setter methods
 */
@Deprecated
public abstract class DefaultStateFlag<T> extends Flag<T> implements me.angeschossen.lands.api.flags.type.parent.DefaultStateFlag<T> {

    /** The default state of this flag when a new area is created. */
    protected boolean defaultState;

    /**
     * Create a new flag with a default state.
     *
     * @param plugin                 the plugin registering this flag
     * @param target                 the target audience for this flag
     * @param name                   unique name of the flag
     * @param applyInSubAreas        whether this flag applies inside sub-areas
     * @param alwaysAllowInWilderness whether this flag is always enabled in the wilderness
     */
    public DefaultStateFlag(@NotNull Plugin plugin, @NotNull Target target, @NotNull String name, boolean applyInSubAreas, boolean alwaysAllowInWilderness) {
        super(plugin, target, name, applyInSubAreas, alwaysAllowInWilderness);
    }

    @Override
    public final boolean getDefaultState() {
        return defaultState;
    }

    @NotNull
    @Override
    public final DefaultStateFlag<T> setDefaultState(boolean defaultState) {
        this.defaultState = defaultState;
        return this;
    }
}
