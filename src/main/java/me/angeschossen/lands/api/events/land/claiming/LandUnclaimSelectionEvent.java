package me.angeschossen.lands.api.events.land.claiming;

import me.angeschossen.lands.api.events.land.claiming.selection.SelectionEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.player.Selection;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * Use {@link me.angeschossen.lands.api.events.land.claiming.selection.LandUnclaimSelectionEvent} instead.
 */
@Deprecated
public class LandUnclaimSelectionEvent extends SelectionEvent {
    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();

    /**
     * Create an instance of this event.
     *
     * @param land       land for which the selection is about to become unclaimed
     * @param landPlayer player that triggered the selection unclaim
     * @param selection  the selection to be unclaimed
     * @param unclaim    the collection of chunks that are going to be unclaimed.
     *                   In contrast to {@link Selection#getChunks()} this doesn't include already unclaimed chunks etc.
     */
    public LandUnclaimSelectionEvent(@NotNull Land land, @Nullable LandPlayer landPlayer, @NotNull Selection selection, @NotNull Set<? extends me.angeschossen.lands.api.land.ChunkCoordinate> unclaim) {
        super(land, landPlayer, selection, unclaim);
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
