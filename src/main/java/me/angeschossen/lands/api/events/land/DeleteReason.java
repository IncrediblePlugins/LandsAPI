package me.angeschossen.lands.api.events.land;

import me.angeschossen.lands.api.events.ChunkDeleteEvent;
import me.angeschossen.lands.api.events.LandDeleteEvent;

/**
 * Describes the cause of a land or chunk deletion, reported by {@link LandDeleteEvent} and {@link ChunkDeleteEvent}.
 */
public enum DeleteReason {
    /**
     * Deleted via a player command, or by a third-party plugin that did not specify a reason.
     */
    DEFAULT,
    /**
     * Explicitly deleted by a third-party plugin with a known cause.
     */
    PLUGIN,
    /**
     * Deleted because the land could not cover its upkeep cost.
     */
    UPKEEP,
    /**
     * Forcefully deleted by a server administrator via a command.
     */
    ADMIN,
    /**
     * Deleted because the owner has been inactive for too long.
     */
    INACTIVITY,
    /**
     * Deleted because the land was captured by the enemy during a war.
     */
    WAR_CAPTURED,
    /**
     * Deleted because a temporary camp's lifetime expired.
     */
    CAMP_EXPIRED,
    /**
     * Deleted automatically because the land had no remaining claims.
     * Servers can disable this behaviour in {@code config.yml}.
     */
    NO_CLAIMS,
    /**
     * Deleted because a member who provided extra claim slots left the land
     * and the owner no longer has enough claim capacity.
     */
    MEMBER_LEAVE
}
