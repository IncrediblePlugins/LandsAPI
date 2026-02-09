package me.angeschossen.lands.api.events.player.database;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.events.player.PlayerEvent;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Called when a player is deleted from the database.
 */
public class LandPlayerDeletedEvent extends PlayerEvent {
    public static final HandlerList handlerList = new HandlerList();
    private final @NotNull Reason reason;

    /**
     * Create instance
     *
     * @param playerUUID the deleted player
     */
    public LandPlayerDeletedEvent(@NotNull UUID playerUUID, @NotNull Reason reason) {
        super(playerUUID);

        this.reason = Checks.requireNonNull(reason, "reason");
    }

    public @NotNull Reason getReason() {
        return reason;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Reason of deletion.
     */
    public enum Reason {
        /**
         * Player is considered inactive because of parameters in the config.
         */
        INACTIVITY,
        /**
         * Player was forcefully deleted by a server admin.
         */
        ADMIN
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
