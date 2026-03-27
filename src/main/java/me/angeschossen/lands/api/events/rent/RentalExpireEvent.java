package me.angeschossen.lands.api.events.rent;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.land.Area;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Called whenever a rental or sold area expires. Sold areas expire when the tenant is forcefully removed and not by time.
 */
public class RentalExpireEvent extends RentalEvent {
    /**
     * Create instance.
     *
     * @param area the sold or rented area
     */
    public RentalExpireEvent(@NotNull Area area) {
        super(Checks.requireNonNull(area, "area"), Objects.requireNonNull(area.getTenant(), "can only call this event for rented areas"));
    }
}
