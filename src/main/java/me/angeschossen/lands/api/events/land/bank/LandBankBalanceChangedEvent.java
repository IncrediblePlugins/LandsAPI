package me.angeschossen.lands.api.events.land.bank;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called every time the bank balance changed. For example, if the land pays upkeep or claims chunks etc.
 * This also includes {@link LandBankDepositEvent} and {@link LandBankWithdrawEvent}.
 * This event isn't cancellable. You can only cancel the previously mentioned deposit and withdraw events.
 */
public class LandBankBalanceChangedEvent extends LandEvent {

    /** Handler list for this event. */
    public static final HandlerList handlerList = new HandlerList();
    private final double prev, now;

    /**
     * Create an instance of this event.
     * @param land land to which the bank belongs to
     * @param prev previous balance
     * @param now new balance
     */
    public LandBankBalanceChangedEvent(@NotNull Land land, double prev, double now) {
        super(land, (LandPlayer) null);

        this.prev = prev;
        this.now = now;
    }

    /**
     * Get the new balance
     * @return The new balance. Never negative
     */
    public double getNow() {
        return now;
    }

    /**
     * Get the previous balance
     * @return The previous balance. Never negative
     */
    public double getPrevious() {
        return prev;
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        builder.put("previous", prev)
                .put("now", now);
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
}
