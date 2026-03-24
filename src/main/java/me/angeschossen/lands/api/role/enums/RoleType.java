package me.angeschossen.lands.api.role.enums;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the built-in role types available in a land or area.
 * Each type has fixed behaviour constraints such as whether it can be deleted,
 * can have members assigned, or can appear multiple times.
 */
public enum RoleType {
    /**
     * The role of this type is assigned to the owner of the land or area.
     */
    OWNER(4, false, false, true, false, false),
    /**
     * The role of this type is assigned for members of lands or nations that are allied
     * to a land.
     */
    ALLY(5, false, false, false, false, true),
    /**
     * The role of this type is automatically assigned to members of the same nation that
     * aren't part of the land itself.
     */
    NATION(3, false, false, false, false, true),
    /**
     * This can be a user created role or another default role.
     */
    NORMAL(2, true, true, true, true, true),
    /**
     * This role is assigned to the tenant of an area.
     */
    TENANT(6, false, false, true, false, true),
    /**
     * The role of this type is assigned to newly trusted players.
     */
    ENTRY(1, false, false, true, true, true),
    /**
     * The role of this type is assigned to players that aren't part of
     * any other role types.
     */
    VISITOR(0, false, false, false, false, false);

    private static final Map<Integer, RoleType> map = new HashMap<>();

    /**
     * Whether this role type is used within a land (as opposed to nation-only roles).
     *
     * @return true if this is a land role type
     */
    public boolean isLand() {
        return this != NATION;
    }

    static {
        for (RoleType roleType : values()) {
            map.put(roleType.id, roleType);
        }
    }

    private final boolean multiple, canBeSet, canPriorityBeChanged;
    private final int id;
    private final boolean canHaveMembers;
    private boolean isDeleteable;

    RoleType(int id, boolean multiple, boolean isDeleteable, boolean canHaveMembers, boolean canBeSet, boolean canPriorityBeChanged) {
        this.id = id;
        this.multiple = multiple;
        this.canPriorityBeChanged = canPriorityBeChanged;
        this.canBeSet = canBeSet;
        this.isDeleteable = isDeleteable;
        this.canHaveMembers = canHaveMembers;
    }

    /**
     * Whether the priority of this role type can be changed.
     *
     * @return true if the priority can be changed
     */
    public boolean canPriorityBeChanged() {
        return canPriorityBeChanged;
    }

    /**
     * Whether this role type can be assigned to a player.
     *
     * @return true if the role can be assigned to members
     */
    public boolean canBeSet() {
        return canBeSet && canHaveMembers();
    }

    /**
     * Get a {@link RoleType} by its numeric ID.
     *
     * @param id the numeric ID
     * @return the matching type, or {@link #NORMAL} if not found
     */
    @NotNull
    public static RoleType getById(int id) {
        return map.getOrDefault(id, NORMAL);
    }

    /**
     * Whether this role type can be applied to players (i.e. NORMAL or ENTRY).
     *
     * @return true if this role can be applied
     */
    public boolean canApply() {
        return this == NORMAL || this == ENTRY;
    }

    /**
     * Whether this role type supports having members assigned to it.
     *
     * @return true if members can be assigned
     */
    public boolean canHaveMembers() {
        return canHaveMembers;
    }

    /**
     * Whether more than one role of this type can exist in the same land or area.
     *
     * @return true if multiple roles of this type are allowed
     */
    public boolean canMultiple() {
        return multiple;
    }

    /**
     * Get the numeric ID of this role type.
     *
     * @return numeric ID
     */
    public int getId() {
        return id;
    }

    /**
     * Whether roles of this type can be deleted.
     *
     * @return true if deleteable
     */
    public boolean isDeleteable() {
        return isDeleteable;
    }

    /**
     * Set whether roles of this type can be deleted.
     *
     * @param deleteable true to allow deletion
     */
    public void setDeleteable(boolean deleteable) {
        isDeleteable = deleteable;
    }

    /**
     * Whether this is a system role type (i.e. cannot have members directly assigned).
     *
     * @return true if system role
     */
    public boolean isSystem() {
        return !canHaveMembers;
    }

    /**
     * Whether members of this role type are required to pay taxes.
     *
     * @return true if members pay taxes
     */
    public final boolean paysTaxes() {
        return this == NORMAL || this == ENTRY;
    }
}
