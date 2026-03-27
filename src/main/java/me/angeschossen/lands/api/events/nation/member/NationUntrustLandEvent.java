package me.angeschossen.lands.api.events.nation.member;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Called whenever a nation removes a land.
 */
public class NationUntrustLandEvent extends NationEditMemberCancellableEvent {

    /** Required by Bukkit's event system. */
    public static HandlerList handlerList = new HandlerList();
    private final @NotNull UntrustReason reason;

    /**
     * Create an instance of this event.
     *
     * @param nation    the nation that removes the land
     * @param land      the land
     * @param initiator player that initiated the removal. Usually by executing "/nations untrust"
     * @param reason    the reason of removal
     */
    public NationUntrustLandEvent(@NotNull Nation nation, @NotNull Land land, UUID initiator, @NotNull UntrustReason reason) {
        super(nation, land, initiator);

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

    /**
     * Get the reason of removal.
     *
     * @return reason of removal
     */
    @NotNull
    public UntrustReason getReason() {
        return reason;
    }

    @Override
    public @NotNull
    HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * The reason a land was removed from a nation.
     */
    public enum UntrustReason {
        /**
         * Used when executing "/nations untrust"
         */
        COMMAND,
        /**
         * Used when a land is automatically being untrusted, because they failed to pay the taxes to the nation.
         */
        TAXES
    }
}
