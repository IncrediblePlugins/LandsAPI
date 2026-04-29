package me.angeschossen.lands.api.war.captureflag;

import com.github.angeschossen.pluginframework.api.blockutil.BlockPosition;
import com.github.angeschossen.pluginframework.api.events.ExpressionEntity;
import me.angeschossen.lands.api.events.war.captureflag.CaptureFlagBreakEvent;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.war.TeamGiver;
import me.angeschossen.lands.api.war.enums.WarTeam;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * Represents a capture flag placed during a war.
 * Capture flags are placed by the attacking team within enemy territory.
 * The attacking team must hold the flag to capture it; defenders can break it to reclaim their territory.
 */
public interface CaptureFlag extends ExpressionEntity {

    /**
     * Get the type of capture flag.
     *
     * @return never null
     */
    @NotNull CaptureFlagType getType();

    /**
     * Get the position of this capture flag.
     *
     * @return Coordinates of the capture flag
     */
    @NotNull BlockPosition getPosition();

    /**
     * Get the area that can be captured.
     *
     * @return the capture area will never be null, but can be empty if the flag hasn't been initialized yet after a server restart
     */
    @NotNull CaptureArea getCaptureArea();

    /**
     * Set the seconds between each firework spawned. This is an approximate value as fireworks only spawn when the flag is ticked.
     *
     * @param interval if smaller than 1, disabled firework spawning
     */
    void setFireworkSpawnInterval(long interval);

    /**
     * Get the configured interval in seconds between firework spawns.
     * This mirrors the value set by {@link #setFireworkSpawnInterval(long)}.
     * The actual interval is approximate because fireworks are only spawned when the flag is ticked.
     *
     * @return the interval in seconds; a value smaller than 1 means firework spawning is disabled
     */
    long getLastFireworkSpawned();

    /**
     * Get the team that placed the flag.
     *
     * @return either {@link me.angeschossen.lands.api.war.enums.WarTeam#ATTACKER} or {@link me.angeschossen.lands.api.war.enums.WarTeam#DEFENDER}
     */
    @NotNull
    me.angeschossen.lands.api.war.enums.WarTeam getPlacedByTeam();

    /**
     * Get the team that is currently capturing.
     *
     * @return if returns {@link me.angeschossen.lands.api.war.enums.WarTeam#NEUTRAL}, no team is capturing
     */
    @NotNull me.angeschossen.lands.api.war.enums.WarTeam getCapturingTeam();


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
     * @param player    the player who broke the flag, or {@code null} if broken by a non-player cause
     * @param reward    {@code true} to reward the breaking team with points
     * @param captured  {@code true} if the flag was captured before being broken
     * @param explosion {@code true} to trigger a visual explosion effect
     * @param reason    the reason the flag was broken
     * @return a future completing with {@code false} if a third-party plugin cancelled the removal
     */
    CompletableFuture<Boolean> breakCaptureFlag(@Nullable LandPlayer player, boolean reward, boolean captured,
                                                boolean explosion, CaptureFlagBreakEvent.BreakReason reason);

    /**
     * Break this flag.
     *
     * @param player   if a player broke that flag
     * @param reward   should the team be rewarded
     * @param captured was it captured?
     * @param end      whether the war has ended, which triggered the removal
     * @param explosion whether the flag was destroyed by an explosion
     * @param reason   reason of removal
     * @return false, if a 3rd party plugin cancelled the removal
     */
    CompletableFuture<Boolean> breakCaptureFlag(@Nullable LandPlayer player, boolean reward, boolean captured, boolean end,
                                                boolean explosion, CaptureFlagBreakEvent.BreakReason reason);

    /**
     * Called by Lands when the capture flag is unloaded (e.g. on chunk unload or server shutdown).
     * Only relevant when implementing a custom capture flag type.
     */
    void atUnload();

    /**
     * Get the war to which this capture flag belongs.
     *
     * @return The war to which this capture flag belongs
     */
    @NotNull TeamGiver getTeamGiver();
}
