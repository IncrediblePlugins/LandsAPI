package me.angeschossen.lands.api.player.invite.result;

/**
 * Possible outcomes of a {@link me.angeschossen.lands.api.player.invite.type.MergeRequest}.
 */
public enum MergeRequestResult implements InviteResult{
    /** The land would exceed the maximum number of claims. */
    FAILURE_MAX_CLAIMS,
    /** A third-party plugin blocked the merge. */
    FAILURE_PLUGIN,
    /** The initiating player does not have the required access. */
    FAILURE_ACCESS,
    /** The target is the same entity as the initiator. */
    FAILURE_SAME,
    /** The merge has already been processed or is already in progress. */
    FAILURE_ALREADY,
    /** Merge is blocked because a war is in progress. */
    FAILURE_WAR,
    /** Merge is blocked due to a nation restriction. */
    FAILURE_NATION,
    /** The lands are not close enough for a forced merge. */
    FAILURE_FORCE_NEAR,
    /** The initiating land cannot afford the cost of the merge. */
    FAILURE_COST,
    /** Another action is already pending for this player. */
    FAILURE_ANOTHER_ACTION,
    /** The land owner changed while the request was pending. */
    FAILURE_OWNER_CHANGED,
    /** The merge was completed successfully. */
    SUCCESS;

    @Override
    public boolean isSuccess() {
        return this == SUCCESS;
    }
}
