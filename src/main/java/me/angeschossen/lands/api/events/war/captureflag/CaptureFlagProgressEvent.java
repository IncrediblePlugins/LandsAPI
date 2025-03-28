package me.angeschossen.lands.api.events.war.captureflag;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.events.war.captureflag.base.CaptureFlagCancellableEvent;
import me.angeschossen.lands.api.war.captureflag.CaptureFlag;
import me.angeschossen.lands.api.war.enums.WarTeam;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called whenever a team makes progress at capturing a flag or capturing it back from the attackers.
 */
public class CaptureFlagProgressEvent extends CaptureFlagCancellableEvent {
    public static HandlerList handlerList = new HandlerList();

    private final @NotNull WarTeam progressorTeam;

    /**
     * Create instance of this event.
     *
     * @param captureFlag    the flag that is being captured
     * @param progressorTeam the progressor
     */
    public CaptureFlagProgressEvent(@NotNull CaptureFlag captureFlag, @NotNull WarTeam progressorTeam) {
        super(captureFlag, null);

        this.progressorTeam = Checks.requireNonNull(progressorTeam, "progressorTeam");
    }

    /**
     * Get the team that is capturing the flag.
     *
     * @return if team equals {@link WarTeam#DEFENDER}, the defenders are capturing the flag back. Will never be {@link WarTeam#NEUTRAL}
     */
    public @NotNull WarTeam getProgressorTeam() {
        return progressorTeam;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
