package me.angeschossen.lands.api.events;

import me.angeschossen.lands.api.events.land.member.LandEditMemberCancellableEvent;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Called whenever a land untrusts a player from the whole land or from a specific area.
 */
public class LandUntrustPlayerEvent extends LandEditMemberCancellableEvent {

    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();
    private final @NotNull
    UntrustReason reason;

    /**
     * Create an instance of this event.
     *
     * @param land       land from which the target is untrusted
     * @param area       If null, the target is being untrusted from the whole land. Otherwise, only from the specified area.
     * @param initiator  The player that initiated untrusting the player. If null, no player initiated this action.
     * @param targetUUID the player that is being untrusted
     * @param reason     the reason of the player being untrusted
     */
    public LandUntrustPlayerEvent(@NotNull Land land, @Nullable Area area, @Nullable UUID initiator, @NotNull UUID targetUUID, @NotNull UntrustReason reason) {
        super(land, area, initiator, targetUUID);

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

    /**
     * Get the reason of the player being untrusted.
     *
     * @return never null
     */
    @NotNull
    public UntrustReason getReason() {
        return reason;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        if (reason != UntrustReason.BAN) {
            super.setCancelled(cancelled);
        }
    }

    @Override
    public @NotNull
    HandlerList getHandlers() {
        return handlerList;
    }


    /**
     * Represents the reason a player was untrusted from a land or area.
     */
    public enum UntrustReason {
        /**
         * Untrust command or untrust action in menu was triggered by a player.
         */
        DEFAULT,
        /**
         * The player is being banned. Banning a player, also untrusts them.
         */
        BAN,
        /**
         * The player wasn't able to pay its taxes.
         */
        TAXES,
        /**
         * The player was a tenant and his rental expired.
         */
        RENTAL_EXPIRED
    }
}
