package me.angeschossen.lands.api.land.rental.offer.types;

import me.angeschossen.lands.api.land.rental.offer.base.RentalOfferBase;

/**
 * Represents a rental offer for an area that has not yet been accepted.
 */
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
