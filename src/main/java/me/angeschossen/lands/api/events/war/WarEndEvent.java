package me.angeschossen.lands.api.events.war;

import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.war.War;
import me.angeschossen.lands.api.war.enums.WarResult;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when a war ends.
 */
public class WarEndEvent extends WarEvent {
    /** Required by Bukkit's event system. */
    public static HandlerList handlerList = new HandlerList();
    private final @NotNull WarResult warResult;
    private final @Nullable MemberHolder winner;

    /**
     * Create an instance of this event.
     * @param war war that ended
     * @param warResult result of the war
     * @param winner if null, no winner
     */
    public WarEndEvent(@NotNull War war, @NotNull WarResult warResult, @Nullable MemberHolder winner) {
        super(war);

        this.warResult = warResult;
        this.winner = winner;
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
     * Get the loser.
     *
     * @return null, if {@link #getResult()} returns {@link WarResult#DRAW}
     */
    @Nullable
    public MemberHolder getLoser() {
        return winner == null ? null : war.getEnemy(winner);
    }

    /**
     * Get the result of this war.
     * @return result of this war
     */
    @NotNull
    public WarResult getResult() {
        return warResult;
    }

    /**
     * Get the winner.
     *
     * @return null, if {@link #getResult()} returns {@link WarResult#DRAW}
     */
    @Nullable
    public MemberHolder getWinner() {
        return winner;
    }
}
