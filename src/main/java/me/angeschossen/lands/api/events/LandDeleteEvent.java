package me.angeschossen.lands.api.events;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.land.DeleteReason;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called whenever a land is being deleted.
 */
public class LandDeleteEvent extends LandEvent implements Cancellable {

    /** Required by Bukkit's event system. */
    public static HandlerList handlerList = new HandlerList();
    private final @NotNull DeleteReason reason;
    private boolean cancelled;

    /**
     * Create an instance of this event.
     *
     * @param land    land that is being delete
     * @param reason  reason of deletion
     * @param deleter The player that initiated the deletion. If null, the deletion wasn't initiated by a player.
     */
    public LandDeleteEvent(@NotNull Land land, @NotNull DeleteReason reason, @Nullable LandPlayer deleter) {
        super(land, deleter);

        this.reason = reason;
    }

    /**
     * Returns the handler list for this event type, as required by Bukkit.
     *
     * @return the static handler list
     */
    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Get the reason of the deletion.
     *
     * @return reason of deletion
     */
    @NotNull
    public DeleteReason getReason() {
        return reason;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Cancel this event.
     *
     * @param cancelled true if you wish to cancel this event
     * @throws IllegalStateException if an administrator initiated the deletion
     */
    @Override
    public void setCancelled(boolean cancelled) throws IllegalStateException {
        if (cancelled && reason == DeleteReason.ADMIN) {
            throw new IllegalStateException("Can't cancel event with reason 'ADMIN'");
        }

        this.cancelled = cancelled;
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        builder.put("reason", reason);
    }

    @Override
    public @Nullable String getLogInfo() {
        return toString();
    }

    @Override
    public String toString() {
        return "LandDeleteEvent{" +
                "land=" + land.toString() +
                ",reason=" + reason.toString() +
                "}";
    }
}