package me.angeschossen.lands.api.membershiprequest;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Represents a player's request to join a land as a member.
 */
public interface LandMemberShipRequest {

    /**
     * Get the player's UUID that requested membership in the land.
     * @return UUID of the requester
     */
    @NotNull
    UUID getPlayerUUID();
}
