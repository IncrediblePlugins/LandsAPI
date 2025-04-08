package me.angeschossen.lands.api.events.land.claiming;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.events.land.LandCancellableEvent;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a land owner accepts a merge request.
 */
public class LandMergeEvent extends LandCancellableEvent {
    public static HandlerList handlerList = new HandlerList();
    private final @NotNull Land toMerge;

    /**
     * Constructor for this event.
     *
     * @param requester  involved land
     * @param landPlayer involved player
     */
    public LandMergeEvent(@NotNull Land requester, @NotNull Land toMerge, LandPlayer landPlayer) {
        super(requester, landPlayer);

        this.toMerge = Checks.requireNonNull(toMerge, "mergedLand");
    }

    /**
     * Get the merged land.
     *
     * @return land that gets merged into {@link #getLand()}
     */
    public @NotNull Land getToMerge() {
        return toMerge;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

}
