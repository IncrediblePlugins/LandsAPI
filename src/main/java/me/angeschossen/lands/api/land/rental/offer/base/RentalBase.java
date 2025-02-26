package me.angeschossen.lands.api.land.rental.offer.base;

import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.block.LandBlock;
import me.angeschossen.lands.api.land.rental.InfoType;
import me.angeschossen.lands.api.land.rental.offer.types.RentalType;
import me.angeschossen.lands.api.player.LandPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface RentalBase {
    /**
     * Get the type of rental or purchase.
     *
     * @return never null
     */
    @NotNull
    RentalType getType();

    /**
     * Get the cost of this offer.
     *
     * @return the cost to purchase or rent it
     */
    double getCost();

    /**
     * Get the size in blocks of the area or land.
     *
     * @return size in blocks
     */
    long getSize();

    /**
     * Get the offer sign or hologram.
     *
     * @return null, if the sign or hologram no longer exists.
     */
    @Nullable LandBlock getBlock();

    /**
     * Get the area that is being sold or being set for rental.
     *
     * @return If this is the default area ({@link Land#getDefaultArea()}, the whole land is being sold.
     */
    @NotNull Area getArea();

    /**
     * Send information about this offer to a player.
     *
     * @param player the recipient
     */
    void sendInfo(@NotNull LandPlayer player);

    /**
     * Get the info lines for holograms etc.
     *
     * @param infoType the type of display
     * @return all info lines
     */
    @NotNull
    List<String> getInfoLines(@NotNull InfoType infoType);
}
