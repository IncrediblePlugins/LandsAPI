package me.angeschossen.lands.api.events.memberholder;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.plugin.LandsEvent;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

/**
 * Base class for events that involve a land or nation ({@link MemberHolder}).
 */
public abstract class MemberHolderEvent extends LandsEvent {

    /** The land or nation involved in this event. */
    protected final @NotNull MemberHolder memberHolder;

    /**
     * Used for events that involve a land or nation.
     * @param memberHolder land or nation
     */
    public MemberHolderEvent(@NotNull MemberHolder memberHolder) {
        Checks.requireNonNull(memberHolder, "memberHolder");
        this.memberHolder = memberHolder;
    }

    /**
     * Get the involved land or nation.
     * @return land or nation
     */
    @NotNull
    public MemberHolder getMemberHolder() {
        return memberHolder;
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        memberHolder.setAffectedPlayers(memberHolder.getExpressionPrefix(), builder);
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        memberHolder.setExpressionVariables(memberHolder.getExpressionPrefix(), builder,null);
    }
}
