package me.angeschossen.lands.api.land;

import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.role.system.SystemFlagStates;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Implemented by entities (such as lands and areas) that support system flag state overrides for players.
 */
public interface SystemFlagStatesHolder {
    /**
     * Set system flag states for a player. System flag states can only be applied
     * to players that aren't trusted. This should only be used if you want some players
     * to bypass the regular role system. For example if you want to implement a war system
     * that allows players to grief land during war.
     * <p>
     * It's your responsibility to remove the flag states when they shouldn't be applied anymore. See {@link #removeSystemFlagStates(UUID)} for more info.
     * Also they aren't persistent and therefore reset at each server restart. Once the player leaves
     * the server they're removed as well. So you need to add your own logic to set them upon login
     * and whenever you want to apply them.
     *
     * @param player     the player for which the flags should be set
     * @param flagStates collection of flag states
     * @throws IllegalStateException if the player is trusted
     */
    void setSystemFlagStates(@NotNull LandPlayer player, @NotNull SystemFlagStates flagStates) throws IllegalStateException;

    /**
     * Remove system flag states for a player.
     *
     * @param player the player
     */
    void removeSystemFlagStates(@NotNull UUID player);
}
