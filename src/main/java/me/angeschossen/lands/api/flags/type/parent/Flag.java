package me.angeschossen.lands.api.flags.type.parent;

import me.angeschossen.lands.api.flags.enums.FlagModule;
import me.angeschossen.lands.api.flags.enums.FlagTarget;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Flags are used to control what player's roles can do and which natural events should be cancelled.
 *
 * @param <T>
 */
public interface Flag<T> {

    /**
     * Check if this flag is set to be automatically be disabled for each land whenever they're read from the database.
     *
     * @return true, if the flag should not be parsed
     */
    boolean isAutoDisable();

    /**
     * Automatically disable flags when a area is read from the database.
     *
     * @param isAutoDisable true if the flag should be disabled for existing lands
     */
    @NotNull
    T setAutoDisable(boolean isAutoDisable);

    /**
     * The plugin that provides this flag.
     *
     * @return never null
     */
    @NotNull Plugin getPlugin();

    /**
     * Check if the flag should be displayed in the toggle menu.
     *
     * @param area       Should it be displayed for this specific area? If null, this is checked for wilderness.
     * @param landPlayer Flags might still be displayed for players that have server admin perms. This is the case for flags that are only meant to be set by server admins.
     * @return false, if should not be displayed
     */
    boolean shouldDisplay(@Nullable Area area, @Nullable LandPlayer landPlayer);

    /**
     * Get the icon that is used for this flag in menus.
     *
     * @return never null
     */
    @NotNull ItemStack getIcon();

    /**
     * Set the icon for this flag.
     *
     * @param icon shouldn't be of type AIR
     * @return instance of this flag
     */
    @NotNull T setIcon(@Nullable ItemStack icon);

    /**
     * Check if this flag displays in the "/lands admin menu" wilderness flags menu.
     *
     * @return true, if this flag displays in the wilderness flags menu
     */
    boolean isDisplayInWilderness();

    /**
     * Check if this flag is always enabled in the wilderness.
     *
     * @return if true, this flag doesn't display in the wilderness admin menu, since it's forcefully enabled.
     */
    boolean isAlwaysAllowInWilderness();

    /**
     * Set whether or not this flag should always be enabled in the wilderness.
     *
     * @param allow if true, server admins won't be able to change this
     * @return instance of this flag
     */
    @NotNull
    T setAlwaysAllowInWilderness(boolean allow);

    /**
     * Check if this flag is available for sub areas.
     *
     * @return true, if this flag is available for sub areas
     */
    boolean isApplyInSubareas();

    /**
     * Toggle availability of this flag for sub areas.
     *
     * @param set if false, this flag won't have any effect in sub areas.
     * @return instance of this flag
     */
    @NotNull
    T setApplyInSubareas(boolean set);

    /**
     * Get the description of this flag. This is used in menus.
     *
     * @return might include color
     */
    @Nullable List<String> getDescription();

    /**
     * Set the description of this flag.
     *
     * @param description each list item represents a new line
     * @return instance of this flag
     */
    @NotNull T setDescription(@Nullable List<String> description);

    /**
     * Set the description of this flag.
     *
     * @param description the description will automatically be split into multiple lines for GUI menus.
     *                    To specify your own lines, use {@link #setDescription(List)}.
     * @return instance of this flag
     */
    @NotNull T setDescription(@Nullable String description);

    /**
     * Check if this flag does display in any toggle menus.
     *
     * @return true, if this flag does display in toggle menus
     */
    boolean isDisplay();

    /**
     * Set whether or not this flag should be displayed in flag toggle menus.
     *
     * @param display if true, this flag displays in flag toggle menus
     * @return instance of this flag
     */
    @NotNull T setDisplay(boolean display);

    /**
     * Get the display name for this flag.
     *
     * @return this might include already parsed color
     */
    @NotNull String getDisplayName();

    /**
     * Set the display name for this flag.
     *
     * @param displayName might include already parsed color
     * @return instance of this plugin
     */
    @NotNull T setDisplayName(@Nullable String displayName);

    /**
     * Get the unique name of this flag.
     *
     * @return the name is unique
     */
    @NotNull String getName();

    /**
     * Check whether or not this flag is enabled in wars.
     *
     * @return true, if this flag is enabled in wars
     */
    boolean isActiveInWar();

    /**
     * Set whether or not this flag should be enabled in all wars.
     *
     * @param activeInWar if true, this flag will be enabled in all current and future wars
     * @return instance of this flag
     */
    @NotNull T setActiveInWar(boolean activeInWar);

    /**
     * Get the needed permission to toggle this flag.
     *
     * @return never null
     */
    @NotNull String getTogglePermission();

    /**
     * Get the module to which this flag belongs.
     *
     * @return never null
     */
    @NotNull FlagModule getModule();

    /**
     * Get the target of this flag.
     *
     * @return never null
     */
    @NotNull FlagTarget getTarget();
}
