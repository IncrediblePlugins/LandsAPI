package me.angeschossen.lands.api.events;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.land.DeleteReason;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.events.land.claiming.selection.LandUnclaimSelectionEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called whenever a chunk is being unclaimed.
 */
public class ChunkDeleteEvent extends LandEvent implements Cancellable {
    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();
    private final int x, z;
    private final @NotNull World world;
    private final @NotNull DeleteReason reason;
    private final @NotNull UnclaimType unclaimType;
    private boolean cancelled;

    /**
     * Create an instance of this event
     *
     * @param landPlayer  The player that initiated the unclaiming. null, if not triggered by a player
     * @param reason      the reason of unclaim
     * @param unclaimType type of unclaim
     * @param world       world
     * @param land        land for which the chunk is unclaimed
     * @param x           chunk x
     * @param z           chunk z
     */
    public ChunkDeleteEvent(@Nullable LandPlayer landPlayer, @NotNull DeleteReason reason, @NotNull UnclaimType unclaimType, @NotNull World world, @NotNull Land land, int x, int z) {
        super(land, landPlayer);

        this.unclaimType = unclaimType;
        this.world = world;
        this.x = x;
        this.z = z;
        this.reason = reason;
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
    public HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Get the reason of unclaim.
     *
     * @return reason
     */
    @NotNull
    public DeleteReason getReason() {
        return reason;
    }

    /**
     * Get the type of unclaim. Lets you check if this deletion is part of a selection etc.
     *
     * @return type of unclaim
     */
    @NotNull
    public UnclaimType getUnclaimType() {
        return unclaimType;
    }

    /**
     * Get the world of the chunk.
     *
     * @return world in which the chunk is located in
     */
    @NotNull
    public World getWorld() {
        return world;
    }

    /**
     * Get chunk x
     *
     * @return chunk x
     */
    public int getX() {
        return x;
    }

    /**
     * Get chunk z
     *
     * @return chunk z
     */
    public int getZ() {
        return z;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Cancel this event.
     *
     * @param b true if you wish to cancel this event
     * @throws IllegalStateException if an administrator initiated the deletion or {@link #getUnclaimType()} doesn't return {@link UnclaimType#DEFAULT}.
     *                               In this case you should use the corresponding events:
     *                               - {@link UnclaimType#LAND_DELETION}: {@link LandDeleteEvent}
     *                               - {@link UnclaimType#ALL}: {@link me.angeschossen.lands.api.events.land.claiming.LandUnclaimAllEvent}
     *                               - {@link UnclaimType#SELECTION}: {@link LandUnclaimSelectionEvent}
     */
    @Override
    public void setCancelled(boolean b) throws IllegalStateException {
        if (b) {
            if (reason == DeleteReason.ADMIN) {
                throw new IllegalStateException("Can't cancel event with reason " + DeleteReason.ADMIN.toString() + ".");
            }

            if (unclaimType != UnclaimType.DEFAULT) {
                throw new IllegalStateException("Can't cancel this event for this unclaim type \"" + unclaimType.toString() + "\". Instead, please cancel one of the specific events for that type: LandUnclaimAllEvent (" + UnclaimType.ALL.toString() + "), LandUnclaimSelectionEvent (" + UnclaimType.SELECTION.toString() + "), LandDeleteEvent (" + UnclaimType.LAND_DELETION.toString() + ")");
            }
        }

        this.cancelled = b;
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        builder.put("reason", reason.toString())
                .put("type", unclaimType.toString());
    }

    /**
     * Describes the scope of the unclaim operation that triggered this event.
     */
    public enum UnclaimType {
        /**
         * A single chunk is being unclaimed.
         */
        DEFAULT,
        /**
         * A land with all its chunks is being unclaimed.
         */
        LAND_DELETION,
        /**
         * All chunks of a land are being unclaimed, but not the land itself.
         */
        ALL,
        /**
         * A selection of chunks is being unclaimed.
         */
        SELECTION
    }
}
