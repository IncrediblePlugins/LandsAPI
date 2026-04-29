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
     * Estimate the total tax revenue this land or nation would collect in a single tax cycle.
     *
     * @param self          for a {@link Land}: the UUID of the player to exclude from the calculation (typically the owner),
     *                      or {@code null} to include all members;
     *                      for a {@link me.angeschossen.lands.api.nation.Nation}: the member {@link Land} to exclude, or {@code null} to include all
     * @param assumeTrusted if {@code true}, treat the excluded entity as trusted/member even if it isn't
     * @param before        if {@code true}, calculate revenue as it was before the most recent tax change
     * @return the estimated tax revenue; never negative
     */
    double getEstimatedTaxRevenue(@Nullable Object self, boolean assumeTrusted, boolean before);
}
