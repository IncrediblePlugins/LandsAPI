package me.angeschossen.lands.api.war;

import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.war.player.WarPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface TeamGiver {
    /**
     * Get the team the player belongs to.
     *
     * @param landPlayer The player
     * @return NEUTRAL, if the player is not related to any land or nation in this war
     */
    @NotNull
    me.angeschossen.lands.api.war.enums.WarTeam getTeam(@NotNull LandPlayer landPlayer);

    /**
     * Get online players of the attacker.
     *
     * @return Online players of the attacker
     */
    @NotNull Collection<? extends WarPlayer> getOnlineAttackers();

    /**
     * Get online players of the attacker.
     *
     * @return Online players of the attacker
     */
    @NotNull Collection<? extends WarPlayer> getOnlineDefenders();
}
