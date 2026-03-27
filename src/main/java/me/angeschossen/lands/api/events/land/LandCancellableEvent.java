package me.angeschossen.lands.api.events.land;

import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

/**
 * Used for events that involve a land and can be cancelled.
 */
public abstract class LandCancellableEvent extends LandEvent implements Cancellable {

    private boolean cancelled;

    /**
     * Constructor for this event.
     * @param land involved land
     * @param landPlayer involved player
     */
    public LandCancellableEvent(@NotNull Land land, LandPlayer landPlayer) {
        super(land, landPlayer);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}