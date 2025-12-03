package me.angeschossen.lands.api.player;

import me.angeschossen.lands.api.blockworks.BlockCoordinate;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.land.ChunkCoordinate;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.claiming.ClaimResult;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiPredicate;

public interface Selection {

    /**
     * Get selection instance.
     * @param landPlayer The player
     * @param giveTool Should the tool be given to the player?
     * @param msg Should progress messages etc. be sent?
     * @param visualization Should the selection be visualized?
     * @return Selection instance
     */
    @NotNull
    static Selection of(@NotNull LandPlayer landPlayer, boolean giveTool, boolean msg, boolean visualization) {
        return APIHandler.getLandsIntegrationFactory().selectionOf(landPlayer, giveTool, msg, visualization);
    }

    /**
     * Is selection complete (pos 1 and pos 2)?
     *
     * @return false, if one position is missing
     */
    boolean isComplete();

    /**
     * Claim all chunks in this selection.
     *
     * @param land The land
     * @param msg  Send success and failure messages?
     * @param cost Should it cost depending on the config?
     * @return The result of the claiming
     * @throws IllegalStateException If the selection isn't valid (or complete)
     */
    @NotNull
    CompletableFuture<ClaimResult> claim(@NotNull Land land, boolean msg, boolean cost) throws IllegalStateException;

    /**
     * Get position one.
     *
     * @return Location of position one
     */
    BlockCoordinate getPos1();

    /**
     * Get position two.
     *
     * @return Location of position two
     */
    BlockCoordinate getPos2();

    /**
     * Set position one
     *
     * @param location Location of position one
     */
    void setPos1(@Nullable Location location);

    /**
     * Set position two.
     *
     * @param location Location of position two
     */
    void setPos2(@Nullable Location location);

    /**
     * Check if this selection is valid.
     *
     * @param sendMessage Send message to the player, if the selection isn't complete?
     * @return false, if the selection isn't complete, both positions are not in the same world or the selection is too big.
     */
    boolean isValid(boolean sendMessage);

    /**
     * Get chunks in this selection.
     *
     * @return Chunks in this selection
     */
    Collection<? extends ChunkCoordinate> getChunks();

    /**
     * Get amount of blocks.
     *
     * @return The amount of blocks in this selection.
     */
    int getChunksAmount();

    /**
     * Check if the selection contains any part of a chunk.
     * @param x Chunk x
     * @param z Chunk z
     * @return true, if the selection contains any port of the provided chunk
     */
    boolean hasChunk(int x, int z);

    /**
     * Get all chunks, of which at least one block is part of this selection.
     * @param predicate Filter chunks
     * @return Chunks of which at least one block is part of this selection and for which the predicate returned true
     */
    Collection<? extends ChunkCoordinate> getChunks(@NotNull BiPredicate<Integer, Integer> predicate);

    /**
     * Disable this selection and the players selection mode.
     */
    void disable();
}
