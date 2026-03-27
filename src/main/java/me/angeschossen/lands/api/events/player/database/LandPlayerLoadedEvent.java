package me.angeschossen.lands.api.events.player.database;

import me.angeschossen.lands.api.events.player.PlayerEvent;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Called once the player data of a player that just joined, is fully loaded.
 */
public class LandPlayerLoadedEvent extends PlayerEvent {
    /** Required by Bukkit's event system. */
    public static final HandlerList handlerList = new HandlerList();

    /**
     * Create instance
     *
     * @param landPlayer the joined player
     */
    public LandPlayerLoadedEvent(@NotNull LandPlayer landPlayer) {
        super(landPlayer);
    }

    /**
     * Get the joined player.
     *
     * @return never null
     */
    @Override
    public @NotNull LandPlayer getLandPlayer() {
        return Objects.requireNonNull(super.getLandPlayer(), "expected landPlayer");
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Returns the handler list for this event type, as required by Bukkit.
     *
     * @return the static handler list
     */
    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
