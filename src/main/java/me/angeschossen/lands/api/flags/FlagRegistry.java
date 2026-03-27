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
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * Contains information about flags.
 */
public interface FlagRegistry {

    /**
     * Register a flag. Flags need to be registered before Lands loads data.
     * To make sure that the flag can be registered, call {@link me.angeschossen.lands.api.LandsIntegration#onLoad(Runnable)}
     * in combination with this method in your {@link Plugin#onLoad()} method of your plugins main class.
     *
     * @param flag flag to register
     * @return same instance
     * @throws FlagConflictException    if an flag with this unique name already exists
     * @throws IllegalArgumentException if the flag name is invalid
     * @throws IllegalStateException    if Lands wasn't loaded yet or it's already enabled
     */
    @NotNull
    me.angeschossen.lands.api.flags.type.parent.Flag<?> register(@NotNull me.angeschossen.lands.api.flags.type.parent.Flag<?> flag) throws FlagConflictException, IllegalArgumentException, IllegalStateException;

    /**
     * Check if the name is valid.
     *
     * @param name the name
     * @return true, if the name is valid
     */
    boolean isValidName(@NotNull String name);

    /**
     * Get the flag that would be used for an interaction with this block
     *
     * @param block the interacted block
     * @param item  the item that is used to interaction with the block
     * @return null, if this block interaction isn't covered by any flag
     */
    @Nullable
    RoleFlag getInteract(@NotNull Block block, @Nullable ItemStack item);

    /**
     * Get a role flag by its name.
     *
     * @param name the unique name
     * @return null, if no role flag with this name exists
     */
    @Nullable
    RoleFlag getRole(@NotNull String name);

    /**
     * Get a natural flag by its name.
     *
     * @param name the unique name
     * @return null, if no natural flag with this name exists
     */
    @Nullable
    NaturalFlag getNatural(@NotNull String name);

    /**
     * Get a flag by its name
     *
     * @param name the unique name
     * @return null, if no flag with this name exists
     */
    @Nullable
    Flag<?> get(@NotNull String name);

    /**
     * Get all registered role flags.
     *
     * @return collection of all role flags
     */
    @NotNull
    Collection<RoleFlag> getRoleFlags();

    /**
     * Get role flags of a specific category
     *
     * @param category the category to filter
     * @return collection of role flags that belong to this category
     */
    @NotNull
    List<RoleFlag> getRoleFlags(@NotNull RoleFlagCategory category);

    /**
     * Filter role flags by an category and module
     *
     * @param category category to filter
     * @param module   module to filter
     * @return collection of flags that belong to this category and module
     */
    @NotNull
    List<RoleFlag> getRoleFlags(@NotNull RoleFlagCategory category, @NotNull FlagModule module);

    /**
     * Get all player flags.
     *
     * @return collection of flags that are used to toggle player's personal settings
     */
    @NotNull
    Collection<PlayerFlag> getPlayerFlags();

    /**
     * Get player flag by its name.
     *
     * @param name the unique name
     * @return null, if this player flag doesn't exist
     */
    @Nullable
    PlayerFlag getPlayer(@NotNull String name);

    /**
     * Get all natural flags.
     *
     * @return collection of all natural flags
     */
    @NotNull
    Collection<NaturalFlag> getNaturalFlags();
}
