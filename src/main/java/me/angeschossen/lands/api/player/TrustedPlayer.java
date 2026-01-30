package me.angeschossen.lands.api.player;

import me.angeschossen.lands.api.land.Land;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface TrustedPlayer {

    /**
     * @return Max amount of support claims
     * @deprecated Support claims now depend on a config option instead.
     * Get the amount of chunks that a land will be able to claim additionally, if
     * this player is part of a land. For trusted players, this amount is related to the lands.chunks.support.number permission.
     * If the player is the owner of the land, this amount is related to the lands.chunks.number permission
     */
    @Deprecated
    int getSupportClaims();

    /**
     * Is the player trusted in the whole land?
     *
     * @return true, if the player is trusted in the whole land
     */
    boolean isTrustedWholeLand();

    /**
     * Get the amount of areas, including the default area ({@link Land#getDefaultArea()}), in which the player is trusted.
     *
     * @return Amount of trusted areas
     */
    int getTrustedSize();

    /**
     * Get the players identifier.
     *
     * @return UUID of the player
     */
    @NotNull
    UUID getUID();
}
