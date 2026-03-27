package me.angeschossen.lands.api.events.land.member;

import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Used for events that involve managing a member and that can be cancelled.
 */
public abstract class LandEditMemberCancellableEvent extends LandEditMemberEvent implements Cancellable {

    private boolean cancelled;

    /**
     * Constructor for this event.
     *
     * @param land      land that manages this member
     * @param area      if null, this action affects the whole land. If not null, this action is only executed for this single area.
     * @param initiator player that initiated this action
     * @param target    member that this action affects
     */
    public LandEditMemberCancellableEvent(@NotNull Land land, @Nullable Area area, @Nullable UUID initiator, @NotNull UUID target) {
        super(land, area, initiator, target);
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
