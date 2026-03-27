package me.angeschossen.lands.api.land.block;

import com.github.angeschossen.pluginframework.api.blockutil.BlockPosition;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.player.LandPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the main block of a land claim, which acts as its physical anchor.
 * A land can have at most one main block.
 */
public interface LandMainBlock extends LandBlock {

    /**
     * Create a mainblock at this position.
     *
     * @param landPlayer the player that creates the block. null, if no player involved
     * @param position   the position of the mainblock
     * @return null, if a 3rd party plugin cancels the placement of the mainblock
     * @throws IllegalStateException    if this isn't called sync, the chunk of the position isn't loaded or the land already has a mainblock
     * @throws IllegalArgumentException if there's no claimed land at the position or the land already has a mainblock
     */
    @Nullable
    static LandMainBlock of(@Nullable LandPlayer landPlayer, @NotNull BlockPosition position) throws IllegalArgumentException, IllegalStateException {
        return APIHandler.getLandsIntegrationFactory().landMainBlockOf(landPlayer, position);
    }
}
