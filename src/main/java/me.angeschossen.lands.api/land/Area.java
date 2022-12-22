package me.angeschossen.lands.api.land;

import com.github.angeschossen.pluginframework.api.events.ExpressionEntity;
import me.angeschossen.lands.api.flags.type.NaturalFlag;
import me.angeschossen.lands.api.flags.type.RoleFlag;
import me.angeschossen.lands.api.player.Invite;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.role.Role;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

public interface Area extends ExpressionEntity {

    double addTax(double tax);

    boolean banPlayer(UUID uuid);

    boolean canEnter(@NotNull LandPlayer landPlayer, boolean sendMessage);

    @NotNull
    String getColorName();

    @NotNull
    Role getEntryRole();

    @Nullable
    Invite getInvite(@NotNull UUID receiverUUID);

    Land getLand();

    @NotNull
    String getName();

    @NotNull
    UUID getOwnerUID();

    @NotNull
    Role getRole(@NotNull UUID playerUID);

    @NotNull
    Role getRole(@NotNull String name);

    double getTax();

    void setTax(double rent);

    @NotNull Collection<UUID> getTrustedPlayers();

    @NotNull
    Role getVisitorRole();

    boolean hasNaturalFlag(@NotNull NaturalFlag flag);

    boolean hasFlag(@NotNull UUID playerUUID, @NotNull RoleFlag flag);

    boolean hasFlag(@NotNull Player player, @NotNull RoleFlag flag, @Nullable Material material, boolean sendMessage);

    boolean hasFlag(@NotNull Player player, @NotNull RoleFlag roleFlag, boolean sendMessage);

    boolean hasFlag(@NotNull LandPlayer player, @NotNull RoleFlag flag, @Nullable Material material, boolean sendMessage);

    boolean hasFlag(@NotNull LandPlayer player, @NotNull RoleFlag roleFlag, boolean sendMessage);

    boolean isBanned(@NotNull UUID playerUID);

    boolean isDefault();

    boolean isTrusted(UUID playerUID);

    boolean toggleFlag(@NotNull NaturalFlag flag);

    void unbanPlayer(UUID playerUID);
}
