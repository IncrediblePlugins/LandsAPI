package me.angeschossen.lands.api.memberholder;

import com.github.angeschossen.applicationframework.api.util.ULID;
import com.github.angeschossen.pluginframework.api.events.ExpressionEntity;
import com.github.angeschossen.pluginframework.api.exceptions.NameAlreadyTakenException;
import com.github.angeschossen.pluginframework.api.holder.Changeable;
import com.github.angeschossen.pluginframework.api.player.PlayerData;
import com.github.angeschossen.pluginframework.api.player.PlayerDataBase;
import me.angeschossen.lands.api.events.LandChatEvent;
import me.angeschossen.lands.api.holders.BalanceHolder;
import me.angeschossen.lands.api.inbox.InboxCategory;
import me.angeschossen.lands.api.inbox.InboxMessage;
import me.angeschossen.lands.api.levels.Level;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.relations.Relation;
import me.angeschossen.lands.api.war.War;
import me.angeschossen.lands.api.war.WarStats;
import me.angeschossen.lands.api.war.enums.WarTeam;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface MemberHolder extends BalanceHolder, ExpressionEntity, CMDTarget, Changeable {
    /**
     * Add an amount of seconds to the warshiled.
     *
     * @param seconds If negative, the seconds will be subtracted from the warshield
     */
    void addWarshield(long seconds);

    /**
     * Get the server name.
     *
     * @return server name, on which the land or nation was initially created
     */
    @NotNull
    String getServerName();

    /**
     * Get the globally unique ID. This ID is unique across all servers, similar to UUID.
     *
     * @return Universally unique lexicographically sortable identifier
     */
    @NotNull
    ULID getULID();

    /**
     * Calculate the level.
     *
     * @param executeCmds Execute level-up or level-down configured "reward" commands?
     * @return the new level. Result of completeablefuture will be null, if level didn't change
     */
    CompletableFuture<? extends Level> calculateLevel(boolean executeCmds);

    /**
     * Check if it still exists.
     *
     * @return false, if it was deleted meanwhile
     */
    boolean exists();

    /**
     * Get all allies.
     *
     * @return Current allies
     */
    @NotNull
    Collection<? extends MemberHolder> getAllies();

    /**
     * Get progress (not percentage) of the cached requirement.
     * See {@link me.angeschossen.lands.api.levels.requirement.CachedRequirement}
     *
     * @param requirement The identification of the cached requirement
     * @return The progress (not percentage) of the cached requirement
     */
    float getCachedRequirement(@NotNull String requirement);

    /**
     * Get the amount of chunks.
     *
     * @return Amount of chunks
     */
    int getChunksAmount();

    /**
     * Get time of last capture flag placement.
     *
     * @return time in milliseconds
     */
    long getLastCaptureFlagPlacement();

    /**
     * Get the name, including colors codes.
     *
     * @return Name with color codes included
     */
    @NotNull
    String getColorName();

    /**
     * Get the timestamp of when it was created.
     *
     * @return Time in milliseconds
     */
    long getCreationTime();

    /**
     * Get the time of creation.
     *
     * @return timestamp of creation
     */
    @NotNull
    Timestamp getCreatedAt();

    /**
     * Get all enemies.
     *
     * @return Current enemies
     */
    @NotNull
    Collection<? extends MemberHolder> getEnemies();

    /**
     * Used for expressions and their variables in events.yml.
     *
     * @return Prefix
     */
    String getExpressionPrefix();

    /**
     * Get numerical identification.
     *
     * @return ID
     * @deprecated Use {@link #getULID()} instead.
     */
    @Deprecated
    int getId();

    /**
     * Get all inbox messages.
     *
     * @return All inbox messages
     */
    @NotNull
    java.util.List<? extends InboxMessage> getInbox();

    /**
     * Filter the inbox by a specific category.
     *
     * @param category The category
     * @return The filtered inbox
     */
    @NotNull
    List<? extends InboxMessage> getInbox(@NotNull InboxCategory category);

    /**
     * Get the current level.
     *
     * @return The current level
     */
    @NotNull
    Level getLevel();

    /**
     * Get the amount of players.
     *
     * @return Total amount of players
     */
    int getMembersAmount();

    @Nullable String getTag();

    @Nullable String getColorTag();

    /**
     * Get the name without colors codes. To have color codes included use {@link #getColorName()}.
     *
     * @return The name with color codes included
     */
    @NotNull
    String getName();

    /**
     * Get online players.
     *
     * @return Online players
     */
    @NotNull
    Collection<? extends LandPlayer> getOnlineLandPlayers();

    /**
     * Get online Bukkit players.
     *
     * @return Online Bukkit players
     */
    @NotNull
    Collection<Player> getOnlinePlayers();

    /**
     * Get the owner.
     *
     * @return UUID of the owner
     */
    @NotNull
    UUID getOwnerUID();

    /**
     * Get the relation of a player.
     *
     * @param playerUID The player
     * @return The relation of this player
     */
    @NotNull
    Relation getRelation(@NotNull UUID playerUID);

    /**
     * Get stats of all previous wars.
     *
     * @return Stats of all previous wars
     */
    @NotNull
    WarStats getStats();

    /**
     * Get all trusted players
     *
     * @return All trusted players
     */
    @NotNull
    Collection<UUID> getTrustedPlayers();

    /**
     * Get the type.
     *
     * @return Type
     */
    @NotNull
    HolderType getType();

    /**
     * Get the current war
     *
     * @return null, if currently not engaged in any war or the war hasn't started yet
     */
    @Nullable
    War getWar();

    /**
     * Use {@link #getWarName(PlayerDataBase)} instead.
     *
     * @return The colored name with prefix
     */
    @Deprecated
    String getWarName();

    /**
     * Depending on the type ({@link #getType()}), this will return the colored name ({@link #getColorName()}) with a prefix.
     * Example: "Land Test" or "Nation Test". This depends on the servers language file.
     *
     * @param landPlayer returned value may depend on the locale of the player
     * @return The colored name with prefix
     */
    String getWarName(@Nullable PlayerDataBase landPlayer);

    /**
     * Get the team of the current war.
     *
     * @return null, if not engaged in any war or the war hasn't started yet
     */
    @Nullable
    WarTeam getWarTeam();

    /**
     * Get the amount of seconds left for the warshield.
     *
     * @return Warshield that is left in seconds
     */
    long getWarshield();

    /**
     * Get the webmap border color. Used for dynmap etc.
     *
     * @return null, if there's no color in the colored name ({@link #getColorName()}).
     */
    @Nullable
    Integer getWebMapBorderColor();

    /**
     * Get the webmap fill color. Used for dynmap etc.
     *
     * @return null, if there's no color in the colored name ({@link #getColorName()}).
     */
    @Nullable
    Integer getWebMapFillColor();

    /**
     * Check if another memberholder is part of this one.
     * Mainly used for nations to check if a land is part of their nation.
     *
     * @param entity The memberholder
     * @return false, if the memberholder is not contained by this memberholder
     */
    boolean hasWarEntity(@NotNull MemberHolder entity);

    /**
     * Check if there's any war shield time left.
     *
     * @return false, if {@link #getWarshield()} returns 0
     */
    boolean hasWarshield();

    /**
     * Check if memberHolder has an alliance with this one.
     *
     * @param memberHolder The target
     * @return true, if they're allys
     */
    boolean isAlly(@NotNull MemberHolder memberHolder);

    /**
     * Check if the memberHolder is an enemy to this one.
     *
     * @param memberHolder The target
     * @return true, if they're enemies
     */
    boolean isEnemy(@NotNull MemberHolder memberHolder);

    /**
     * Get the relationship between lands or nations.
     *
     * @param memberHolder the other land or nation
     * @return never null
     */
    @NotNull Relation getRelation(@NotNull MemberHolder memberHolder);

    /**
     * Check if they're in an active war.
     *
     * @return false, if they're not engaged in a war or the war hasn't started yet
     */
    boolean isInWar();

    /**
     * Check if an level requirement is already calculated and cached.
     *
     * @param requirement The identification of the requirement
     * @return false, if the requirement progress isn't cached
     */
    boolean isRequirementCached(@NotNull String requirement);

    /**
     * Use {@link #isTrusted(UUID)} instead.
     * Check if an player is trusted.
     *
     * @param landPlayer The player
     * @return false, if the player isn't trusted
     */
    @Deprecated
    boolean isTrusted(@NotNull LandPlayer landPlayer);

    /**
     * Check if an player is trusted.
     *
     * @param uuid The player's UUID
     * @return false, if the player isn't trusted
     */
    boolean isTrusted(@NotNull UUID uuid);

    /**
     * Check if in case of a war, the terretory of this land or nation will be influenced by the block break list etc. in the wars.yml configuration.
     *
     * @return true, if this land or nation is a war field
     */
    boolean isWarField();

    /**
     * Leave the current war or abort the received war declaration.
     *
     * @return false, they aren't engaged in a war and haven't received a war declaration
     */
    @NotNull
    CompletableFuture<Boolean> leaveWar();

    /**
     * Update the progress of a cached level requirement.
     *
     * @param requirement        the identification of the requirement
     * @param modify             can be negative
     * @param allowNegative      allow the result to be negative?
     * @param levelRecalculation should the level be recalculated? Alternatively you can call {@link #calculateLevel(boolean)}
     * @return The result / progress value
     */
    @NotNull
    CompletableFuture<@Nullable Float> modifyRequirementCache(@NotNull String requirement, float modify, boolean allowNegative, boolean levelRecalculation);

    /**
     * Remove an inbox message from the inbox.
     *
     * @param message message to remove
     */
    void removeInboxMessage(@NotNull InboxMessage message);

    /**
     * Send A message to online players of this land or nation.
     *
     * @param playerUUID    The sender
     * @param message       The message
     * @param messageSource The source of the message
     */
    void sendMessage(@NotNull UUID playerUUID, @NotNull String message, LandChatEvent.MessageSource messageSource);

    /**
     * Set a new name.
     *
     * @param name The new name. Can include color codes.
     * @return false, if a 3rd party plugin cancelled this name update.
     * @throws NameAlreadyTakenException If the name is already taken
     */
    boolean setName(@Nullable LandPlayer landPlayer, @NotNull String name) throws NameAlreadyTakenException, IllegalArgumentException;

    /**
     * Set the amount of seconds for the war shield.
     *
     * @param seconds Amount of seconds for the war shield
     */
    void setWarShield(long seconds);

    /**
     * Update the level requirement progress
     *
     * @param requirement Identification of the requirement
     * @param val         The new value
     * @throws IllegalArgumentException If this requirement doesn't exist
     */
    void updateRequirementCache(@NotNull String requirement, float val) throws IllegalArgumentException;

    /**
     * Update the level requirement progress
     *
     * @param requirement identification of the requirement
     * @param val         the new value
     * @param levelCalc   if the level should be recalculated after updating this requirement. Alternatively you can call {@link #calculateLevel(boolean)}
     * @throws IllegalArgumentException If this requirement doesn't exist
     */
    void updateRequirementCache(@NotNull String requirement, float val, boolean levelCalc) throws IllegalArgumentException;
}
