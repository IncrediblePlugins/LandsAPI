package me.angeschossen.lands.api.events.war;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.player.PlayerEvent;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

/**
 * Called when a war is being declared or a war declaration is being accepted, if mutual war declarations are enabled.
 */
public class WarDeclareEvent extends PlayerEvent implements Cancellable {

    /** Required by Bukkit's event system. */
    public static HandlerList handlerList = new HandlerList();
    private final @NotNull MemberHolder attacker, defender;
    private boolean cancelled;

    /**
     * Create instance of this event.
     *
     * @param attacker land or nation to which the sender belongs
     * @param sender   the sender of the declaration
     * @param defender the receiver of the declaration
     */
    public WarDeclareEvent(@NotNull MemberHolder attacker, @NotNull LandPlayer sender, @NotNull MemberHolder defender) {
        super(sender);

        Checks.requireNonNull(sender, "sender");
        Checks.requireNonNull(attacker, "attacker");
        Checks.requireNonNull(defender, "defender");
        this.attacker = attacker;
        this.defender = defender;
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
     * Get the player that sent the declaration to the defenders.
     *
     * @return player that sent the declaration
     */
    @Override
    public @NotNull LandPlayer getLandPlayer() {
        assert landPlayer != null;
        return landPlayer;
    }

    /**
     * Get the sender / attacker.
     *
     * @return sender / attacker
     */
    @NotNull
    public MemberHolder getAttacker() {
        return attacker;
    }

    /**
     * Get the receiver / defender.
     *
     * @return receiver / defender
     */
    @NotNull
    public MemberHolder getDefender() {
        return defender;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        super.setAffectedPlayers(builder);
        attacker.setAffectedPlayers("war_attacker_", builder);
        defender.setAffectedPlayers("war_defender_", builder);
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        UUID uuid = getPlayerUID();
        attacker.setExpressionVariables("war_attacker_", builder, uuid);
        defender.setExpressionVariables("war_defender_", builder, uuid);
    }

    @Override
    public String toString() {
        return "WarDeclareEvent{attacker=" + attacker.toString() + ",defender=" + defender.toString() + "}";
    }
}
