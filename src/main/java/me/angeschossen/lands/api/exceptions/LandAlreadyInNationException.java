package me.angeschossen.lands.api.exceptions;

/**
 * Thrown if a land is part of a nation and you try to add it to another nation.
 */
public class LandAlreadyInNationException extends RuntimeException {

    /**
     * Create instance.
     */
    public LandAlreadyInNationException() {
        super("This land is already part of a nation.");
    }
}
