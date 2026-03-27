package me.angeschossen.lands.api.events.land;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.player.PlayerNullableEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

/**
 * Used for events that involve a land.
 */
public abstract class LandEvent extends PlayerNullableEvent {

    /** The land involved in this event. */
    protected final @NotNull Land land;

    /**
     * Constructor for this event.
     *
     * @param land       involved land
     * @param landPlayer involved player
     */
    public LandEvent(@NotNull Land land, @Nullable LandPlayer landPlayer) {
        super(landPlayer);

        Checks.requireNonNull(land, "land");
        this.land = land;
    }

    /**
     * Constructor for this event.
     *
     * @param land       involved land
     * @param landPlayer The involved player, which might be offline.
     */
    public LandEvent(@NotNull Land land, UUID landPlayer) {
        super(landPlayer);

        this.land = land;
    }

    /**
     * Get the involved land.
     *
     * @return never null
     */
    @NotNull
    public Land getLand() {
        return land;
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        super.setAffectedPlayers(builder);

        land.setAffectedPlayers("land_", builder);
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        land.setExpressionVariables("land_", builder, getPlayerUID());
    }
}
