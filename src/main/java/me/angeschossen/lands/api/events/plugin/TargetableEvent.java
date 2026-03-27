package me.angeschossen.lands.api.events.plugin;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Used for events that affect a target.
 */
public interface TargetableEvent {

    /**
     * Use {@link #getTargetUUID()} instead.
     * @return The target
     */
    @Deprecated
    @NotNull UUID getTargetUID();

    /**
     * Get the provided target player. Used at trusting players etc.
     * @return The target
     */
    @NotNull UUID getTargetUUID();
}
