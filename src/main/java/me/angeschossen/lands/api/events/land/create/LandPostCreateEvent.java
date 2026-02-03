package me.angeschossen.lands.api.events.land.create;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called after a land is actually created.
 */
public class LandPostCreateEvent extends LandEvent {
    public static HandlerList handlerList = new HandlerList();

    /**
     * Create instance.
     *
     * @param land       the new land
     * @param landPlayer creator of the land
     */
    public LandPostCreateEvent(@NotNull Land land, @NotNull LandPlayer landPlayer) {
        super(land, Checks.requireNonNull(landPlayer, "landPlayer"));
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }
}
