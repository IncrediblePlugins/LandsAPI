package me.angeschossen.lands.api.land;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A category can be set to a {@link Land} to singal to other players what the land is about. For example shops, arenas, looking for members etc.
 * Each server can define their own categories.
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
