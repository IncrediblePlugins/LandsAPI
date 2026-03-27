package me.angeschossen.lands.api.role.system;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.flags.type.RoleFlag;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Holds a set of role flags that are applied to a player outside of the normal role system.
 * Use this to grant temporary flag overrides, for example during a war.
 */
public final class SystemFlagStates {
    private final @NotNull LandsIntegration integration;
    private final @NotNull Set<RoleFlag> flags;

    /**
     * Create instance.
     *
     * @param landsIntegration the integration that owns these flag states
     * @param flags            the flags to apply
     */
    public SystemFlagStates(@NotNull LandsIntegration landsIntegration, @NotNull Set<RoleFlag> flags) {
        this.integration = Checks.requireNonNull(landsIntegration, "integration");
        this.flags = Checks.requireNonNull(flags, "flags");
    }

    /**
     * Get the integration that owns these flag states.
     *
     * @return never null
     */
    @NotNull
    public LandsIntegration getIntegration() {
        return integration;
    }

    /**
     * Check if these flag states also contain a specific role flag.
     *
     * @param flag the flag to check
     * @return true, if this flag is included
     */
    public boolean hasFlag(@NotNull RoleFlag flag) {
        return flags.contains(Checks.requireNonNull(flag, "flag"));
    }
}
