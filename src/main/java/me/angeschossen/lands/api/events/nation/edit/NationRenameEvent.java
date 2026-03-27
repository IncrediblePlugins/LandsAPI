package me.angeschossen.lands.api.events.nation.edit;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.nation.Nation;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a nation is being renamed.
 */
public class NationRenameEvent extends NationEditEvent implements Cancellable {
    /** Handler list for this event. */
    public static final HandlerList handlerList = new HandlerList();
    private final @NotNull String oldName;
    private final @NotNull String newName;
    private boolean cancelled = false;

    /**
     * Create an instance of this event.
     *
     * @param nation     nation that is being renamed
     * @param oldName    old name
     * @param newName    new name to set
     * @param landPlayer The player that is renaming the nation. If null, a player didn't initiate setting a new name.
     */
    public NationRenameEvent(@NotNull Nation nation, @NotNull String oldName, @NotNull String newName, LandPlayer landPlayer) {
        super(nation, landPlayer);

        Checks.requireNonNull(oldName, "oldName");
        Checks.requireNonNull(newName, "newName");
        this.oldName = oldName;
        this.newName = newName;
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
     * Get the current name.
     * @return current name including color codes etc.
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
     * @return new name including color codes etc.
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

        builder.put("name_old", oldName)
                .put("name_new", newName);
    }
}
