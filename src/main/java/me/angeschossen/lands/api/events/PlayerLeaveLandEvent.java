package me.angeschossen.lands.api.events;

import me.angeschossen.lands.api.events.player.PlayerLandEvent;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called whenver a land leaves the whole land.
 */
public class PlayerLeaveLandEvent extends PlayerLandEvent {

    /** Required by Bukkit's event system. */
    public static HandlerList handlerList = new HandlerList();

    /**
     * Create an instance of this event.
     * @param land land that the player is leaving
     * @param area If null, the player is leaving the whole land. Otherwise, only the specified area.
     * @param landPlayer the player that is leaving
     */
    public PlayerLeaveLandEvent(@NotNull Land land, @Nullable Area area, @NotNull LandPlayer landPlayer) {
        super(land, area, landPlayer);
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
