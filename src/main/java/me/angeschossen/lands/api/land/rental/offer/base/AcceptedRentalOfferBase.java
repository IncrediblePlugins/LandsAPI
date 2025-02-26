package me.angeschossen.lands.api.land.rental.offer.base;

/**
 * Provides information about a accepted offer.
 * */
public interface AcceptedRentalOfferBase extends RentalBase {

    /**
     * Get the compensation that is paid to the tenant, if the land cancels the rental before it expires.
     *
     * @return compensation that is paid to the tenant
     */
    double getCompensation();

    /**
     * Expire the accepted offer to cancel a active rental.
     *
     * @return false, if cancelled by a 3rd party plugin
     */
    boolean expire();
}
