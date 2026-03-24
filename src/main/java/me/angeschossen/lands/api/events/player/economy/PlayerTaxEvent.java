package me.angeschossen.lands.api.events.player.economy;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.player.PlayerEvent;
import me.angeschossen.lands.api.land.Area;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

/**
 * Called when a land member needs to pay taxes to a land.
 */
public class PlayerTaxEvent extends PlayerEvent implements Cancellable {
    /** Required by Bukkit's event system. */
    public static final HandlerList handlerList = new HandlerList();
    private final @NotNull Area area;
    private final double balance;
    private boolean cancelled = false;

    /**
     * Create instance of this event.
     *
     * @param area      the area that has the taxes configured
     * @param playerUID the UUID of the player that needs to pay taxes
     * @param balance   the player's current balance
     */
    public PlayerTaxEvent(@NotNull Area area, @NotNull UUID playerUID, double balance) {
        super(playerUID);

        this.area = area;
        this.balance = balance;
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
     * The area of which has the taxes configured.
     *
     * @return never null
     */
    @NotNull
    public Area getArea() {
        return area;
    }

    /**
     * Get the taxes of the area.
     * @return taxes of the area
     */
    public double getTax() {
        return area.getTax();
    }

    /**
     * Get the player's balance.
     * @return player's balance
     */
    public double getPlayerBalance() {
        return balance;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Check if the player has enough money to pay the tax.
     * @return true, if the player doesn't have enough money. Depending on the server's config this may trigger the player getting untrusted.
     */
    public boolean isInsufficient() {
        return balance < area.getTax();
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        super.setAffectedPlayers(builder);
        area.setAffectedPlayers("area_", builder);
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        builder.put("player_balance", balance)
                .put("insufficient", isInsufficient());

        area.setExpressionVariables("area_", builder, playerUUID);
    }
}
