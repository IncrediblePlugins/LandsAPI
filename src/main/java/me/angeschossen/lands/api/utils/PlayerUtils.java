package me.angeschossen.lands.api.utils;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Utility methods for player-related operations.
 */
public interface PlayerUtils {

    /**
     * Getting the name of an player. The player might be offline.
     * @param playerUID The player
     * @return The name of the player
     */
    @NotNull String getName(@NotNull UUID playerUID);
}
