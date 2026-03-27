package me.angeschossen.lands.api.player.invite.result;

/**
 * Represents the result of accepting an {@link me.angeschossen.lands.api.player.invite.Invite}.
 */
public interface InviteResult {

    /**
     * Check whether the invite acceptance was successful.
     *
     * @return true if the invite was accepted successfully
     */
    boolean isSuccess();
}
