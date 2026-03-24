package me.angeschossen.lands.api.events;

import me.angeschossen.lands.api.events.land.member.LandEditMemberCancellableEvent;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Called whenver a land invites a player to the whole land or a specific area inside that land.
 */
public class LandInvitePlayerEvent extends LandEditMemberCancellableEvent {
    /** Required by Bukkit's event system. */
    public static HandlerList handlerList = new HandlerList();

    /**
     * Create an instance of this event.
     *
     * @param land       the land that invites the player
     * @param area       if null, the player is invited to a specific area only
     * @param initiator  The player that initiated the invitation. If null, a player didn't initiate the invitation.
     * @param targetUUID the player that is being invited
     */
    public LandInvitePlayerEvent(@NotNull Land land, @Nullable Area area, @Nullable UUID initiator, @NotNull UUID targetUUID) {
        super(land, area, initiator, targetUUID);
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
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }
}
