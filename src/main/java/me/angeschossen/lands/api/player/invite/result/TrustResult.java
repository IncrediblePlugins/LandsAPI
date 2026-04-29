package me.angeschossen.lands.api.player.invite.result;

/**
 * Represents the result of a trust invite action.
 */
public enum TrustResult implements InviteResult {
    /**
     * A player tried to trust himself.
     */
    FAILURE_SELF,
    /**
     * The executing player does not have permission to trust other players.
     */
    FAILURE_ACCESS,
    /**
     * The target player is already trusted.
     */
    FAILURE_ALREADY,
    /**
     * The area has reached the maximum number of members.
     * This is related to the {@code lands.members.number} permission.
     */
    FAILURE_MAX_MEMBERS,
    /**
     * Banned players cannot be trusted.
     */
    FAILURE_BANNED,
    /**
     * Trusting players is disabled while a war is in progress.
     */
    FAILURE_WAR,
    /**
     * The target player has its own land and isn't allowed to be a member of a land
     * and a owner of their own land at the same time. This is related to the "invite-owner" option in config.yml.
     */
    FAILURE_OWN_LAND,
    /**
     * The target player has reached the maximum number of lands they can own.
     */
    FAILURE_PLAYER_MAX_OWN_LANDS,
    /**
     * The target player has reached the maximum number of lands they can be a member of.
     * This is related to the {@code lands.lands.number} permission.
     */
    FAILURE_PLAYER_MAX_LANDS,
    /**
     * The target player has already been invited and the invite is still pending.
     */
    FAILURE_ALREADY_INVITED,
    /**
     * A third-party plugin cancelled the trust via {@link me.angeschossen.lands.api.events.LandTrustPlayerEvent}.
     */
    FAILURE_PLUGIN,
    /**
     * The target player is offline and the server does not use LuckPerms, which is required
     * to look up permission-based limits for offline players.
     */
    FAILURE_OFFLINE,
    /**
     * The target player has disabled receiving invites via their personal settings menu.
     */
    FAILURE_PLAYER_FLAG_RECEIVE_INVITES,
    /**
     * The target player was invited successfully.
     * Returned when invites are enabled in {@code config.yml}.
     */
    SUCCESS_INVITED,
    /**
     * The target player was trusted directly without an invite.
     * Returned when the acting player is a server admin or invites are disabled in {@code config.yml}.
     */
    SUCCESS_TRUSTED;

    @Override
    public boolean isSuccess() {
        return this == SUCCESS_INVITED || this == SUCCESS_TRUSTED;
    }
}
