package me.angeschossen.lands.api.events;

import me.angeschossen.lands.api.events.land.member.LandEditMemberCancellableEvent;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Called whenver a land bans a player from a land or an area.
 * This also untrusts the player, if they were previously trusted.
 */
public class LandBanPlayerEvent extends LandEditMemberCancellableEvent {

    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();

    /**
     * Create an instance of this event.
     *
     * @param land the land
     * @param area if null, the target is banned from the whole land. Otherwise, only from the area.
     * @param initiator the player that initiated the ban
     * @param targetUUID the taget that is getting banned
     */
    public LandBanPlayerEvent(@NotNull Land land, @Nullable Area area, @NotNull UUID initiator, @NotNull UUID targetUUID) {
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
