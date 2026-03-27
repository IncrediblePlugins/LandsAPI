package me.angeschossen.lands.api.role.enums;

/**
 * Used to check if a player is allowed to edit a specific role or a player that is part of that role.
 */
public enum EditPlayerResponse {
    /**
     * The player is the tenant of the area.
     */
    FAILURE_TENANT,
    /**
     * The role has a higher priority.
     */
    FAILURE_ROLE_PRIORITY,
    /**
     * The player is able to edit the role or player that belongs to it.
     */
    SUCCESS
}
