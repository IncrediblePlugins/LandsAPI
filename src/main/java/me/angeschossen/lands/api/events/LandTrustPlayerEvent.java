package me.angeschossen.lands.api.events;

import me.angeschossen.lands.api.events.land.member.LandEditMemberCancellableEvent;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Called when a land trusts a players.
 * Depending on the servers config, invites are enabled.
 * If invites are enabled, this event is called whenever a player accepts an invitation to join a land.
 * If invites are disabled, players are trusted directly and the event is called at the command execution or menu action that trusts the player.
 */
public class LandTrustPlayerEvent extends LandEditMemberCancellableEvent {
    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();

    /**
     * Create an instance of this event.
     * @param land the land
     * @param area If null, the player is trusted to the whole land. Otherwise, only to the specified area.
     * @param initiator The player that trusted the target. If null, no player initiated this action.
     * @param targetUUID the target that is being trusted
     */
    public LandTrustPlayerEvent(@NotNull Land land, @Nullable Area area, @Nullable UUID initiator, @NotNull UUID targetUUID) {
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
