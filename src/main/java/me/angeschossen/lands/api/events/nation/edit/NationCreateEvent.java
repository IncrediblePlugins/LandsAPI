package me.angeschossen.lands.api.events.nation.edit;

import me.angeschossen.lands.api.nation.Nation;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Called whenever a player tries to create a nation.
 */
public class NationCreateEvent extends NationEditEvent implements Cancellable {

    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();
    private boolean cancelled;

    /**
     * Create an instance of this event.
     *
     * @param landPlayer null, if no player involved in creating this nation
     * @param nation     the nation being created
     */
    public NationCreateEvent(@Nullable LandPlayer landPlayer, @NotNull Nation nation) {
        super(nation, landPlayer);
    }

    /**
     * Create an instance of this event.
     *
     * @param playerUUID null, if no player involved in creating this nation
     * @param nation     the nation being created
     */
    public NationCreateEvent(@Nullable UUID playerUUID, @NotNull Nation nation) {
        super(nation, playerUUID);
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

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public @Nullable String getLogInfo() {
        return toString();
    }

    @Override
    public String toString() {
        return "NationCreateEvent{" +
                "nation=" + nation.toString() +
                "}";
    }
}
