package me.angeschossen.lands.api.war.captureflag;

import com.github.angeschossen.pluginframework.api.blockutil.BlockPosition;
import com.github.angeschossen.pluginframework.api.events.ExpressionEntity;
import me.angeschossen.lands.api.events.war.captureflag.CaptureFlagBreakEvent;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.war.War;
import me.angeschossen.lands.api.war.enums.WarTeam;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public interface CaptureFlag extends ExpressionEntity {
    /**
     * Get the position of this capture flag.
     *
     * @return Coordinates of the capture flag
     */
    @NotNull BlockPosition getPosition();

    /**
     * Set the seconds between each firework spawned. This is an approximate value as fireworks only spawn when the flag is ticked.
     *
     * @param interval if smaller than 1, disabled firework spawning
     */
    void setFireworkSpawnInterval(long interval);

    /**
     * Get the seconds between each firework spawned. This is an approximate value as fireworks only spawn when the flag is ticked.
     *
     * @return if smaller than 1, firework spawning is disabled
     */
    long getLastFireworkSpawned();

    /**
     * Get the team that placed the flag.
     *
     * @return either {@link WarTeam#ATTACKER} or {@link WarTeam#DEFENDER}
     */
    @NotNull
    WarTeam getPlacedByTeam();

    @Nullable WarTeam getProgressingTeam();

    /**
     * Get amount of seconds that the flag must be hold.
     *
     * @return never smaller than 1
     */
    long getSecondsToHold();

    /**
     * Set the amount of seconds that the flag must be hold.
     *
     * @param secondsToHold can't be smaller than 1
     */
    void setSecondsToHold(long secondsToHold);

    /**
     * Modify the capture progress.
     *
     * @param progressor the team that should progress
     * @param progress   either {@link WarTeam#ATTACKER} or {@link WarTeam#DEFENDER}
     * @return false, if progress doesn't change the current progress or progress was cancelled by a 3rd party plugin
     */
    boolean modifyProgress(@NotNull WarTeam progressor, float progress);

    /**
     * Break this flag.
     *
     * @param player   if a player broke that flag
     * @param reward   should the team be rewarded
     * @param captured was it captured?
     * @param exlosion create any explosion?
     * @param reason   reason of removal
     * @return false, if a 3rd party plugin cancelled the removal
     */
    CompletableFuture<Boolean> breakCaptureFlag(@Nullable LandPlayer player, boolean reward, boolean captured,
                                                boolean exlosion, CaptureFlagBreakEvent.BreakReason reason);

    /**
     * Break this flag.
     *
     * @param player   if a player broke that flag
     * @param reward   should the team be rewarded
     * @param captured was it captured?
     * @param reason   reason of removal
     * @return false, if a 3rd party plugin cancelled the removal
     */
    CompletableFuture<Boolean> breakCaptureFlag(@Nullable LandPlayer player, boolean reward, boolean captured, boolean end,
                                                boolean explosion, CaptureFlagBreakEvent.BreakReason reason);

    /**
     * Trigger code execution whenever the flag is unloaded.
     * This is only relevant, if you implement your own capture flag.
     */
    void atUnload();

    /**
     * Get the war to which this capture flag belongs.
     *
     * @return The war to which this capture flag belongs
     */
    @NotNull War getWar();
}
