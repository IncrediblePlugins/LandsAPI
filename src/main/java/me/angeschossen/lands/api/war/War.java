package me.angeschossen.lands.api.war;

import com.github.angeschossen.pluginframework.api.events.ExpressionEntity;
import me.angeschossen.lands.api.flags.type.RoleFlag;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.war.captureflag.CaptureFlag;
import me.angeschossen.lands.api.war.player.WarPlayer;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface War extends ExpressionEntity, WarState, TeamGiver {

    void updateStats(@NotNull ActiveWarStats statsAtt,@NotNull  ActiveWarStats statsDef);

    /**
     * Add an online player to this war.
     * This is called by Lands.
     *
     * @param landPlayer the player to add
     */
    void addOnlinePlayer(@NotNull LandPlayer landPlayer);

    /**
     * End this war.
     *
     * @return never null
     */
    @NotNull
    CompletableFuture<Void> end();

    /**
     * End this war.
     *
     * @param winner      Who should be the winner? {@link #getDefender()} or {@link #getAttacker()}.
     * @param surrendered If true, the other team surrendered
     * @param reward      If true and surrendered is false, rewards from wars.yml are given to the winner
     */
    CompletableFuture<Void> end(@NotNull MemberHolder winner, boolean surrendered, double reward);

    /**
     * Get stats of this war for the attacker {@link #getAttacker()}.
     *
     * @return Stats of the attacker
     */
    @NotNull
    ActiveWarStats getAttackerStats();

    /**
     * Get online players of the attacker.
     *
     * @return Online players of the attacker
     */
    @NotNull Collection<? extends WarPlayer> getOnlineAttackers();

    /**
     * Get all capture flags of this war.
     *
     * @return never null
     */
    @NotNull
    Collection<? extends CaptureFlag> getCaptureFlags();

    /**
     * Get stats of this war for the defender {@link #getDefender()}.
     *
     * @return Stats of the defender
     */
    @NotNull
    ActiveWarStats getDefenderStats();

    /**
     * Get online players of the defender.
     *
     * @return Online players of the defender
     */
    @NotNull Collection<? extends WarPlayer> getOnlineDefenders();

    /**
     * Get all capture flags that were placed by a specific team.
     *
     * @param warTeam the team
     * @return all capture flags placed by a specific team
     */
    @Nullable Collection<? extends CaptureFlag> getPlacedByTeam(@NotNull me.angeschossen.lands.api.war.enums.WarTeam warTeam);

    /**
     * @param winner The assumed winner
     * @return Configured robbery reward from wars.yml. The reward depends on the other teams balance
     */
    double getReward(@Nullable MemberHolder winner);

    /**
     * Get the war stats of a specific team
     *
     * @param warTeam the team
     * @return stats of a specific team
     */
    @NotNull
    WarStats getStats(@NotNull me.angeschossen.lands.api.war.enums.WarTeam warTeam);

    /**
     * Get milliseconds when of date at which the war started
     *
     * @return milliseconds when of date at which the war started
     */
    Timestamp getStarted();

    /**
     * Set started time. See {@link #getStarted()} for more info.
     *
     * @param started milliseconds
     */
    void setStarted(long started);

    /**
     * Get information about player in this war.
     *
     * @param landPlayer the player
     * @return null, if player doesn't participate in this war
     */
    @Nullable
    WarPlayer getWarPlayer(@NotNull LandPlayer landPlayer);

    /**
     * Get the current winner.
     *
     * @return The current winner
     */
    @Nullable
    MemberHolder getWinner();

    /**
     * Check if the war allows player to do specific actions during war in the enemies lands.
     *
     * @param land        the enemy land to check for
     * @param landPlayer  the player doing the action
     * @param roleFlag    the flag
     * @param material    material of block. null if no block involved
     * @param sendMessage should a denied message be sent?
     * @return false, if player not allowed to do this in the enemy land
     */
    boolean hasFlag(@NotNull Land land, @NotNull LandPlayer landPlayer, @NotNull RoleFlag roleFlag, @Nullable Material material, boolean sendMessage);

    /**
     * Check if points to win from wars.yml is reached or war timeout from wars.yml is reached.
     *
     * @return true, if points to win is reached by the current {@link #getWinner()} or war timeout is reached.
     * The war will end in a matter of seconds of this method returns true
     */
    boolean isEndingSoon();

    /**
     * Check if a player is participating in this war.
     *
     * @param player the player to check
     * @return false, if player isn't participating
     */
    boolean isParticipating(@NotNull LandPlayer player);

    /**
     * Remove an online player from this war.
     *
     * @param player  the player that is logging out
     * @param logging related to cooldown_logging option in config.yml
     */
    void removeOnlinePlayer(@NotNull LandPlayer player, boolean logging);
}
