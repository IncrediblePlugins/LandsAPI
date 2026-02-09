package me.angeschossen.lands.api.memberholder;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.limits.LimitTarget;
import org.jetbrains.annotations.NotNull;

public enum HolderType {
    /**
     * A land. See {@link me.angeschossen.lands.api.land.Land}
     */
    LAND(LimitTarget.LAND),
    /**
     * A nation. See {@link me.angeschossen.lands.api.nation.Nation}
     */
    NATION(LimitTarget.NATION);

    private final @NotNull LimitTarget limitTarget;

    HolderType(@NotNull LimitTarget limitTarget) {
        this.limitTarget = Checks.requireNonNull(limitTarget, "limitationTarget");
    }

    public @NotNull LimitTarget getLimitationTarget() {
        return limitTarget;
    }
}
