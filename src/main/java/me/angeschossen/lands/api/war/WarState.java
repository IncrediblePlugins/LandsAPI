package me.angeschossen.lands.api.war;

import com.github.angeschossen.applicationframework.api.util.ULID;
import com.github.angeschossen.pluginframework.api.holder.Changeable;
import com.github.angeschossen.pluginframework.api.player.PlayerDataBase;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.war.declaration.WarDeclaration;
import me.angeschossen.lands.api.war.enums.WarSetting;
import me.angeschossen.lands.api.war.enums.WarStatus;
import me.angeschossen.lands.api.war.enums.WarTeam;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface WarState extends Changeable {

    /**
     * Check if the current server should tick this war.
     * Only one server should return true, if you use Redis.
     *
     * @return true if the current server should tick this war
     */
    boolean shouldServerTick();

    /**
     * Called each time a land or nation object of this war needed to be recreated because of a Redis update.
     *
     * @param prev    the previous memberHolder object
     * @param updated the new memberHolder object
     */
    void onMemberHolderUpdated(@NotNull MemberHolder prev, @NotNull MemberHolder updated);

    /**
     * Broadcast a message
     *
     * @param key    message key of locale file
     * @param p      placeholders
     * @param v      placeholder values
     * @param filter if true, only send to players engaged in this war. If false, send to all players
     */
    void broadcast(String key, String[] p, Function<PlayerDataBase, String[]> v, boolean filter);

    /**
     * Get the land or nation that declared war initially.
     *
     * @return The attacker
     */
    MemberHolder getAttacker();

    /**
     * Get the enemy of a player.
     *
     * @param playerUID the player
     * @return null, if player not engaged in the war
     */
    @Nullable
    MemberHolder getEnemy(UUID playerUID);

    /**
     * Get the opposite team of the land or nation.
     *
     * @param memberHolder Land or nation
     * @return the provided memberHolder if it's not part of any team
     */
    @NotNull
    MemberHolder getEnemy(@NotNull MemberHolder memberHolder);

    /**
     * Get land or nation by opponent team
     *
     * @param warTeam the opponent team
     * @return never null
     */
    @NotNull
    MemberHolder getMemberHolder(@NotNull WarTeam warTeam);

    /**
     * Get the ID of this warstate
     *
     * @return numerical ID
     */
    @Deprecated
    int getId();

    @NotNull
    ULID getULID();

    /**
     * get the current state of the war or declaration
     *
     * @return current state
     */
    WarStatus getState();

    /**
     * Get the team of a land or nation
     *
     * @param entity land or nation
     * @return {@link WarTeam#NEUTRAL}, if not engaged in this war
     */
    @NotNull
    WarTeam getTeam(@NotNull MemberHolder entity);

    /**
     * Get the max tribute a team can set
     *
     * @param warTeam the team to check
     * @return the opponent will need to pay this tribute to the team, if they want to surrender
     */
    double getMaxTribute(@NotNull WarTeam warTeam);

    /**
     * Get required points to win.
     *
     * @return required points to win
     */
    float getPointsToWin();

    /**
     * Get the team the player belongs to.
     *
     * @param player The player
     * @return NEUTRAL, if the player is not related to any land or nation in this war
     */
    @NotNull
    WarTeam getTeam(@NotNull LandPlayer player);

    /**
     * Get the land or nation that was declared war against by the attacker.
     *
     * @return The defender
     */
    @NotNull
    MemberHolder getDefender();

    /**
     * If state represents:
     * {@link War}: Get the time left until the war ends forcefully (no team reached the needed amount of points)
     * {@link WarDeclaration} Time until the war starts
     * {@link me.angeschossen.lands.api.war.declaration.MutualDeclaration} Time until the declaration is automatically being declined.
     *
     * @return Time in seconds
     */
    long getTimeLeft();

    /**
     * Same as {@link #getTimeLeft()}, but formatted
     *
     * @param sender for per player locale support
     * @return may depend on the player's locale
     */
    String getTimeLeftFormatted(@Nullable PlayerDataBase sender);

    /**
     * Check if this war has a specific flag set. They're configured in the config.
     *
     * @param warSetting flag to check
     * @return true, if flag is set
     */
    boolean hasFlag(@NotNull WarSetting warSetting);

    /**
     * Get the tribute that a specific team needs to pay.
     *
     * @param entity the team
     * @return tribute that the entity needs to pay
     */
    double getTribute(@NotNull MemberHolder entity);

    /**
     * Get the tribute that has been set by the initial attacker.
     *
     * @return the defenders need to pay this if they want to surrender
     */
    double getTribute();

    /**
     * Set the tribute that the defenders need to pay, if they surrender
     *
     * @param tribute the tribute to set
     */
    void setTribute(double tribute);

    /**
     * Check if this land or nation takes part in this war.
     *
     * @param entity The land or nation
     * @return true, if it takes part in this war
     */
    boolean isParticipating(@NotNull MemberHolder entity);

    /**
     * Send current info of the war.
     *
     * @param landPlayer if null, sends it to all players that are engaged in the war
     */
    void sendCurrentInfo(@Nullable LandPlayer landPlayer);

    /**
     * Removes the war state from both the attacker and the defender.
     */
    void removeFromBothParties();

    /**
     * Forcefully end this war and delete it.
     *
     * @return never null
     */
    @NotNull
    CompletableFuture<Void> delete();

    /**
     * Let a team surrender.
     *
     * @param surrenderer the surrendering team
     * @param filter      don't the resulting inbox messages to this player
     * @return false, if the surrenderer doesn't have enough money to pay {@link #getTribute(MemberHolder)}
     */
    CompletableFuture<Boolean> surrender(@NotNull MemberHolder surrenderer, @Nullable LandPlayer filter);

    /**
     * Get attacker and defender.
     *
     * @return attacker and defender for iteration
     */
    @NotNull
    MemberHolder[] getEntities();
}
