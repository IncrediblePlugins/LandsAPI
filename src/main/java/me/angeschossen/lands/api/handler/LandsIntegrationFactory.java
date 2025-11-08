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

    @NotNull
    LandsIntegration of(@NotNull Plugin plugin);

    ItemStack buildItemStack(@NotNull ItemType itemType, @Nullable LandPlayer landPlayer);

    ItemStack buildCampItem(@Nullable LandPlayer landPlayer, int radius);

    @NotNull
    CombatTag combatTagOf(@NotNull LandsIntegration landsIntegration, @NotNull LandPlayer attacker, LandPlayer target, long duration, boolean showMessage);

    @NotNull
    InboxMessage inboxMessageOf(@NotNull LandsIntegration landsIntegration, @NotNull MemberHolder memberHolder, @NotNull InboxCategory category, @NotNull String key, @Nullable String[] placeholders, String[] placeholderValues, boolean isAlert, boolean broadcast, @Nullable LandPlayer filterReceive);

    @NotNull
    Role roleOf(@NotNull RoleHolder roleHolder, @NotNull String name) throws IllegalArgumentException, IllegalStateException;

    @NotNull CompletableFuture<? extends Land> landOf(@NotNull String name, @Nullable String tag, @NotNull LandType landType, @NotNull Location location, @NotNull LandPlayer owner, boolean claim, boolean msg);

    LandMainBlock landMainBlockOf(@Nullable LandPlayer landPlayer, @NotNull BlockPosition blockPosition);

    @NotNull
    Selection selectionOf(@NotNull LandPlayer landPlayer, boolean giveTool, boolean msg, boolean visualization);
}
