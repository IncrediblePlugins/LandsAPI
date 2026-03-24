package me.angeschossen.lands.api.events.player.teleportation;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.land.spawn.LandSpawnTeleportEvent;
import me.angeschossen.lands.api.events.player.PlayerEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

/**
 * Deprecated. Use {@link LandSpawnTeleportEvent} instead.
 */
@Deprecated
public class PlayerSpawnLandEvent extends PlayerEvent implements Cancellable {
    /** Required by Bukkit's event system. */
    public static final HandlerList handlerList = new HandlerList();
    private final @NotNull Land land;
    private boolean cancelled = false;

    /**
     * Create instance of this event.
     *
     * @param land       the land spawn the player is teleporting to
     * @param landPlayer the player that is teleporting
     */
    public PlayerSpawnLandEvent(@NotNull Land land, LandPlayer landPlayer) {
        super(landPlayer);

        this.land = land;
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
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        super.setAffectedPlayers(builder);

        land.setAffectedPlayers("land_",builder);
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        land.setExpressionVariables("land_", builder,getPlayerUID());
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Get the land whose spawn the player is teleporting to.
     *
     * @return never null
     */
    @NotNull
    public Land getLand() {
        return land;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
