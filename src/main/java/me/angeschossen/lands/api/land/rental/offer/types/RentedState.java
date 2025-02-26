package me.angeschossen.lands.api.land.rental.offer.types;

import me.angeschossen.lands.api.land.rental.offer.base.AcceptedRentalOfferBase;

public interface RentedState extends RentOffer, AcceptedRentalOfferBase {
    /**
     * Get the rented and already paid minutes.
     *
     * @return rented and paid minutes
     */
    int getRentedMinutes();

    /**
     * Add or remove minutes to the rental time.
     *
     * @param minutes can be negative to remove time
     */
    void modifyRentedMinutes(int minutes);

    /**
     * Get the maximum amount of minutes that can be added by the tenant.
     * {@link #modifyRentedMinutes(int)} bypasses this
     *
     * @return never negative
     */
    int getMaxAdditiveMinutes();

    /**
     * Check if the offer is removed after the rental expires.
     *
     * @return if true, the rental offer will be completely removed after the rental expired
     */
    boolean isRemoveAfterExpiration();

    /**
     * Make the offer be removed after the rental expired.
     *
     * @param value if true, the rental offer will be removed once the rental expires
     */
    void setRemoveAfterExpiration(boolean value);
}
