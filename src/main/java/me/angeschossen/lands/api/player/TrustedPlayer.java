package me.angeschossen.lands.api.player;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface TrustedPlayer {

    /**
     * Get max chunk claims
     *
     * @return Max chunk claims
     */
    @NotNull
    int getSupportClaims();

    /**
     * Is the player trusted in the whole land?
     *
     * @return Is trusted in whole land
     */
    @NotNull
    boolean isTrustedWholeLand();

    int getTrustedSize();

    /**
     * Get player UID
     *
     * @return The players UUID
     */
    @NotNull
    UUID getUID();
}
