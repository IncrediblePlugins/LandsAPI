package me.angeschossen.lands.api.events.nation.edit;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.land.DeleteReason;
import me.angeschossen.lands.api.nation.Nation;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called whenever a nation is about to be deleted.
 */
public class NationDeleteEvent extends NationEditEvent implements Cancellable {

    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();
    private final @NotNull DeleteReason reason;
    private boolean cancelled;

    /**
     * Create instance of this event.
     *
     * @param nation  the nation that is being deleted
     * @param reason  reason of the deletion
     * @param deleter null, if no player is involved in deleting this nation
     */
    public NationDeleteEvent(@NotNull Nation nation, @NotNull DeleteReason reason, @Nullable LandPlayer deleter) {
        super(nation, deleter);

        Checks.requireNonNull(reason, "reason");
        this.reason = reason;
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        builder.put("reason", reason);
    }

    /**
     * Returns the handler list for this event type.
     *
     * @return the handler list; never null
     */
    public static HandlerList getHandlerList() {
        return handlerList;
    }

    /**
     * Get the nation that is being deleted.
     *
     * @return the nation; never null
     */
    @NotNull
    public Nation getNation() {
        return nation;
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
     * @throws IllegalStateException if this nation is forcefully deleted by an administrator ({@link DeleteReason#ADMIN})
     */
    @Override
    public void setCancelled(boolean cancelled) throws IllegalStateException {
        if (cancelled && reason == DeleteReason.ADMIN) {
            throw new IllegalStateException("Can't cancel event with reason 'ADMIN'");
        }

        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public @Nullable String getLogInfo() {
        return toString();
    }

    @Override
    public String toString() {
        return "NationCreateEvent{" +
                "nation=" + nation.toString() +
                ",reason=" + reason.toString() +
                "}";
    }
}