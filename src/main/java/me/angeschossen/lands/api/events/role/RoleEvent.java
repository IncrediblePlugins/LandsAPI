package me.angeschossen.lands.api.events.role;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.plugin.LandsEvent;
import me.angeschossen.lands.api.role.Role;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for events that involve a role.
 */
public abstract class RoleEvent extends LandsEvent {
    /** The role involved in this event. */
    protected final @NotNull Role role;

    /**
     * Create instance.
     *
     * @param role the role involved in this event
     */
    protected RoleEvent(@NotNull Role role) {
        this.role = Checks.requireNonNull(role, "role");
    }

    /**
     * Get the role.
     *
     * @return role affected by this event
     */
    public @NotNull Role getRole() {
        return role;
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {
        role.setExpressionVariables("role_", builder, null);
    }
}
