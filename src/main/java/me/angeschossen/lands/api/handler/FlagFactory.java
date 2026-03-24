package me.angeschossen.lands.api.handler;

import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.exceptions.FlagConflictException;
import me.angeschossen.lands.api.flags.enums.FlagTarget;
import me.angeschossen.lands.api.flags.enums.RoleFlagCategory;
import me.angeschossen.lands.api.flags.type.NaturalFlag;
import me.angeschossen.lands.api.flags.type.RoleFlag;
import org.jetbrains.annotations.NotNull;

/**
 * This interface is not intended for direct usage and might change at any time.
 */
public interface FlagFactory {

    /**
     * Create a new {@link RoleFlag}.
     *
     * @param plugin     the integration instance of the plugin registering the flag
     * @param flagTarget the target audience for the flag
     * @param category   the role-flag category
     * @param name       unique name for the flag
     * @return the created role flag; never null
     * @throws FlagConflictException    if a flag with the same name already exists
     * @throws IllegalArgumentException if any parameter is invalid
     */
    @NotNull RoleFlag roleFlagOf(@NotNull LandsIntegration plugin, @NotNull FlagTarget flagTarget, @NotNull RoleFlagCategory category, @NotNull String name) throws FlagConflictException, IllegalArgumentException;

    /**
     * Create a new {@link NaturalFlag}.
     *
     * @param plugin     the integration instance of the plugin registering the flag
     * @param flagTarget the target audience for the flag
     * @param name       unique name for the flag
     * @return the created natural flag; never null
     * @throws FlagConflictException    if a flag with the same name already exists
     * @throws IllegalArgumentException if any parameter is invalid
     */
    @NotNull NaturalFlag naturalFlagOf(@NotNull LandsIntegration plugin, @NotNull FlagTarget flagTarget, @NotNull String name) throws FlagConflictException, IllegalArgumentException;
}
