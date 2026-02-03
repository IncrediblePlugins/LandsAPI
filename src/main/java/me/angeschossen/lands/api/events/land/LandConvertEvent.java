package me.angeschossen.lands.api.events.land;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.enums.LandType;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called each time a land is converted into an admin land or back into a player land.
 *
 * @see LandType
 */
public class LandConvertEvent extends LandEvent implements Cancellable {
    public static HandlerList handlerList = new HandlerList();
    private final @NotNull LandType landType;
    private boolean cancelled;

    /**
     * Create an instance of this event.
     *
     * @param land       land that is about to converted into a different type
     * @param landPlayer The player that triggered this conversion.
     *                   if null this is initiated by the plugin itself.
     * @param landType   Type into which the land is about to be converted into
     */
    public LandConvertEvent(@NotNull Land land, @Nullable LandPlayer landPlayer, @NotNull LandType landType) {
        super(land, landPlayer);

        this.landType = landType;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        builder.put("type", landType);
    }

    /**
     * Get the new land type that the land is about to get converted into
     * @return never null
     */
    @NotNull
    public LandType getLandType() {
        return landType;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
