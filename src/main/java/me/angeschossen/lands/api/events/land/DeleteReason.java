package me.angeschossen.lands.api.events.land;

import me.angeschossen.lands.api.events.ChunkDeleteEvent;
import me.angeschossen.lands.api.events.LandDeleteEvent;

/**
 * Delete reasons are used at the {@link ChunkDeleteEvent} and {@link LandDeleteEvent}.
 */
public enum DeleteReason {
    /**
     * Used at command execution through player or when 3rd party plugins didn't specify a delete-reason.
     */
    DEFAULT,
    /**
     * Used by 3rd party plugins.
     */
    PLUGIN,
    /**
     * Deletion because of insufficient funds.
     */
    UPKEEP,
    /**
     * Forcefully deletion triggered by a server admin via a cmd.
     */
    ADMIN,
    /**
     * A land is deleted because of inactivity of the owner.
     */
    INACTIVITY,
    /**
     * Land was captured by their enemy during war.
     */
    WAR_CAPTURED,
    /**
     * A temporary camp has expired.
     */
    CAMP_EXPIRED,
    /**
     * A land with no claims was automatically deleted.
     * Servers can disable this in config.
     */
    NO_CLAIMS,
    /**
     * A land member that claimed chunks left the land and the land owner can't claim any more chunks.
     */
    MEMBER_LEAVE
}
