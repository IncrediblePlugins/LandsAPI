package me.angeschossen.lands.api.events.war.captureflag.base;

import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.war.captureflag.CaptureFlag;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Used for events that effect a capture flag.
 */
public abstract class CaptureFlagCancellableEvent extends CaptureFlagEvent implements Cancellable {
    private boolean cancelled;

    /**
     * Constructor
     * @param captureFlag the affected capture flag
     * @param player if null, no player is involved in triggering this event
     */
    public CaptureFlagCancellableEvent(@NotNull CaptureFlag captureFlag, @Nullable LandPlayer player) {
        super(captureFlag, player);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
