package me.angeschossen.lands.api.war.captureflag;

import com.github.angeschossen.pluginframework.api.blockutil.BlockPosition;
import com.github.angeschossen.pluginframework.api.events.ExpressionEntity;
import me.angeschossen.lands.api.blockworks.BoundingBox;
import me.angeschossen.lands.api.events.war.captureflag.CaptureFlagBreakEvent;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.war.War;
import me.angeschossen.lands.api.war.enums.WarTeam;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Callable;
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

    /**
     * Get the team that is currently capturing.
     *
     * @return if returns {@link WarTeam#NEUTRAL}, no team is capturing
     */
    @NotNull WarTeam getCapturingTeam();

    /**
     * Get the area to capture.
     *
     * @return bounding box that defines the capture area
     */
    @NotNull BoundingBox getBoundingBox();

    /**
     * Get the amount of seconds this capture flag was captured by {@link #getCapturingTeam()}.
     *
     * @return this only contains the time where the team was actively capturing it. If the enemy team starts capturing, this time will be reduced until its 0. Then the time will increase again with a different {@link #getCapturingTeam()}
     */
    long getSecondsHeld();

    /**
     * Get amount of seconds that the flag must be hold by {@link #getCapturingTeam()}.
     * This means that {@link #getCapturingTeam()} must return a specific team for this duration. If the capturing team changes, they loose progress by time.
     *
     * @return never smaller than 1
     */
    long getSecondsToHold();

    /**
     * Set the amount of seconds that the flag must be held by the same team.
     *
     * @param secondsToHold can't be smaller than 1
     */
    void setSecondsToHold(long secondsToHold);

    /**
     * Set callable that is called whenever Lands evaluates the progressing team.
     * This will for example be called when a player leaves the capture area or gets killed.
     *
     * @param callable will be called immediately after set
     */
    void setEvaluateCapturingTeam(@NotNull Callable<@NotNull WarTeam> callable);

    /**
     * Modify the capture progress.
     *
     * @param progress seconds to add or to remove (negative)
     * @return false, if progress doesn't change the current progress or progress was cancelled by a 3rd party plugin
     */
    boolean modifyProgress(long progress);

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
