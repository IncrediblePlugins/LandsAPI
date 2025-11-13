package me.angeschossen.lands.api.events.land.claiming.selection;

import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.player.Selection;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * Called whenever a selection of chunks is unclaimed from a land.
 * This usally involves a player executing entering the selection mode ({@link Selection}) and selecting a rectangle of chunks.
 */
public class LandUnclaimSelectionEvent extends SelectionEvent {
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

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public String toString() {
        return "LandUnclaimSelectionEvent{land=" + land.toString() +
                ",selection=" + selection.toString() +
                "}";
    }
}
