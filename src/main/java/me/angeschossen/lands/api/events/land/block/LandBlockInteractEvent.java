package me.angeschossen.lands.api.events.land.block;

import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.block.LandBlock;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called whenever a player interacts with a mainblock or caputeflag etc.
 * Landblock types: {@link me.angeschossen.lands.api.land.block.LandBlockType}
 */
public class LandBlockInteractEvent extends LandBlockEvent {
    /** Handler list for this event. */
    public static final HandlerList handlerList = new HandlerList();

    /**
     * Create an instance of this event.
     *
     * @param land       the land containing the block
     * @param landPlayer the player interacting with the block, or {@code null} if no player is involved
     * @param landBlock  the land block being interacted with
     */
    public LandBlockInteractEvent(@NotNull Land land, @Nullable LandPlayer landPlayer, @NotNull LandBlock landBlock) {
        super(land, landPlayer, landBlock);
    }

    /**
     * Returns the handler list for this event type.
     *
     * @return the handler list; never null
     */
    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }
}
