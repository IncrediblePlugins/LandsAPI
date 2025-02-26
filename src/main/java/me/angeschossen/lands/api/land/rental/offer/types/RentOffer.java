package me.angeschossen.lands.api.land.rental.offer.types;

import me.angeschossen.lands.api.land.rental.offer.base.RentalOfferBase;

public interface RentOffer extends RentalOfferBase {
    /**
     * Get the amount of minutes for each renewal interval.
     *
     * @return amount of minutes for each renewal interval
     */
    int getMinutes();

    /**
     * Get the maximum rental time.
     *
     * @return max. rental time
     */
    int getMaxMinutes();
}
