package me.angeschossen.lands.api.events.player.area;

import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.Nullable;

/**
 * This event is called when a player enters a area.
 * Please note that the player must not enter from wilderness.
 * They can also enter from a different area of the same or a different land.
 */
public class PlayerAreaEnterEvent extends PlayerAreaEvent implements Cancellable {
    private final Area from;
    private boolean cancelled;

    /**
     * Create instance of this event.
     *
     * @param from       the area the player is entering from, or null if from wilderness
     * @param area       the area the player is entering
     * @param landPlayer the player entering the area
     */
    public PlayerAreaEnterEvent(@Nullable Area from, Area area, LandPlayer landPlayer) {
        super(area, landPlayer);

        this.from = from;
    }


    /**
     * Get the area from which the player is entering from.
     * This area can be from the same or from a different land as {@link #getArea}
     *
     * @return This method will return null if the player is entering from the wilderness.
     */
    @Nullable
    public Area getFrom() {
        return from;
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
        return "PlayerAreaEnterEvent{player=" + landPlayer.getPlayer() + "}";
    }
}
