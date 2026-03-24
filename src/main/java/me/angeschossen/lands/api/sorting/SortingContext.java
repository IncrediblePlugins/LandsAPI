package me.angeschossen.lands.api.sorting;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * Provides a set of sortings for a specific type of target (e.g. lands or nations).
 *
 * @param <T> the type of entity being sorted
 */
public interface SortingContext<T> {
    /**
     * Add a sorting to this sorting context.
     *
     * @param sorting The sorting
     * @throws IllegalStateException If the a sorting with the same ID already exists in this context
     */
    void addSorting(@NotNull Sorting<T> sorting) throws IllegalStateException;

    /**
     * Get the identification of this context.
     *
     * @return Identifier
     */
    @NotNull String getId();

    /**
     * Go through sorting modes. Useful for GUI etc.
     *
     * @param mode Current mode
     * @return Next mode in list
     */
    @NotNull Sorting<T> getNext(@Nullable Sorting<T> mode);

    /**
     * Get sorting by its ID.
     *
     * @param id case insensitive ID of the sorting. Default sortings: balance, chunks, members, level, ratio_kd (kills/deaths in wars ratio) or ratio_wl (won/lost wars ratio)
     * @return null, if no sorting with this ID registered
     */
    @Nullable Sorting<T> getSortingModeById(@NotNull String id);

    /**
     * Get all sortings of this context.
     *
     * @return Sortings of this context
     */
    @NotNull Collection<Sorting<T>> getSortings();

    /**
     * Get sorting targets.
     *
     * @return All sorting targets
     */
    @NotNull List<T> getTargets();
}
