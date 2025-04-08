package me.angeschossen.lands.api.player.invite.result;

public enum MergeRequestResult implements InviteResult{
    FAILURE_MAX_CLAIMS,
    FAILURE_PLUGIN,
    FAILURE_ACCESS,
    FAILURE_SAME,
    FAILURE_ALREADY,
    FAILURE_WAR,
    FAILURE_NATION,
    FAILURE_FORCE_NEAR,
    FAILURE_COST,
    FAILURE_ANOTHER_ACTION,
    FAILURE_OWNER_CHANGED,
    SUCCESS;

    @Override
    public boolean isSuccess() {
        return this == SUCCESS;
    }
}
