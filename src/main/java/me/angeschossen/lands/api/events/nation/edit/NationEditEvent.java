package me.angeschossen.lands.api.events.nation.edit;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.player.PlayerNullableEvent;
import me.angeschossen.lands.api.nation.Nation;
import me.angeschossen.lands.api.player.LandPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

/**
 * Used for events that modify a nation.
 */
public abstract class NationEditEvent extends PlayerNullableEvent {


    /** The nation being edited by this event. */
    protected final @NotNull Nation nation;

    /**
     * Create an instance of this event.
     *
     * @param nation     nation to be edited
     * @param landPlayer involved player
     */
    public NationEditEvent(@NotNull Nation nation, @Nullable LandPlayer landPlayer) {
        super(landPlayer);

        Checks.requireNonNull(nation, "nation");
        this.nation = nation;
    }

    /**
     * Create an instance of this event.
     *
     * @param nation     nation to be edited
     * @param landPlayer involved player
     */
    public NationEditEvent(@NotNull Nation nation, @Nullable UUID landPlayer) {
        super(landPlayer);

        Checks.requireNonNull(nation, "nation");
        this.nation = nation;
    }

    /**
     * Get the nation that is affected by this event.
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
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        nation.setExpressionVariables("nation_", builder, getPlayerUID());
    }
}
