package me.angeschossen.lands.api.land;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A category that can be assigned to a {@link Land} to signal to other players what the land is about — for example shops, arenas, or recruitment.
 * Each server can define its own categories.
 */
public interface LandCategory {

    /**
     * Get the description of this category.
     * @return Never null
     */
    @NotNull List<String> getDescription();

    /**
     * Get the name of this category.
     * @return Might include color codes
     */
    @NotNull String getName();
}
