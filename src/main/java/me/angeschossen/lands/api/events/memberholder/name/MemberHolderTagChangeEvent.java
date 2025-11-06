package me.angeschossen.lands.api.events.memberholder.name;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.events.memberholder.MemberHolderEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when a land or nation tag is being changed.
 */
public class MemberHolderTagChangeEvent extends MemberHolderEvent implements Cancellable {
    public static final HandlerList handlerList = new HandlerList();
    private final @Nullable String newTag;
    private boolean cancelled = false;
    private final @NotNull LandPlayer landPlayer;

    /**
     * Create an instance of this event.
     *
     * @param landPlayer   the player that sets the new tag. If null, no player initiated this action.
     * @param memberHolder land or nation
     * @param newTag       the new tag, including color
     */
    public MemberHolderTagChangeEvent(@NotNull LandPlayer landPlayer, @NotNull MemberHolder memberHolder, @Nullable String newTag) {
        super(memberHolder);

        this.landPlayer = landPlayer;
        this.newTag = newTag;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }


    /**
     * Get the the player.
     *
     * @return player that initiated the change
     */
    public @NotNull LandPlayer getLandPlayer() {
        return landPlayer;
    }

    /**
     * Get the current tag.
     *
     * @return null, if currently no tag is set
     */
    @Nullable
    public String getCurrentTag() {
        return memberHolder.getColorTag();
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Get the new tag.
     *
     * @return null, if tag removed
     */
    @Nullable
    public String getNewTag() {
        return newTag;
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

        String currentTag = getCurrentTag();
        builder.put("tag_current", currentTag == null ? "null" : currentTag);
        builder.put("tag_new", newTag == null ? "null" : newTag);
    }

    @Override
    public String toString() {
        return "MemberHolderTagChangeEvent{" +
                "memberHolder=" + memberHolder.toString() +
                ",currentTag=" + getCurrentTag() +
                ",newTag=" + newTag +
                "}";
    }
}
