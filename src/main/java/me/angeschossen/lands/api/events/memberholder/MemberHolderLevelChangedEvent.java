package me.angeschossen.lands.api.events.memberholder;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.levels.Level;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called on level up and down of a land or nation.
 */
public class MemberHolderLevelChangedEvent extends MemberHolderEvent {
    /** Handler list for this event. */
    public static final HandlerList handlerList = new HandlerList();

    private final @NotNull Level previous;

    /**
     * Create instance of this event.
     *
     * @param memberHolder land or nation
     * @param previous     the previous level
     */
    public MemberHolderLevelChangedEvent(@NotNull MemberHolder memberHolder, @NotNull Level previous) {
        super(memberHolder);

        Checks.requireNonNull(previous, "previous level");
        this.previous = previous;
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

    /**
     * Get new level.
     * @return new level
     */
    @NotNull
    public Level getNew() {
        return memberHolder.getLevel();
    }

    /**
     * Get the previous level.
     * @return previous level
     */
    @NotNull
    public Level getPrevious() {
        return previous;
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        previous.setExpressionVariables("level_previous_", builder, null);
        memberHolder.getLevel().setExpressionVariables("level_new_", builder, null);
    }
}
