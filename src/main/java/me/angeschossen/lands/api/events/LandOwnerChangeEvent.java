package me.angeschossen.lands.api.events;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.land.member.LandEditMemberCancellableEvent;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

/**
 * Called whenver the owner of a land or specific area inside that land changes.
 */
public class LandOwnerChangeEvent extends LandEditMemberCancellableEvent {
    /** Required by Bukkit's event system. */
    public static final HandlerList handlerList = new HandlerList();
    private final @NotNull Reason reason;

    /**
     * Create an instance of this event.
     *
     * @param land      the land
     * @param area      If null, the owner of the whole land changes.
     *                  Otherwise, only the owner of a specific area changes. This would be the case if a area is being rented.
     * @param initiator The player that initiated the owner change. If null, this change wasn't initiated by a player.
     *                  Could be the case of a rented area expires.
     * @param newOwner  the new owner
     * @param reason    the reason of the owner change
     */
    public LandOwnerChangeEvent(@NotNull Land land, @Nullable Area area, @Nullable UUID initiator, @NotNull UUID newOwner, @NotNull Reason reason) {
        super(land, area, initiator, newOwner);

        Checks.requireNonNull(reason, "reason");
        this.reason = reason;
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
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Get the reason of the owner change.
     *
     * @return reason of owner change
     */
    @NotNull
    public Reason getReason() {
        return reason;
    }

    /**
     * Cancel this event.
     *
     * @param cancelled true if you wish to cancel this event
     * @throws IllegalStateException if this change was initiated by an server administrator
     */
    @Override
    public void setCancelled(boolean cancelled) throws IllegalStateException {
        if (cancelled && reason == Reason.ADMIN) {
            throw new IllegalStateException("Can't cancel event with reason 'ADMIN'");
        }

        super.setCancelled(cancelled);
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        super.setAffectedPlayers(builder);
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        builder.put("reason", reason);
    }

    /**
     * The reason for an ownership change of a land or area.
     */
    public enum Reason {
        /**
         * A player just started renting an area.
         */
        RENT,
        /**
         * The rental of an area just expired.
         */
        RENT_EXPIRED,
        /**
         * An area was purchased.
         */
        BOUGHT,
        /**
         * A purchased area was forcefully transfered back to the land owner.
         * Usually used at "/lands rent remove tenant".
         * The tenant might receive a compensation.
         */
        BOUGHT_EXPIRED,
        /**
         * Normal ownership transfer. Used at "/lands setowner".
         */
        DEFAULT,
        /**
         * Owner was offline too long and is replaced by another land member.
         */
        LAND_EXPIRATION,
        /**
         * Ownership transfer was initiated by an server administrator.
         */
        ADMIN,
        /**
         * A new owner is set at a land, because the previous owner failed to pay the upkeep.
         */
        UPKEEP
    }
}
