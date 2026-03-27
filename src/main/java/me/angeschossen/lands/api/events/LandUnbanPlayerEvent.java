package me.angeschossen.lands.api.events;

import me.angeschossen.lands.api.events.land.member.LandEditMemberCancellableEvent;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Called whenever a land unbans a player from the whole land or a specific area.
 */
public class LandUnbanPlayerEvent extends LandEditMemberCancellableEvent {

    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();

    /**
     * Create an instance of this event.
     *
     * @param land       the land that the player is being unbanned from
     * @param area       If null, the player is unbanned from the whole land. Otherwise, only from the specified area.
     * @param initiator  The player that initiated the target getting unbanned. If null, no player initiated this action.
     * @param targetUUID the player that is being unabnned
     */
    public LandUnbanPlayerEvent(@NotNull Land land, @Nullable Area area, @Nullable UUID initiator, @NotNull UUID targetUUID) {
        super(land, area, initiator, targetUUID);
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
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }
}
