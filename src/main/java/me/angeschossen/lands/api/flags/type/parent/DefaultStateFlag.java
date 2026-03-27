package me.angeschossen.lands.api.flags.type.parent;

import org.jetbrains.annotations.NotNull;

/**
 * Flags that have a default state.
 * @param <T> the flag implementation
 */
public interface DefaultStateFlag<T> extends Flag<T> {

    /**
     * Get the default state.
     * @return true, if flag is enabled by default.
     */
    boolean getDefaultState();

    /**
     * Set the default state.
     * @param state true, if flag should be enabled by default
     * @return instance of this flag
     */
    @NotNull
    DefaultStateFlag<T> setDefaultState(boolean state);
}
