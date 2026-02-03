package me.angeschossen.lands.api.events.player.database;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.events.player.PlayerEvent;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Called whenever a player is deleted from the database.
 */
public final class LandPlayerDeletedEvent extends PlayerEvent {
    public static final HandlerList handlerList = new HandlerList();
    private final @NotNull Reason reason;

    /**
     * Create instance
     *
     * @param playerUUID the player to be deleted
     */
    public LandPlayerDeletedEvent(@NotNull UUID playerUUID, @NotNull Reason reason) {
        super(Checks.requireNonNull(playerUUID, "playerUUID"));

        this.reason = Checks.requireNonNull(reason, "reason");
    }

    /**
     * Get the reason of why the player was deleted.
     *
     * @return reason of deletion
     */
    public @NotNull Reason getReason() {
        return reason;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public enum Reason {
        /**
         * Forceful deletion by a server admin via a admin command.
         */
        ADMIN,
        /**
         * Deletion because of inactivity.
         */
        INACTIVITY
    }
}
