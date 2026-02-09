package me.angeschossen.lands.api.events.land.create;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * This event is fired when a player attempts to create land.
 * The land may not be created if the server configured to automatically claim chunks at land creation.
 * If the claiming of the chunks fails, the land won't actually be created. Use {@link LandPostCreateEvent} if you
 * want to make sure that the land actually is being created.
 */
public class LandPreCreateEvent extends LandEvent implements Cancellable {

    public static HandlerList handlerList = new HandlerList();
    private boolean cancelled;

    /**
     * Create an instance of this event.
     *
     * @param landPlayer The player that initiated the creation. If null, the creation wasn't initiated by a player.
     * @param land       the land that is being created
     */
    public LandPreCreateEvent(@NotNull Land land, @NotNull LandPlayer landPlayer) {
        super(land, Checks.requireNonNull(landPlayer, "landPlayer"));
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public String toString() {
        return "LandCreateEvent{" +
                "land=" + land.toString() +
                "}";
    }
}
