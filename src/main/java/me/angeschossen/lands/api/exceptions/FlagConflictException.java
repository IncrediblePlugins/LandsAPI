package me.angeschossen.lands.api.exceptions;


import me.angeschossen.lands.api.flags.type.parent.Flag;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Thrown when you try to register a flag, which name is already taken by a different flag.
 */
public class FlagConflictException extends RuntimeException {

    /** The flag that is already registered with the conflicting name. */
    private final @NotNull Flag<?> existing;

    /**
     * Create instance.
     *
     * @param existing the existing flag
     * @param message  message to throw
     */
    public FlagConflictException(@NotNull Flag<?> existing, @NotNull String message) {
        super(message);

        Objects.requireNonNull(existing, "existing flag can't be null");
        this.existing = existing;
    }

    /**
     * Get the existing flag, which already has the provided name.
     *
     * @return the existing flag
     */
    @NotNull
    public Flag<?> getExisting() {
        return existing;
    }
}
