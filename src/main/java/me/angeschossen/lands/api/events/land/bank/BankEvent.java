package me.angeschossen.lands.api.events.land.bank;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Used for all events that involve a land's bank.
 */
public abstract class BankEvent extends LandEvent implements Cancellable {
    private boolean cancelled = false;
    /** The amount being deposited or withdrawn; can be negative. */
    protected final double value;

    /**
     * Create an instance.
     * @param land the land that the bank belongs to
     * @param landPlayer The player that is involded. For example at "/lands deposit"
     * @param value can be negative
     */
    public BankEvent(@NotNull Land land, @Nullable LandPlayer landPlayer, double value) {
        super(land, landPlayer);
        this.value = value;
    }

    /**
     * Get the modification value.
     * @return can be negative
     */
    public final double getValue() {
        return value;
    }

    @Override
    public final boolean isCancelled() {
        return cancelled;
    }

    @Override
    public final void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        builder.put("value", value);
    }
}
