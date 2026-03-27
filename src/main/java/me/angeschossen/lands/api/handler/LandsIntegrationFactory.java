package me.angeschossen.lands.api.handler;

import com.github.angeschossen.pluginframework.api.blockutil.BlockPosition;
import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.inbox.InboxCategory;
import me.angeschossen.lands.api.inbox.InboxMessage;
import me.angeschossen.lands.api.items.ItemType;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.block.LandMainBlock;
import me.angeschossen.lands.api.land.enums.LandType;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.player.Selection;
import me.angeschossen.lands.api.player.combat.CombatTag;
import me.angeschossen.lands.api.role.Role;
import me.angeschossen.lands.api.role.RoleHolder;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * This interface is not intended for direct usage and might change at any time.
 */
public interface LandsIntegrationFactory {

    /**
     * Get or create the integration instance for the given plugin.
     *
     * @param plugin the plugin hooking into Lands
     * @return the integration instance; never null
     */
    @NotNull
    LandsIntegration of(@NotNull Plugin plugin);

    /**
     * Build an item stack for the given item type.
     *
     * @param itemType   the type of item to build
     * @param landPlayer the player for whom the item is built, or {@code null} for a generic item
     * @return the constructed item stack
     */
    ItemStack buildItemStack(@NotNull ItemType itemType, @Nullable LandPlayer landPlayer);

    /**
     * Build a camp creation item for the given player.
     *
     * @param landPlayer the player for whom the item is built, or {@code null} for a generic item
     * @param radius     the camp radius
     * @return the constructed camp item
     */
    ItemStack buildCampItem(@Nullable LandPlayer landPlayer, int radius);

    /**
     * Create a combat tag between two players.
     *
     * @param landsIntegration the integration that created this tag
     * @param attacker         the attacking player
     * @param target           the defending player, or {@code null}
     * @param duration         duration of the combat tag in milliseconds
     * @param showMessage      whether to show a message to the players
     * @return the created combat tag; never null
     */
    @NotNull
    CombatTag combatTagOf(@NotNull LandsIntegration landsIntegration, @NotNull LandPlayer attacker, LandPlayer target, long duration, boolean showMessage);

    /**
     * Create an inbox message for a member holder.
     *
     * @param landsIntegration  the integration creating the message
     * @param memberHolder      the land or nation receiving the message
     * @param category          the inbox category
     * @param key               the message translation key
     * @param placeholders      placeholder names, or {@code null}
     * @param placeholderValues placeholder replacement values
     * @param isAlert           whether this message is an alert
     * @param broadcast         whether to broadcast to online members
     * @param filterReceive     if non-null, only this player receives the message
     * @return the created inbox message; never null
     */
    @NotNull
    InboxMessage inboxMessageOf(@NotNull LandsIntegration landsIntegration, @NotNull MemberHolder memberHolder, @NotNull InboxCategory category, @NotNull String key, @Nullable String[] placeholders, String[] placeholderValues, boolean isAlert, boolean broadcast, @Nullable LandPlayer filterReceive);

    /**
     * Create a new role for a role holder.
     *
     * @param roleHolder the land or nation that owns the role
     * @param name       the name of the new role
     * @return the created role; never null
     * @throws IllegalArgumentException if the name is invalid
     * @throws IllegalStateException    if the role limit is reached
     */
    @NotNull
    Role roleOf(@NotNull RoleHolder roleHolder, @NotNull String name) throws IllegalArgumentException, IllegalStateException;

    /**
     * Create a new land asynchronously.
     *
     * @param name     the land name
     * @param tag      optional short tag, or {@code null}
     * @param landType the type of land to create
     * @param location the initial location used to determine the first chunk
     * @param owner    the owner of the new land
     * @param claim    whether to claim the chunk at the given location
     * @param msg      whether to send feedback messages to the owner
     * @return a future that resolves to the created land
     */
    @NotNull CompletableFuture<? extends Land> landOf(@NotNull String name, @Nullable String tag, @NotNull LandType landType, @NotNull Location location, @NotNull LandPlayer owner, boolean claim, boolean msg);

    /**
     * Create a main block for the given position.
     *
     * @param landPlayer the player placing the block, or {@code null}
     * @param blockPosition the position at which the main block is placed
     * @return the created land main block
     */
    LandMainBlock landMainBlockOf(@Nullable LandPlayer landPlayer, @NotNull BlockPosition blockPosition);

    /**
     * Create a chunk selection for the given player.
     *
     * @param landPlayer the player entering selection mode
     * @param giveTool   whether to give the player the selection tool
     * @param msg        whether to send feedback messages to the player
     * @param isPassive  whether the selection is passive (no active mode UI)
     * @return the created selection; never null
     */
    @NotNull
    Selection selectionOf(@NotNull LandPlayer landPlayer, boolean giveTool, boolean msg, boolean isPassive);
}
