package me.angeschossen.lands.api.events.land.spawn;

import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Called when a player teleports to a land spawn.
 */
public class LandSpawnTeleportEvent extends LandEvent implements Cancellable {
    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();
    private boolean cancelled;

    /**
     * Create an instance of this event.
     *
     * @param land       land to which the spawn belongs
     * @param landPlayer player that teleports to the spawn
     */
    public LandSpawnTeleportEvent(@NotNull Land land, @NotNull LandPlayer landPlayer) {
        super(land, landPlayer);

        Objects.requireNonNull(landPlayer, "LandPlayer can't be null");
    }

    @Override
    public @NotNull LandPlayer getLandPlayer() {
        assert super.getLandPlayer() != null;
        return super.getLandPlayer();
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
    public HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
