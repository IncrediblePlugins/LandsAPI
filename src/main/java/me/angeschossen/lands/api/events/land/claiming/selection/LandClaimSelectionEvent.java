package me.angeschossen.lands.api.events.land.claiming.selection;

import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.player.Selection;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * Called whenever a selection of chunks is claimed for a land.
 * This usally involves a player executing entering the selection mode ({@link Selection}) and selecting a rectangle of chunks.
 */
public class LandClaimSelectionEvent extends SelectionEvent {
    public static HandlerList handlerList = new HandlerList();

    /**
     * Create an instance of this event.
     *
     * @param land       land for which the selection is about to become claimed
     * @param landPlayer player that triggered the selection claim
     * @param selection  the selection to be claimed
     * @param affectedChunks    the collection of chunks that are going to be claimed.
     *                   In contrast to {@link Selection#getChunks()} this doesn't include already claimed chunks etc.
     */
    public LandClaimSelectionEvent(@NotNull Land land, @Nullable LandPlayer landPlayer, @NotNull Selection selection, @NotNull Set<? extends me.angeschossen.lands.api.land.ChunkCoordinate> affectedChunks) {
        super(land, landPlayer, selection, affectedChunks);
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
        return "LandClaimSelectionEvent{land=" + land.toString() +
                ",selection=" + selection.toString() +
                "}";
    }
}
