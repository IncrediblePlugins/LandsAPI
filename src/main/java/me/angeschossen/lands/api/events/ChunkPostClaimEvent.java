package me.angeschossen.lands.api.events;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.LandWorld;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called after claiming a chunk.
 */
public class ChunkPostClaimEvent extends LandEvent {

    public static HandlerList handlerList = new HandlerList();

    private final int x, z;
    private final @NotNull LandWorld world;

    public ChunkPostClaimEvent(@Nullable LandPlayer landPlayer, @NotNull Land land, @NotNull LandWorld landWorld, int x, int z) {
        super(land, landPlayer);

        Checks.requireNonNull(landWorld, "landWorld");
        this.x = x;
        this.world = landWorld;
        this.z = z;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
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
     * Get the world in which the chunk is located in.
     *
     * @return world
     */
    @NotNull
    public LandWorld getWorld() {
        return world;
    }

    /**
     * Get chunk x.
     *
     * @return chunk x
     */
    public int getX() {
        return x;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public String toString() {
        return "ChunkPostClaimEvent{land=" + land.toString() +
                ",world=" + world.toString() +
                ",x=" + x +
                ",z=" + z +
                "}";
    }
}
