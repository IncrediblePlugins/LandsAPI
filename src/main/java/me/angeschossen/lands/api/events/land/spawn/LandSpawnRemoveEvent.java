package me.angeschossen.lands.api.events.land.spawn;

import com.github.angeschossen.pluginframework.api.blockutil.UnloadedPosition;
import me.angeschossen.lands.api.events.LandDeleteEvent;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This event is called each time the spawn is removed.
 * It mostly fires when an admin deletes world data or the world, in which the spawn was located
 * in doesn't exist any longer. This is not called upon land deletion.
 * If you want to handle this case too, just listen to {@link LandDeleteEvent}.
 */
public class LandSpawnRemoveEvent extends LandEvent {
    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();
    private final UnloadedPosition current;

    /**
     * Create an instance of this event.
     *
     * @param land       land to which the current spawn belongs
     * @param landPlayer The player that removes this spawn.
     *                   If null, the spawn is being removed by the plugin itself.
     * @param current    current spawn that is being removed
     */
    public LandSpawnRemoveEvent(@NotNull Land land, @Nullable LandPlayer landPlayer, @NotNull UnloadedPosition current) {
        super(land, landPlayer);

        this.current = current;
    }

    /**
     * Returns the handler list for this event type.
     *
     * @return the handler list; never null
     */
    public static HandlerList getHandlerList() {
        return handlerList;
    }

    /**
     * Get the current spawn.
     *
     * @return location of the spawn
     */
    @NotNull
    public UnloadedPosition getCurrent() {
        return current;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}