package me.angeschossen.lands.api.player.claiming;

/**
 * Describes the outcome of a chunk claiming operation.
 */
public enum ClaimResult {
    /**
     * Claiming was successful.
     */
    SUCCESS(false),
    /**
     * Claiming failed completely.
     */
    FAILED(true),
    /**
     * Claiming failed partly, but the minor failure can be ignored.
     * This happens if for example not all chunks of an selection could be claimed.
     */
    IGNOREABLE(false),
    /**
     * For example, if the selection is invalid or not complete.
     */
    INVALID(true);

    /** Whether the claiming process should be aborted after this result. */
    public final boolean shouldStop;

    ClaimResult(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }
}
