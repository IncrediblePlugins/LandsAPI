package me.angeschossen.lands.api.events.land.block;

import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.block.LandBlock;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for events that involve a {@link LandBlock}, such as placement or removal.
 */
public abstract class LandBlockEvent extends LandEvent implements Cancellable {

    /** The land block that is affected by this event. */
    protected final @NotNull LandBlock landBlock;
    /** Whether this event has been cancelled. */
    protected boolean cancelled;

    /**
     * Create an instance of this event.
     *
     * @param land       the land that owns the block
     * @param landPlayer the player involved, or {@code null} if no player is involved
     * @param landBlock  the land block affected by this event
     */
    public LandBlockEvent(@NotNull Land land, @Nullable LandPlayer landPlayer, @NotNull LandBlock landBlock) {
        super(land, landPlayer);
        this.landBlock = landBlock;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    /**
     * Get the land block involved in this event.
     *
     * @return the land block; never null
     */
    @NotNull
    public final LandBlock getLandBlock() {
        return landBlock;
    }
}
