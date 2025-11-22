package me.angeschossen.lands.api.land.block;

import com.github.angeschossen.pluginframework.api.blockutil.impl.BlockPosition;
import me.angeschossen.lands.api.land.Container;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Special blocks that can be placed inside a land.
 */
public interface LandBlock {
    /**
     * Get the unique Id of this block.
     *
     * @return unique id
     */
    @NotNull String getId();

    /**
     * Get the container in which the landblock is placed in.
     *
     * @return never null. A landblock needs to be placed inside a land
     */
    @NotNull Container getContainer();

    /**
     * Get the center of the structure.
     *
     * @return center of the structure
     */
    @NotNull
    BlockPosition getCenter();

    /**
     * Check if the structure contains a block coordinate.
     *
     * @param x Block x
     * @param y Block y
     * @param z Block z
     * @return true, if the block belongs to the structure
     */
    boolean containsBlock(int x, int y, int z);

    /**
     * Get the type of this block.
     *
     * @return type of the block
     */
    @NotNull LandBlockType getLandBlockType();

    /**
     * @param done       will be executed once all blocks are removed
     * @param delayTicks remove a block each x ticks. So if the landblock
     *                   consists of 5 blocks it will take 5 * x ticks to remove the complete landblock.
     * @return false, if a 3rd party plugin cancelled the removal of the landblock
     */
    boolean remove(@Nullable Runnable done, int delayTicks);
}
