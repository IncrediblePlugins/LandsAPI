package me.angeschossen.lands.api.war.player;

import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Tracks war-specific runtime state for an online player participating in a war,
 * such as their scoreboard display. Instances are created when a player logs in during an active war
 * and removed when they log out.
 */
public interface WarPlayer {
    /**
     * Get the player.
     *
     * @return player interface
     */
    @NotNull
    LandPlayer getLandPlayer();

    /**
     * Set the scoreboard for this player
     *
     * @param scoreboard if null, removes scoreboard
     */
    void setScoreboard(@Nullable Scoreboard scoreboard);

    /**
     * Get the current set scoreboard
     *
     * @return current set scoreboard
     */
    @Nullable
    Scoreboard getScoreboard();

    /**
     * Close their current war menu and set the scoreboard to the parameter.
     *
     * @param main the scoreboard to set - this will remove the current scoreboard for the nametags
     */
    void removeMenuAndScoreboard(@Nullable Scoreboard main);
}
