package me.angeschossen.lands.api.events.nation.member;

import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Called whenever a nation modifies one of its members (lands) and the event is cancellable.
 */
public abstract class NationEditMemberCancellableEvent extends NationEditMemberEvent implements Cancellable {

    /** Whether this event has been cancelled. */
    protected boolean cancelled;

    /**
     * Create an instance of this event.
     *
     * @param nation    the nation whose membership is being modified
     * @param land      the land being added or removed
     * @param initiator the UUID of the player who initiated the action, or {@code null}
     */
    public NationEditMemberCancellableEvent(@NotNull Nation nation,@NotNull  Land land, UUID initiator) {
        super(nation, land, initiator);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
