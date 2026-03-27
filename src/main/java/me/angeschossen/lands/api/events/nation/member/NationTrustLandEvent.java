package me.angeschossen.lands.api.events.nation.member;

import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Called when a land joins a nation.
 * Usually at "/nations accept"
 */
public class NationTrustLandEvent extends NationEditMemberCancellableEvent {

    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();

    /**
     * Create an instance of this event.
     *
     * @param nation    nation that previously invited the land
     * @param land      land that accepts the nation's invitation to join them
     * @param initiator the player that initially sent the invitation to the land
     */
    public NationTrustLandEvent(@NotNull Nation nation, @NotNull Land land, @Nullable UUID initiator) {
        super(nation, land, initiator);
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
    public @NotNull
    HandlerList getHandlers() {
        return handlerList;
    }
}
