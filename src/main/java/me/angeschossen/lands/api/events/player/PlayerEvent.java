package me.angeschossen.lands.api.events.player;

import me.angeschossen.lands.api.player.LandPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Used for events that affect a player.
 */
public abstract class PlayerEvent extends PlayerNullableEvent {

    /**
     * Constructor
     *
     * @param landPlayer the player that is online
     */
    public PlayerEvent(@NotNull LandPlayer landPlayer) {
        super(landPlayer);

        Objects.requireNonNull(landPlayer, "LandPlayer can't be null");
    }

    /**
     * Constructor
     *
     * @param playerUUID UUID of the player, that might be offline
     */
    public PlayerEvent(@NotNull UUID playerUUID) {
        super(playerUUID);

        Objects.requireNonNull(playerUUID, "UUID can't be null");
    }

    /**
     * Get the player that is online
     *
     * @return might be null, if player not online
     */
    @Override
    @Nullable
    public LandPlayer getLandPlayer() {
        return landPlayer;
    }

    /**
     * Get the player's UUID
     *
     * @return never null
     */
    @Override
    @NotNull
    public UUID getPlayerUUID() {
        return Objects.requireNonNull(super.getPlayerUUID(), "expected player UUID");
    }

    /**
     * Get the player's UUID
     *
     * @return never null
     * @deprecated Use {{@link #getPlayerUUID()}} instead.
     */
    @Override
    @NotNull
    @Deprecated
    public UUID getPlayerUID() {
        return getPlayerUUID();
    }
}
