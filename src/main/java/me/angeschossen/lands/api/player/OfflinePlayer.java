package me.angeschossen.lands.api.player;

import com.github.angeschossen.pluginframework.api.limits.LimitationHolder;
import me.angeschossen.lands.api.land.Land;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

/**
 * Data for offline players.
 */
public interface OfflinePlayer extends LimitationHolder {

    /**
     * Get the last known name.
     *
     * @return might not be up to date with the player's current name. Is null, if no data about this player in the database.
     */
    @Nullable
    String getName();

    /**
     * Get a players /lands edit land. Commands are executed for this land.
     *
     * @return The current /lands edit land
     */
    @Nullable
    Land getEditLand();

    /**
     * Get all lands the player owns or is a member of.
     *
     * @return All lands
     */
    @NotNull
    Collection<? extends Land> getLands();

    /**
     * Get the number of lands (own lands and where the player is trusted).
     *
     * @return 0, if the player isn't part of any land
     */
    int getSize();

    /**
     * Get the identifier of this player.
     *
     * @return UUID of the player
     */
    @NotNull
    UUID getUID();
}
