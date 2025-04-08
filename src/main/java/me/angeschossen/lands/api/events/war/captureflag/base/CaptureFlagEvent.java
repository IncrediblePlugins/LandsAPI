package me.angeschossen.lands.api.events.war.captureflag.base;

import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.war.WarEvent;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.war.captureflag.CaptureFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

/**
 * Used for events that effect a capture flag.
 */
public abstract class CaptureFlagEvent extends WarEvent {
    protected final @Nullable LandPlayer player;
    protected final @NotNull CaptureFlag captureFlag;

    /**
     * Constructor
     *
     * @param captureFlag the affected capture flag
     * @param player      if null, no player is involved in triggering this event
     */
    public CaptureFlagEvent(@NotNull CaptureFlag captureFlag, @Nullable LandPlayer player) {
        super(captureFlag.getWar());

        this.player = player;
        this.captureFlag = captureFlag;
    }

    /**
     * Get the flag that is affected by this event.
     *
     * @return never null
     */
    public @NotNull CaptureFlag getCaptureFlag() {
        return captureFlag;
    }

    /**
     * Get the player involved.
     *
     * @return if null, no player involved
     */
    @Nullable
    public LandPlayer getLandPlayer() {
        return player;
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        super.setAffectedPlayers(builder);

        if (player != null) {
            player.setAffectedPlayers("placer_", builder);
        }
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        super.setExpressionVariables(builder);

        if (player != null) {
            final UUID uuid = player.getUID();
            player.setExpressionVariables("placer_", builder, uuid);
            captureFlag.setExpressionVariables("flag_", builder, uuid);
        }
    }
}
