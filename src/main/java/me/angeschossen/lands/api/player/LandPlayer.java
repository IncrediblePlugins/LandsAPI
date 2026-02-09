package me.angeschossen.lands.api.player;

import com.github.angeschossen.pluginframework.api.events.ExpressionEntity;
import com.github.angeschossen.pluginframework.api.limit.holder.LimitHolder;
import com.github.angeschossen.pluginframework.api.player.PlayerData;
import me.angeschossen.lands.api.flags.type.PlayerFlag;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.limits.Limitation;
import me.angeschossen.lands.api.player.chat.ChatMode;
import me.angeschossen.lands.api.player.combat.CombatTag;
import me.angeschossen.lands.api.player.invite.Invite;
import me.angeschossen.lands.api.war.War;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;

public interface LandPlayer extends OfflinePlayer, ExpressionEntity, PlayerData, LimitHolder {

    Object sendMessage(String key, String[] p, String[] v);

    /**
     * Set the current chat mode. See {@link ChatMode} for more info.
     *
     * @param chat The mode or null, to it disable completely
     */
    void setChatMode(@Nullable ChatMode chat);

    /**
     * Get the current chat mode. See {@link ChatMode} for more info.
     *
     * @return null, if none
     */
    @Nullable
    ChatMode getChatMode();

    /**
     * Toggle a personal flag for this player.
     *
     * @param flag The flag to toggle
     * @return true, if the new state equals flag set
     */
    boolean toggleFlag(@NotNull PlayerFlag flag);

    /**
     * Check if a personal flag is set.
     *
     * @param flag The flag
     * @return true, if the flag is set
     */
    boolean hasFlag(@NotNull PlayerFlag flag);

    /**
     * Get the current combat tag.
     *
     * @return null, if the player isn't in combat or the server doesn't have combat tags enabled and no 3rd party plugin set one.
     */
    @Nullable
    CombatTag getCombatTag();

    /**
     * Get a players edit land, while respecting the server's config. Depending on the server's config,
     * this is either the /lands edit land or the land in which the player is currently standing.
     *
     * @param sendMessage if true, the player will receive a message, if they're not part of any land or if the land at their current position isn't claimed
     * @return null, if has no edit land selected or no land at the players current position
     */
    @Nullable
    Land getEditLand(boolean sendMessage);

    /**
     * Use {@link #getInvite(Land)} instead.
     * Get received invite of a specific land.
     *
     * @param landName Name of the land. Shouldn't include color codes
     * @return null, if no invite of a land with this name was found
     */
    @Deprecated
    @Nullable
    Invite getInvite(@NotNull String landName);

    /**
     * Get invite that was sent by a specific land.
     *
     * @param land The land that sent the invite to this player
     * @return null, if the land didn't send any invite to this player
     */
    @Nullable
    Invite getInvite(@NotNull Land land);

    /**
     * Get all received invites.
     *
     * @return All received invites
     */
    @NotNull
    Collection<? extends Invite> getInvites();

    /**
     * Use {@link #getLands()} instead.
     * Get land where the player is member in.
     *
     * @param landName Name or displayname of land
     * @return Land or null, if not member
     */
    @Deprecated
    @Nullable
    Land getLand(@NotNull String landName);

    /**
     * Get a random land, which the player owns.
     *
     * @return null, if player doesn't own a land
     */
    @Nullable
    Land getOwningLand();

    /**
     * Get the Bukkit player.
     *
     * @return Bukkit player
     */
    Player getPlayer();

    /**
     * Get current the current selection. The selection mode is entered
     * by executing "/lands selection".
     *
     * @return null, if the player isn't in selection mode
     */
    @Nullable
    Selection getSelection();

    /**
     * Get the amount of chunks that a land will be able to claim additionally, if
     * this player is part of a land. This amount is related to the lands.chunks.support.number permission.
     *
     * @return Max amount of support claims
     */
    int getSupportClaimsPerLand();

    /**
     * Get a collection of wars the player is engaged in.
     *
     * @return All wars the player is currently engaged in.
     */
    @NotNull
    Set<? extends War> getWars();

    /**
     * Use {@link #getInvite(Land)} instead.
     * Check if the player invited from a land.
     *
     * @param landName Name of land
     * @return false, if no invite from that land found
     */
    @Deprecated
    boolean hasInvite(@NotNull String landName);

    /**
     * Is the player participating in a war?
     *
     * @return true, if player is in taking part in any war
     */
    boolean isInWar();

    /**
     * Check if the player is participating in a specific war.
     *
     * @param war The war
     * @return true, if they're participating in this war.
     */
    boolean isInWar(@NotNull War war);

    /**
     * Check if the player owns any land.
     *
     * @return false, if players doesn't own any land
     */
    boolean ownsLand();

    /**
     * Set the edit land. This is the same as executing "/lands edit".
     * Following commands will be executed for this land.
     *
     * @param land Land to select for the edit mode.
     */
    void setEditLand(@Nullable Land land);
}
