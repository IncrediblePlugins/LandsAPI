package me.angeschossen.lands.api.events.player;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

/**
 * Used for events that affect a player and land.
 */
public abstract class PlayerLandEvent extends PlayerEvent implements Cancellable {
    private final @Nullable Area area;
    private final @NotNull Land land;
    private boolean cancelled;

    /**
     * Constructor
     *
     * @param land       the affected land
     * @param area       the affected area
     * @param landPlayer involved player
     */
    public PlayerLandEvent(@NotNull Land land, @Nullable Area area, @NotNull LandPlayer landPlayer) {
        super(landPlayer);

        Checks.requireNonNull(land, "land");
        Checks.requireNonNull(landPlayer, "landPlayer");
        this.land = land;
        this.area = area;
        this.landPlayer = landPlayer;
    }

    /**
     * Get the area.
     * @return if null, this event affects the whole land. If not null, this event only affects this area.
     */
    @Nullable
    public Area getArea() {
        return area;
    }

    /**
     * Get the land.
     * @see #getArea()
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
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        super.setAffectedPlayers(builder);
        land.setAffectedPlayers("land_", builder);
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        UUID uuid = getPlayerUID();
        if (area != null) {
            area.setExpressionVariables("area_", builder, uuid);
        } else {
            land.setExpressionVariables("land_", builder, uuid);
        }
    }
}
