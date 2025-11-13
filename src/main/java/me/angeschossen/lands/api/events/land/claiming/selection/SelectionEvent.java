package me.angeschossen.lands.api.events.land.claiming.selection;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.events.land.LandEvent;
import me.angeschossen.lands.api.land.ChunkCoordinate;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.player.Selection;
import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public abstract class SelectionEvent extends LandEvent implements Cancellable {
    protected final Selection selection;
    private boolean cancelled;
    private final Set<? extends ChunkCoordinate> affectedChunks;

    /**
     * Constructor
     *
     * @param land           land for which the selection is about to become unclaimed or claimed
     * @param landPlayer     player that triggered the selection unclaim or claim
     * @param selection      the selection to be unclaimed or claimed
     * @param affectedChunks the collection of chunks that are affected by this selection
     *                       In contrast to {@link Selection#getChunks()} this doesn't include already unclaimed or claimed chunks etc.
     */
    public SelectionEvent(@NotNull Land land, @Nullable LandPlayer landPlayer, @NotNull Selection selection, @NotNull Set<? extends me.angeschossen.lands.api.land.ChunkCoordinate> affectedChunks) {
        super(land, landPlayer);

        Checks.requireNonNull(selection, "selection");
        Checks.requireNonNull(affectedChunks, "affectedChunks");

        if (!selection.isValid(false)) {
            throw new IllegalArgumentException("The selection isn't valid");
        }

        this.selection = selection;
        this.affectedChunks = affectedChunks;
    }

    /**
     * Get the collection of chunks that is affected by this selection.
     *
     * @return this doesn't include already unclaimed or claimed chunks or chunks that the player isn't allowed to unclaim or claim
     */
    @NotNull
    public final Set<? extends me.angeschossen.lands.api.land.ChunkCoordinate> getAffectedChunks() {
        return affectedChunks;
    }

    /**
     * Get the involved selection. To retrieve chunks that are going to be unclaimed or claimed use {@link #getAffectedChunks()}.
     *
     * @return never null
     */
    @NotNull
    public final Selection getSelection() {
        return selection;
    }

    /**
     * Check if a specific chunk is going to be unclaimed or claimed.
     *
     * @param world The world
     * @param x     chunk X identifier
     * @param z     chunk Z identifier
     * @return true, if {@link #getAffectedChunks()} contains this chunk
     */
    public final boolean isChunkAffected(World world, int x, int z) {
        World w = selection.getPos1().getWorld();
        return world.equals(w) && affectedChunks.contains(new me.angeschossen.lands.api.blockworks.ChunkCoordinate(x, z));
    }

    @Override
    public final boolean isCancelled() {
        return cancelled;
    }

    @Override
    public final void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
