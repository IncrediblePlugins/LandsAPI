package me.angeschossen.lands.api.events.memberholder;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.relations.Relation;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

/**
 * Called whenever the relationship between lands or nations changes.
 * This is not called when an {@link me.angeschossen.lands.api.relations.RelationRequest} is sent, but rather when it is accepted by the receiver.
 */
public class MemberHolderRelationChangeEvent extends MemberHolderEvent implements Cancellable {

    public static final HandlerList handlerList = new HandlerList();

    private final @NotNull MemberHolder target;
    private final @NotNull Relation relation;
    private boolean cancelled;

    /**
     * Create instance of this event.
     *
     * @param initiator land or nation that initiated the change
     * @param target    the target land or nation
     * @param relation  the new relation status
     */
    public MemberHolderRelationChangeEvent(@NotNull MemberHolder initiator, @NotNull MemberHolder target, @NotNull Relation relation) {
        super(initiator);

        this.target = Checks.requireNonNull(target, "target");
        this.relation = Checks.requireNonNull(relation, "relation");
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    /**
     * Get the current relation.
     *
     * @return current relation between the two
     */
    public @NotNull Relation getCurrentRelation() {
        return memberHolder.getRelation(target);
    }

    /**
     * Get the new relation.
     *
     * @return the new relation between the two
     */
    public @NotNull Relation getNewRelation() {
        return relation;
    }

    /**
     * Get the target of the change.
     *
     * @return never null
     */
    public @NotNull MemberHolder getTarget() {
        return target;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        target.setExpressionVariables("target_" + target.getExpressionPrefix(), builder, null);
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        super.setAffectedPlayers(builder);

        target.setAffectedPlayers("target_" + target.getExpressionPrefix(), builder);
    }
}
