package me.angeschossen.lands.api.events.rent;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.rental.offer.base.RentalOfferBase;
import me.angeschossen.lands.api.player.LandPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Called whenever a rent or sell offer is created.
 */
public class RentalOfferCreateEvent extends RentalEvent {
    private final RentalOfferBase rentalOffer;

    /**
     * Create instance.
     *
     * @param area        the area to be rented or sold. If area is the default area, the complete land will be sold.
     * @param landPlayer  the player that creates the offer
     * @param rentalOffer the created offer
     */
    public RentalOfferCreateEvent(@NotNull Area area, @NotNull LandPlayer landPlayer, @NotNull RentalOfferBase rentalOffer) {
        super(area, landPlayer);

        this.rentalOffer = Checks.requireNonNull(rentalOffer, "rentalOffer");
    }

    /**
     * Get the created offer.
     *
     * @return never null
     */
    @Override
    public @NotNull RentalOfferBase getOffer() {
        return rentalOffer;
    }
}
