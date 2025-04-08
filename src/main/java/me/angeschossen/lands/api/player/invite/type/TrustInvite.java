package me.angeschossen.lands.api.player.invite.type;

import me.angeschossen.lands.api.player.invite.Invite;
import me.angeschossen.lands.api.player.invite.result.TrustResult;

public interface TrustInvite extends Invite<TrustResult> {
    /**
     * Check if the player is going to be trusted in the whole land.
     * @return true, if the player is going to be trusted in the whole land
     */
    boolean isWholeLand();
}
