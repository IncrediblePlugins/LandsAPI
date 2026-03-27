package me.angeschossen.lands.api.player.combat;

import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.player.LandPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an active combat tag between two players.
 * A combat tag prevents players from escaping combat by leaving the server or teleporting.
 * Combat tags are applied to both the attacker and the target for the duration of the tag.
 */
public interface CombatTag {
    /**
     * Create an combat tag and apply it to both players.
     *
     * @param landsIntegration Your instance of {@link LandsIntegration}
     * @param attacker         Initial attacker. Can't be the same as target.
     * @param target           Initial target. Can't be the same as attacker.
     * @param duration         Duration of this combat tag. If lower than 1, the combat tag won't expire and must be removed manually via {@link #remove()}.
     *                         Combat tags do not persist when leaving the server
     * @param showMessage      If false, no message (by default bossbar) will be sent
     * @return CombatTag instance
     */
    static @NotNull CombatTag of(@NotNull LandsIntegration landsIntegration, @NotNull LandPlayer attacker, LandPlayer target, long duration, boolean showMessage) {
        return APIHandler.getLandsIntegrationFactory().combatTagOf(landsIntegration, attacker, target, duration, showMessage);
    }

    /**
     * Get time until combat tag expires.
     * The resets at each hit.
     *
     * @return Expiration of combat tag
     */
    long getTimeLeft();

    /**
     * Get the initial attacker.
     *
     * @return Never null
     */
    @NotNull
    LandPlayer getAttacker();

    /**
     * Get the initial target.
     *
     * @return Never null
     */
    @NotNull
    LandPlayer getTarget();

    /**
     * Get the duration of this combat tag.
     *
     * @return Duration of the combat tag
     */
    long getDuration();

    /**
     * Removes this combat tag from both players.
     */
    void remove();
}
