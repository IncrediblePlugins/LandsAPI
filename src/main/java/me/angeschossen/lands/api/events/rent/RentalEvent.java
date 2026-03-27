package me.angeschossen.lands.api.events.rent;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.events.player.area.PlayerAreaEvent;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.rental.offer.base.RentalOfferBase;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

/**
 * Called for events related to rental.
 */
public abstract class RentalEvent extends PlayerAreaEvent implements Cancellable {
    private boolean cancelled;

    /**
     * Create instance.
     *
     * @param area       rented or sold area
     * @param playerUUID involved player
     */
    public RentalEvent(@NotNull Area area, @NotNull UUID playerUUID) {
        super(Checks.requireNonNull(area, "area"), Checks.requireNonNull(playerUUID, "playerUUID"));
    }

    /**
     * Create instance.
     *
     * @param area       rented or sold area
     * @param landPlayer involved player
     */
    public RentalEvent(@NotNull Area area, @NotNull LandPlayer landPlayer) {
        super(Checks.requireNonNull(area, "area"), Checks.requireNonNull(landPlayer, "landPlayer"));
    }

    /**
     * Get the rental offer.
     *
     * @return never null
     */
    @NotNull
    public RentalOfferBase getOffer() {
        return Objects.requireNonNull(area.getRentalOffer(), "expected rental offer");
    }

    /**
     * Get the area.
     *
     * @return if this is the default area, the whole land is being sold.
     */
    @Override
    public @NotNull Area getArea() {
        return super.getArea();
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}