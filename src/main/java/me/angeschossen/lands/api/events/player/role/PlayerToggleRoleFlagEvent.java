package me.angeschossen.lands.api.events.player.role;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.role.RoleEvent;
import me.angeschossen.lands.api.flags.type.RoleFlag;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.role.Role;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

/**
 * Called whenever a player toggles a role flag.
 */
public class PlayerToggleRoleFlagEvent extends RoleEvent implements Cancellable {
    public static final HandlerList handlerList = new HandlerList();
    private final @NotNull LandPlayer landPlayer;
    private final @NotNull RoleFlag roleFlag;
    private boolean cancelled;

    /**
     * Create instance of this event.
     *
     * @param role       role for which the flag is changed
     * @param landPlayer the player that changes the state
     */
    public PlayerToggleRoleFlagEvent(@NotNull Role role, @NotNull RoleFlag roleFlag, @NotNull LandPlayer landPlayer) {
        super(role);

        this.roleFlag = Checks.requireNonNull(roleFlag, "roleFlag");
        this.landPlayer = Checks.requireNonNull(landPlayer, "landPlayer");
    }

    public @NotNull RoleFlag getFlag() {
        return roleFlag;
    }

    /**
     * Get the player.
     *
     * @return player that toggles the flag
     */
    public @NotNull LandPlayer getLandPlayer() {
        return landPlayer;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        landPlayer.setAffectedPlayers("player_", builder);
    }
}
