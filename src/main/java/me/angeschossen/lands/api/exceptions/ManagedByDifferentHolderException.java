package me.angeschossen.lands.api.exceptions;

/**
 * Thrown if a player tries to edit a role that is for example managed by the nation and not by the land.
 */
public class ManagedByDifferentHolderException extends RuntimeException {
    /**
     * Create instance.
     * @param errorMessage The error message
     */
    public ManagedByDifferentHolderException(String errorMessage) {
        super(errorMessage);
    }
}
