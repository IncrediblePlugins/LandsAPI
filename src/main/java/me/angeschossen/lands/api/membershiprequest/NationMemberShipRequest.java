package me.angeschossen.lands.api.membershiprequest;

import me.angeschossen.lands.api.land.Land;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a land's request to join a nation.
 */
public interface NationMemberShipRequest {
    /**
     * Get the land that requested membership in the nation.
     * @return Land that requested membership
     */
    @NotNull
    Land getLand();
}
