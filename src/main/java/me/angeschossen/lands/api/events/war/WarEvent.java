package me.angeschossen.lands.api.events.war;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.plugin.LandsEvent;
import me.angeschossen.lands.api.war.War;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

/**
 * Used for events that affect a war.
 */
public abstract class WarEvent extends LandsEvent {

    /** The war involved in this event. */
    protected final @NotNull War war;

    /**
     * Create instance.
     *
     * @param war the war involved in this event
     */
    public WarEvent(@NotNull War war) {
        Checks.requireNonNull(war, "war");
        this.war = war;
    }

    /**
     * Get the affected war.
     *
     * @return never null
     */
    @NotNull
    public War getWar() {
        return war;
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        war.setAffectedPlayers("war_", builder);
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        war.setExpressionVariables("war_", builder, null);
    }
}
