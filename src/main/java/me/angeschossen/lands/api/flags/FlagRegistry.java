package me.angeschossen.lands.api.flags;

import me.angeschossen.lands.api.exceptions.FlagConflictException;
import me.angeschossen.lands.api.flags.enums.FlagModule;
import me.angeschossen.lands.api.flags.enums.RoleFlagCategory;
import me.angeschossen.lands.api.flags.type.NaturalFlag;
import me.angeschossen.lands.api.flags.type.PlayerFlag;
import me.angeschossen.lands.api.flags.type.RoleFlag;
import me.angeschossen.lands.api.flags.type.parent.Flag;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public interface FlagRegistry {

    @NotNull
    me.angeschossen.lands.api.flags.type.parent.Flag<?> register(@NotNull me.angeschossen.lands.api.flags.type.parent.Flag<?> flag) throws FlagConflictException, IllegalArgumentException;

    boolean isValidName(String name);

    @Nullable
    RoleFlag getInteract(@NotNull Block block, @Nullable ItemStack item);

    @Nullable RoleFlag getRole(@NotNull String name);

    @Nullable NaturalFlag getNatural(@NotNull String name);

    @Nullable Flag<?> get(@NotNull String name);

    @NotNull
    Collection<RoleFlag> getRoleFlags();

    @NotNull
    List<RoleFlag> getRoleFlags(@NotNull RoleFlagCategory category);

    @NotNull
    List<RoleFlag> getRoleFlags(@NotNull RoleFlagCategory category, @NotNull FlagModule module);

    @NotNull Collection<PlayerFlag> getPlayerFlags();

    @Nullable PlayerFlag getPlayer(@NotNull String name);

    @NotNull
    Collection<NaturalFlag> getNaturalFlags();
}