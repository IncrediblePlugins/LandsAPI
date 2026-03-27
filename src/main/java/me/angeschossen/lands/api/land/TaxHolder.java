package me.angeschossen.lands.api.land;

import com.github.angeschossen.pluginframework.api.holder.ChangeSaveable;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an entity (land or area) that has configurable tax settings.
 */
public interface TaxHolder extends ChangeSaveable {

    /**
     * Set tax value.
     *
     * @param tax New tax value. Can't be negative
     */
    void setTax(double tax);

    /**
     * Add taxes for this area.
     *
     * @param tax If negative, it will remove taxes.
     * @return New tax value
     */
    double addTax(double tax);

    /**
     * Get current tax value.
     *
     * @return Current tax
     */
    double getTax();

    /**
     * Get the name of this area. To include color codes, use {@link #getColorName()} instead.
     *
     * @return Name of the land without color codes
     */
    @NotNull
    String getName();

    /**
     * Same as {@link #getName()}, but with colors codes included.
     *
     * @return Name with color codes
     */
    @Deprecated
    @NotNull
    String getColorName();

    /**
     * Same as {@link #getName()}, but with color codes included.
     *
     * @param sender the command sender used for per-player language lookup, or {@code null} for the default language
     * @return Name with color codes
     */
    @NotNull
    String getColorName(@Nullable CommandSender sender);

    /**
     * Get estimated tax revenue amount.
     * @param self UUID of player for {@link Land} and Land for {@link me.angeschossen.lands.api.nation.Nation}.
     * @param assumeTrusted Assume the player is trusted or the land is a member of the nation?
     * @param before If it should calculate the tax revenue before the latest tax increase
     * @return The estimated tax revenue
     */
    double getEstimatedTaxRevenue(@Nullable Object self, boolean assumeTrusted, boolean before);
}
