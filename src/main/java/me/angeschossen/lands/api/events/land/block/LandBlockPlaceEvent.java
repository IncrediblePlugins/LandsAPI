package me.angeschossen.lands.api.events.land.block;

import me.angeschossen.lands.api.land.block.LandBlock;
import me.angeschossen.lands.api.land.block.LandBlockType;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when a land block is placed. This will also be fired for the auto placed mainblock at land creation.
 * Landblock types: {@link me.angeschossen.lands.api.land.block.LandBlockType}
 */
public class LandBlockPlaceEvent extends LandBlockEvent {
    public static final HandlerList handlerList = new HandlerList();

    /**
     * Create an instance of this event.
     *
     * @param landPlayer player that placed the landblock. If null, no player is involved.
     * @param landBlock  landblock that is being placed
     */
    public LandBlockPlaceEvent(@Nullable LandPlayer landPlayer, @NotNull LandBlock landBlock) {
        super(landBlock.getContainer().getLand(), landPlayer, landBlock);
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Cancel the placement of this landblock.
     *
     * @param cancel true if you wish to cancel this event
     * @throws IllegalStateException placements of rental signs can't be cancelled. See {@link LandBlockType#RENTAL}
     */
    @Override
    public void setCancelled(boolean cancel) {
        if (landBlock.getLandBlockType() == LandBlockType.RENTAL) {
            throw new IllegalStateException("Can't cancel landblock placement of type " + LandBlockType.RENTAL.toString());
        }
        super.setCancelled(cancel);
    }

    @Override
    public String toString() {
        return "LandBlockPlaceEvent{" +
                "block=" + landBlock.toString() +
                "}";
    }

    @Override
    public @Nullable String getLogInfo() {
        return toString();
    }
}