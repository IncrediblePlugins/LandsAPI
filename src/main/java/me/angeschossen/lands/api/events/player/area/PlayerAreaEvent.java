package me.angeschossen.lands.api.events.player.area;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.player.PlayerEvent;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

/**
 * Used for events where a player interacts with an area.
 */
public abstract class PlayerAreaEvent extends PlayerEvent {
    /** Required by Bukkit's event system. */
    public static HandlerList handlerList = new HandlerList();
    /** The area involved in this event. */
    protected final Area area;

    /**
     * Create instance with an online player.
     *
     * @param area       the involved area
     * @param landPlayer the involved player
     */
    public PlayerAreaEvent(@NotNull Area area, LandPlayer landPlayer) {
        super(landPlayer);

        this.area = area;
    }

    /**
     * Create instance with a player UUID (may be offline).
     *
     * @param area   the involved area
     * @param player UUID of the involved player
     */
    public PlayerAreaEvent(@NotNull Area area, UUID player) {
        super(player);

        this.area = area;
    }

    /**
     * Returns the handler list for this event type, as required by Bukkit.
     *
     * @return the static handler list
     */
    public static HandlerList getHandlerList() {
        return handlerList;
    }

    /**
     * Get the area. Can be entering or leaving, depending on the event.
     *
     * @return the affected area
     */
    @NotNull
    public Area getArea() {
        return area;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        super.setAffectedPlayers(builder);

        area.setAffectedPlayers("area_", builder);
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        area.setExpressionVariables("area_", builder, getPlayerUID());
    }
}
