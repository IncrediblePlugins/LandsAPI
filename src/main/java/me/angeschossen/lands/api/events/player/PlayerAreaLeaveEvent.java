package me.angeschossen.lands.api.events.player;

import me.angeschossen.lands.api.events.internal.PlayerLocationAreaEvent;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * This event is called when a player leaves a area into wilderness.
 */
public class PlayerAreaLeaveEvent extends PlayerLocationAreaEvent implements Cancellable {
    public static HandlerList handlerList = new HandlerList();
    private boolean cancelled;

    public PlayerAreaLeaveEvent(Area area, LandPlayer landPlayer) {
        super(area, landPlayer);
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
