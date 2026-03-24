package me.angeschossen.lands.api.events.land.member;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.player.PlayerNullableEvent;
import me.angeschossen.lands.api.events.plugin.TargetableEvent;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

/**
 * Used for events that involve managing a member.
 */
public abstract class LandEditMemberEvent extends PlayerNullableEvent implements TargetableEvent {
    /** UUID of the member that this action affects. */
    protected final UUID target;
    /** The land that manages this member. */
    protected final Land land;
    /** The area this action is scoped to, or null if it applies to the whole land. */
    protected final @Nullable
    Area area;

    /**
     * Constructor for this event.
     *
     * @param land      land that manages this member
     * @param area      if null, this action affects the whole land. If not null, this action is only executed for this single area.
     * @param initiator player that initiated this action
     * @param target    member that this action affects
     */
    public LandEditMemberEvent(Land land, @Nullable Area area, @Nullable UUID initiator, @NotNull UUID target) {
        super(initiator);

        this.target = target;
        this.land = land;
        this.area = area;
    }

    /**
     * Get the involved area.
     *
     * @return if null, this action is applied to the whole land. If not null, this action is applied to this single area only.
     */
    @Nullable
    public Area getArea() {
        return area;
    }

    /**
     * Get the involved land.
     * @return never null
     */
    @NotNull
    public Land getLand() {
        return land;
    }

    /**
     * Use {@link #getTargetUUID()} instead.
     * @return the affected member
     */
    @NotNull
    @Deprecated
    public UUID getTargetUID() {
        return getTargetUUID();
    }

    /**
     * Get the affected member
     * @return never null
     */
    @NotNull
    public UUID getTargetUUID() {
        return target;
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        super.setAffectedPlayers(builder);

        builder.put("player_target", Collections.singleton(target));

        if (area != null) {
            area.setAffectedPlayers("area_", builder);
        } else {
            land.setAffectedPlayers("land_", builder);
        }
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        builder.put("target_name", APIHandler.getInstance().getPlayerUtils().getName(target));
        builder.put("target_uuid", target);

        UUID uuid = getPlayerUID();
        if (area != null) {
            area.setExpressionVariables("area_", builder, uuid);
        } else {
            land.setExpressionVariables("land_", builder, uuid);
        }
    }
}
