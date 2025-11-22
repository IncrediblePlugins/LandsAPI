package me.angeschossen.lands.api.events.land.block;

import me.angeschossen.lands.api.land.block.LandBlock;
import me.angeschossen.lands.api.land.block.LandBlockType;
import me.angeschossen.lands.api.land.block.removalreason.BlockRemovalReason;
import me.angeschossen.lands.api.land.block.removalreason.LandBlockRemovalReason;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when a land block is removed. This is called for blocks such as capture flags or mainblocks.
 * Landblock types: {@link me.angeschossen.lands.api.land.block.LandBlockType}
 */
public class LandBlockRemoveEvent extends LandBlockEvent {
    public static final HandlerList handlerList = new HandlerList();
    private final @NotNull LandBlockRemovalReason reason;

    /**
     * Create an instance of this event.
     *
     * @param landPlayer player that initiated the removal. If null, no player is involved.
     * @param landBlock  landblock that is being removed
     * @param reason     the reason of the removal
     */
    public LandBlockRemoveEvent(@Nullable LandPlayer landPlayer, @NotNull LandBlock landBlock, @NotNull LandBlockRemovalReason reason) {
        super(landBlock.getContainer().getLand(), landPlayer, landBlock);

        this.reason = reason;
    }

    /**
     * Get the reason of the removal.
     *
     * @return reason of removal
     */
    @NotNull
    public final LandBlockRemovalReason getReason() {
        return reason;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Cancel the removal of the landblock.
     *
     * @param cancel true if you wish to cancel this event
     * @throws IllegalStateException if you try to cancel the removal of a landblock that is being removed because the block doesn't exist anymore in the world. See {@link BlockRemovalReason#BLOCK_INVALID}. The removal of rental signs can't be cancelled either.
     */
    @Override
    public void setCancelled(boolean cancel) throws IllegalStateException {
        if (reason == BlockRemovalReason.BLOCK_INVALID) {
            throw new IllegalStateException("Can't cancel landblock removal with reason " + reason.toString());
        }

        if (landBlock.getLandBlockType() == LandBlockType.RENTAL) {
            throw new IllegalStateException("Can't cancel removal of rental landblock");
        }

        super.setCancelled(cancel);
    }

    @Override
    public String toString() {
        return "LandBlockRemoveEvent{" +
                "block=" + landBlock.toString() +
                ",reason=" + reason.toString() +
                "}";
    }

    @Override
    public @Nullable String getLogInfo() {
        return toString();
    }
}