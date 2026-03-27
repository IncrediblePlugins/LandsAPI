package me.angeschossen.lands.api.events.memberholder;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a land or nation needs to pay upkeep.
 */
public class MemberHolderUpkeepEvent extends MemberHolderEvent implements Cancellable {
    /** Handler list for this event. */
    public static final HandlerList handlerList = new HandlerList();
    private final double upkeep;
    private final double balance;
    private boolean cancelled = false;

    /**
     * Create instance of this event.
     * @param memberHolder the involved memberHolder
     * @param upkeep upkeep to pay
     * @param balance the current balance
     */
    public MemberHolderUpkeepEvent(@NotNull MemberHolder memberHolder, double upkeep, double balance) {
        super(memberHolder);

        this.upkeep = upkeep;
        this.balance = balance;
    }

    /**
     * Returns the handler list for this event type.
     *
     * @return the handler list; never null
     */
    public static HandlerList getHandlerList() {
        return handlerList;
    }

    /**
     * Check if they have enough money to pay the upkeep.
     * @return true, if they don't have enough money
     */
    public boolean IsInsufficient() {
        return balance < upkeep;
    }

    /**
     * Get the current balance.
     * @return current balance
     */
    public double getBalance() {
        return balance;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Get the upkeep that they need to pay
     * @return upkeep to pay
     */
    public double getUpkeep() {
        return upkeep;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        builder.put("upkeep", upkeep)
                .put(memberHolder.getExpressionPrefix() + "_balance", balance);
    }
}
