package me.angeschossen.lands.api.role;

import com.github.angeschossen.applicationframework.api.util.ULID;
import com.github.angeschossen.pluginframework.api.events.ExpressionEntity;
import me.angeschossen.lands.api.flags.type.RoleFlag;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.role.enums.RoleType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.CompletableFuture;


public interface Role extends ExpressionEntity {

    /**
     * Create a new role.
     *
     * @param roleHolder area to which the role should be added
     * @param name       the name of the new role
     * @return never null
     * @throws IllegalArgumentException if the name is invalid
     * @throws IllegalStateException    if a role with the name already exists
     */
    @NotNull
    static Role of(@NotNull RoleHolder roleHolder, @NotNull String name) throws IllegalArgumentException, IllegalStateException {
        return APIHandler.getLandsIntegrationFactory().roleOf(roleHolder, name);
    }

    /**
     * Get the globally unique ID. This ID is unique across all lands and servers, similar to UUID.
     *
     * @return Universally unique lexicographically sortable identifier
     */
    @NotNull
    ULID getULID();

    /**
     * Some role types can't be deleted as they are required.
     * You can check that, by viewing at the constructor of this enum: {@link RoleType}
     *
     * @return false, if role can't be deleted
     */
    boolean isDeleteable();

    /**
     * Check if this role is the visitor role.
     *
     * @return true, if this role's type equals {@link RoleType#VISITOR}
     * @deprecated Use {@link #getType()} instead.
     */
    @Deprecated
    boolean isVisitorRole();

    /**
     * Get the role with the lower priority.
     *
     * @param allowVisitor include visistor role?
     * @param onlyCanBeSet only return roles that can be set?
     * @return null, if no role with lower priority exists
     */
    @Nullable
    Role getLowerPriorityRole(boolean allowVisitor, boolean onlyCanBeSet);

    /**
     * Get all management flags.
     * @return active flags with {@link me.angeschossen.lands.api.flags.enums.RoleFlagCategory#MANAGEMENT}
     */
    @NotNull
    Set<RoleFlag> getManagementFlags();

    /**
     * Get the icon of this role.
     *
     * @return never null
     */
    @NotNull
    ItemStack getIcon();

    /**
     * Set a new icon for this role.
     * This supports NBT data and custom heads.
     *
     * @param icon icon to set
     */
    void setIcon(@NotNull ItemStack icon);

    /**
     * Get the role with the higher priority.
     *
     * @param allowVisitor include visistor role?
     * @param onlyCanBeSet only return roles that can be set?
     * @return null, if no role with higher priority exists
     */
    @Nullable
    Role getHigherPriorityRole(boolean allowVisitor, boolean onlyCanBeSet);

    /**
     * Get all management flags.
     * @return active flags with {@link me.angeschossen.lands.api.flags.enums.RoleFlagCategory#ACTION}
     */
    @NotNull
    Set<RoleFlag> getActionFlags();

    /**
     * Delete a role.
     *
     * @return never null
     * @throws IllegalStateException if {@link #isDeleteable()} returns false
     */
    @NotNull
    CompletableFuture<Void> delete() throws IllegalStateException;

    /**
     * Get the role type.
     *
     * @return Role type
     */
    @NotNull
    RoleType getType();

    /**
     * Get the name of this role.
     *
     * @return Name without color. To get the name with color, use {@link #getColorName()} instead.
     */
    @NotNull
    String getName();

    /**
     * Set a new name for this role.
     *
     * @param name The new name. Names can include colors codes
     * @throws IllegalArgumentException If a role with this name already exists
     */
    void setName(@NotNull String name) throws IllegalArgumentException;

    /**
     * Returns the name of the role, including its color codes.
     *
     * @return Name with color
     */
    @NotNull
    String getColorName();

    /**
     * Toggle a flag for this role.
     *
     * @param flag The flag
     * @return The new state of this flag
     */
    boolean toggleFlag(@NotNull RoleFlag flag);

    /**
     * Check if a flag is set for this role.
     *
     * @param flag The flag
     * @return true, if the flag is set
     */
    boolean hasFlag(@NotNull RoleFlag flag);

    /**
     * Get the hierarchy / priority of a role.
     *
     * @return A higher priority means that one role is above another in the hierarchy. Minimum value is 0
     */
    int getPriority();

    /**
     * Swap priority of two roles.
     *
     * @param from the priority of this role will be applied to the current role and the
     *             parameter role will receive the priority of the current role
     */
    void swapPriority(@NotNull Role from);

    /**
     * Get land, area etc. that the role belongs to.
     * @return never null
     */
    @NotNull
    RoleHolder getHolder();
}
