package me.angeschossen.lands.api.events.nation.member;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

/**
 * Called whenever a nation modifies one of its members (lands).
 */
public abstract class NationEditMemberEvent extends LandEvent {
    /** The UUID of the player who initiated this action, or {@code null} if none. */
    protected final @Nullable UUID initiator;
    /** The nation whose membership is being changed. */
    protected final @NotNull Nation nation;
    /** The land being added to or removed from the nation. */
    protected final @NotNull Land land;

    /**
     * Used for events that involve a nation modifying one of its lands.
     *
     * @param nation    the nation
     * @param land      the modified land
     * @param initiator the initiatior of this action
     */
    public NationEditMemberEvent(@NotNull Nation nation, @NotNull Land land, @Nullable UUID initiator) {
        super(land, initiator);

        Checks.requireNonNull(nation, "nation");
        Checks.requireNonNull(land, "land");
        this.initiator = initiator;
        this.land = land;
        this.nation = nation;
    }

    /**
     * Get the involved nation.
     * @return never null
     */
    @NotNull
    public Nation getNation() {
        return nation;
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        super.setAffectedPlayers(builder);

        nation.setAffectedPlayers("nation_", builder);
        land.setAffectedPlayers("land_", builder);
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        UUID uuid = getPlayerUID();
        nation.setExpressionVariables("nation_", builder, uuid);
        land.setExpressionVariables("land_", builder, uuid);
    }
}
