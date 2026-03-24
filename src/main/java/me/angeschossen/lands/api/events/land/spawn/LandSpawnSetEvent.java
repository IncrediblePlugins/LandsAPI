package me.angeschossen.lands.api.events.land.spawn;

import com.github.angeschossen.pluginframework.api.blockutil.UnloadedPosition;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.land.LandCancellableEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * This even is called each time the spawn of a land changes.
 */
public class LandSpawnSetEvent extends LandCancellableEvent {
    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();
    private final @NotNull UnloadedPosition location;

    /**
     * Create an instance of this event.
     *
     * @param land       land to which this spawn belongs
     * @param landPlayer player which sets this spawn.
     *                   if null, the spawn is set by the plugin itself
     * @param location   the new spawn
     */
    public LandSpawnSetEvent(@NotNull Land land, @Nullable LandPlayer landPlayer, @NotNull UnloadedPosition location) {
        super(land, landPlayer);

        Objects.requireNonNull(location, "spawn location can't be null");
        this.location = location;
    }

    /**
     * Get the new location of the spawn.
     *
     * @return the new spawn location
     */
    @NotNull
    public UnloadedPosition getNew() {
        return location;
    }

    /**
     * Get the old spawn.
     *
     * @return null, if previously none was set
     */
    @Nullable
    public UnloadedPosition getCurrent() {
        return land.getSpawnPosition();
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
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        builder.put("world", location.getWorldName())
                .put("x", location.getBlockX())
                .put("y", location.getBlockY())
                .put("z", location.getBlockZ());
    }
}