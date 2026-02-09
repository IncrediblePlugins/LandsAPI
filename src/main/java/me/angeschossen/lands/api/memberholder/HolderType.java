package me.angeschossen.lands.api.memberholder;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.limits.LimitationTarget;
import org.jetbrains.annotations.NotNull;

public enum HolderType {
    /**
     * A land. See {@link me.angeschossen.lands.api.land.Land}
     */
    LAND(LimitationTarget.LAND),
    /**
     * A nation. See {@link me.angeschossen.lands.api.nation.Nation}
     */
    NATION(LimitationTarget.NATION);

    private final @NotNull LimitationTarget limitationTarget;

    HolderType(@NotNull LimitationTarget limitationTarget) {
        this.limitationTarget = Checks.requireNonNull(limitationTarget, "limitationTarget");
    }

    public @NotNull LimitationTarget getLimitationTarget() {
        return limitationTarget;
    }
}
