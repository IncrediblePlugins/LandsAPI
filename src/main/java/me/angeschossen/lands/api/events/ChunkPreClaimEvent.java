package me.angeschossen.lands.api.events;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.LandWorld;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called before a chunk is being claimed.
 */
public class ChunkPreClaimEvent extends LandEvent implements Cancellable {

    public static HandlerList handlerList = new HandlerList();
    private final int x;
    private final int z;
    private final @NotNull LandWorld world;
    private boolean cancelled;

    public ChunkPreClaimEvent(@NotNull Land land, @Nullable LandPlayer landPlayer, @NotNull LandWorld world, int x, int z) {
        super(land, landPlayer);

        Checks.requireNonNull(world, "world");
        this.world = world;
        this.x = x;
        this.z = z;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    /**
     * Get chunk x.
     *
     * @return chunk x
     */
    public int getX() {
        return x;
    }

    /**
     * Get chunk z.
     *
     * @return chunk z
     */
    public int getZ() {
        return z;
    }

    /**
     * Use {@link #getWorld()} instead.
     *
     * @return world name
     */
    @Deprecated
    @NotNull
    public String getWorldName() {
        return world.getName();
    }

    /**
     * Get the world in which the chunk is located in.
     *
     * @return world in which the chunk is located
     */
    @NotNull
    public LandWorld getWorld() {
        return world;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public String toString() {
        return "ChunkPreClaimEvent{land=" + land.toString() +
                ",world=" + world.toString() +
                ",x=" + x +
                ",z=" + z +
                "}";
    }
}
