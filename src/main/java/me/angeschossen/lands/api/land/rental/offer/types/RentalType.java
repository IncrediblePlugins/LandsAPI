package me.angeschossen.lands.api.land.rental.offer.types;

public enum RentalType {
    /**
     * The offer is to rent a sub area.
     * See {@link RentOffer}
     */
    RENT,
    /**
     * The sub areas is rented.
     * See {@link RentedState}
     */
    RENTED,
    /**
     * The offer is to purchase a sub area or a whole land.
     * See {@link SellOffer}
     */
    SELL,
    /**
     * The sub area or land is sold.
     * See {@link SoldState}
     */
    SOLD
}
