package me.angeschossen.lands.api.player.invite.result;

public enum TrustResult implements InviteResult{
    /**
     * A player tried to trust himself.
     */
    FAILURE_SELF,
    /**
     * A player isn't allowed to trust players.
     */
    FAILURE_ACCESS,
    /**
     * The target player is already trusted.
     */
    FAILURE_ALREADY,
    /**
     * The area has reched the maximum amount of members.
     * This is related to the lands.members.number permission.
     */
    FAILURE_MAX_MEMBERS,
    /**
     * You can't trust an banned player.
     */
    FAILURE_BANNED,
    /**
     * During war trusting players is disabled.
     */
    FAILURE_WAR,
    /**
     * The target player has its own land and isn't allowed to be a member of a land
     * and a owner of their own land at the same time. This is related to the "invite-owner" option in config.yml.
     */
    FAILURE_OWN_LAND,
    /**
     * The player reached its maximum amount of lands, they can be a member of. This is related to the
     * lands.lands.number permission.
     */
    FAILURE_PLAYER_MAX_LANDS,
    /**
     * The target player is already invited.
     */
    FAILURE_ALREADY_INVITED,
    /**
     * A 3rd party plugin cancelled the trust event ({@link me.angeschossen.lands.api.events.LandTrustPlayerEvent}).
     */
    FAILURE_PLUGIN,
    /**
     * If the server doesn't use Luckperms for permissions, the target player must be online.
     * Using Luckperms maximum lands limits can be checked for offline players as well.
     */
    FAILURE_OFFLINE,
    /**
     * The target player disabled receiving invites in their personal settings menu.
     */
    FAILURE_PLAYER_FLAG_RECEIVE_INVITES,
    /**
     * The target player was invite successfully.
     * If invitis are enabled in config.yml.
     */
    SUCCESS_INVITED,
    /**
     * The target player was trusted successfully.
     * If the player that trusts the target is a server admin or invites are disabled in config.yml.
     */
    SUCCESS_TRUSTED;

    @Override
    public boolean isSuccess(){
        return this == SUCCESS_INVITED || this == SUCCESS_TRUSTED;
    }
}
