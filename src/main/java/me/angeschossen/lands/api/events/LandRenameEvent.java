package me.angeschossen.lands.api.events;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a land is renamed.
 */
public class LandRenameEvent extends LandEvent implements Cancellable {
    /** Required by Bukkit's event system. */
    public static final HandlerList handlerList = new HandlerList();
    private final @NotNull String oldName;
    private final @NotNull String newName;
    private boolean cancelled = false;

    /**
     * Create an instance of this event.
     *
     * @param landPlayer the player that sets the new name. If null, no player initiated this action.
     * @param land       the land that is being renamed
     * @param oldName    the old name, including color
     * @param newName    the new name, including color
     */
    public LandRenameEvent(LandPlayer landPlayer, @NotNull Land land, @NotNull String oldName, @NotNull String newName) {
        super(land, landPlayer);

        this.oldName = oldName;
        this.newName = newName;
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
     * Get the current name.
     *
     * @return includes color
     */
    @NotNull
    public String getCurrentName() {
        return oldName;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Get the new name.
     *
     * @return includes color
     */
    @NotNull
    public String getNewName() {
        return newName;
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

        builder.put("name_old", oldName);
        builder.put("name_new", newName);
    }

    @Override
    public String toString() {
        return "LandRenameEvent{" +
                "land=" + land.toString() +
                ",currentName=" + oldName +
                ",newName=" + newName +
                "}";
    }
}
