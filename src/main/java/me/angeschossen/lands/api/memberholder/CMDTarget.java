package me.angeschossen.lands.api.memberholder;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents an entity whose name can be used as a command parameter.
 * Spaces in names are replaced with underscores to make them usable in commands.
 */
public interface CMDTarget {
    /**
     * Replaces spaces with an underscore to make names useable as command parameters.
     * @param in The input
     * @return The name with spaces replaced with underscores
     */
    static String parseCMDName(@NotNull String in) {
        Objects.requireNonNull(in, "input can't be null");
        return in.replace(' ', '_');
    }

    /**
     * Get the command parameter name.
     * @return Name that is used in command parameters.
     */
    @NotNull
    String getCMDName();
}
