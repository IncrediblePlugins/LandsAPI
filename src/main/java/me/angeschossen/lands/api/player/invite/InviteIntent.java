package me.angeschossen.lands.api.player.invite;

/**
 * Describes the intention behind an invite sent by a land.
 */
public enum InviteIntent {
    /**
     * The land wants to trust a player.
     */
    TRUST,
    /**
     * The land wants to set a new owner.
     */
    OWNER,
    /**
     * The land wants to merge another land into theirs.
     */
    MERGE
}
