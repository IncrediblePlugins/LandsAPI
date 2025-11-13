package me.angeschossen.lands.api.events.land.claiming;

import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called whenever all claims are unclaimed at once for a land.
 * Usually the case when a player executes "/lands unclaim all".
 */
public class LandUnclaimAllEvent extends LandEvent implements Cancellable {
    public static HandlerList handlerList = new HandlerList();
    private boolean cancelled;

    /**
     * Create an instance of this event.
     * @param land land from which all claims are about the be unclaimed
     * @param landPlayer involved player. Usually the player that executed "/lands unclaim all"
     */
    public LandUnclaimAllEvent(@NotNull Land land, @Nullable LandPlayer landPlayer) {
        super(land, landPlayer);
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
    public void setCancelled(boolean b)  {
        this.cancelled = b;
    }

    @Override
    public String toString() {
        return "LandUnclaimAllEvent{land=" + land.toString() +
                "}";
    }
}
