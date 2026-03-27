package me.angeschossen.lands.api.events.player.area;

import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;

/**
 * This event is called when a player leaves a area into wilderness.
 */
public class PlayerAreaLeaveEvent extends PlayerAreaEvent implements Cancellable {
    private boolean cancelled;

    /**
     * Create instance of this event.
     *
     * @param area       the area the player is leaving
     * @param landPlayer the player leaving the area
     */
    public PlayerAreaLeaveEvent(Area area, LandPlayer landPlayer) {
        super(area, landPlayer);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    @Override
    public String toString() {
        return "PlayerAreaLeaveEvent{player=" + landPlayer.getPlayer() + "}";
    }
}
