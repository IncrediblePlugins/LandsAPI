package me.angeschossen.lands.api.sorting;

import com.github.angeschossen.pluginframework.api.utils.StringUtils;
import me.angeschossen.lands.api.player.LandPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A sorting evaluates the place of a land or nation on the leaderboard.
 * Default sortings: balance, chunks, members, level, ratio_kd (kills/deaths in wars ratio) or ratio_wl (won/lost wars ratio)
 *
 * @param <T> the type of entity being sorted (e.g. a land or nation)
 */
public abstract class Sorting<T> implements Comparator<T> {

    /** Unique identifier for this sorting. */
    protected final String id;
    /** The context that provides the list of targets to sort. */
    protected final SortingContext<T> sortingContext;
    /** The current sorted list of entries. */
    protected List<T> entries = Collections.emptyList();

    /**
     * Create a new sorting.
     *
     * @param sortingContext the context that supplies the targets to sort
     * @param id             a unique identifier for this sorting
     * @throws IllegalStateException if the id is already registered
     */
    public Sorting(@NotNull SortingContext<T> sortingContext, @NotNull String id) throws IllegalStateException {
        this.id = StringUtils.toLowerCase(id);
        this.sortingContext = sortingContext;
    }

    /**
     * Replace an entry in the sorted list.
     *
     * @param prev    the entry to replace
     * @param updated the replacement entry
     */
    public final void replace(T prev, T updated) {
        int curr = entries.indexOf(prev);
        if (curr != -1) {
            entries.set(curr, updated);
        }
    }

    /**
     * Remove an entry from the sorted list.
     *
     * @param t the entry to remove
     */
    public final void remove(T t) {
        entries.remove(t);
    }

    /**
     * Get the entry at the given leaderboard place (1-based).
     *
     * @param place 1-based leaderboard position
     * @return the entry at that place, or null if no entry exists there
     * @throws IllegalArgumentException if place is less than 1
     */
    @Nullable
    public final T get(int place) {
        if (place < 1) {
            throw new IllegalArgumentException("Place can't be smaller than 1.");
        }

        place -= 1;
        return entries.size() <= place ? null : entries.get(place);
    }

    /**
     * Get the full sorted list of entries.
     *
     * @return all entries in sorted order
     */
    @NotNull
    public final List<T> get() {
        return entries;
    }

    /**
     * @deprecated Use {@link #getDisplayName(LandPlayer)} instead.
     * @return display name
     */
    @NotNull
    @Deprecated
    public abstract String getDisplayName();

    /**
     * Get the unique ID of this sorting.
     *
     * @return unique ID
     */
    public final String getId() {
        return id;
    }

    /**
     * Get the emoji used to represent this sorting in displays.
     *
     * @return emoji string
     */
    @NotNull
    public abstract String getEmoji();

    /**
     * Get the 1-based leaderboard place of an entry.
     *
     * @param t the entry
     * @return 1-based place, or 0 if not in the list
     */
    public final int getPlace(T t) {
        return entries.indexOf(t) + 1;
    }

    /**
     * Parse a hologram line for the entry at the given place.
     *
     * @param place 1-based leaderboard position
     * @return the parsed line, or null if no entry exists at that place
     */
    @Nullable
    public final String handleParseHologramLine(int place) {
        T t = get(place);
        return t == null ? null : parseHologramLine(place, t);
    }


    /**
     * Parse menu item placeholders for the given entry at the given place.
     *
     * @param place 1-based leaderboard position
     * @param t     the entry at that place
     * @return a 2D array where index 0 contains placeholder names and index 1 contains values
     */
    public final String[][] handleParseMenuItem(int place, @NotNull T t) {
        String[][] s = getGUIPlaceholders(place, t);
        if (s.length < 2 || s[0].length != s[1].length) {
            throw new IllegalStateException("Placeholder and value array must be of the same length.");
        }

        return s;
    }

    /**
     * Parse menu item placeholders for the entry at the given place.
     *
     * @param place 1-based leaderboard position
     * @return a 2D array of placeholders and values, or an empty array if no entry exists
     */
    @NotNull
    public final String[][] handleParseMenuItem(int place) {
        T t = get(place);
        if (t == null) {
            return new String[0][0];
        }

        return handleParseMenuItem(place, t);
    }

    /**
     * Get the placeholder keys supported by this sorting.
     *
     * @return placeholder key array
     */
    public abstract String[] getPlaceholders();

    /**
     * Parse sign lines for the entry at the given place.
     *
     * @param place 1-based leaderboard position
     * @return sign lines, or an empty array if no entry exists
     */
    @NotNull
    public final String[] handleParseSignLines(int place) {
        T t = get(place);
        return t == null ? new String[0] : parseSignLines(place, t);
    }

    /**
     * Sort all targets from the context and update the internal list.
     */
    public final void sort() {
        final List<T> list = sortingContext.getTargets();
        this.entries = list.stream().sorted(this).collect(Collectors.toList());
    }

    /**
     * Get GUI placeholder pairs for a specific entry.
     *
     * @param place 1-based leaderboard position
     * @param t     the entry at that place
     * @return a 2D array where index 0 is placeholder names and index 1 is values
     */
    @NotNull
    protected abstract String[][] getGUIPlaceholders(int place, T t);

    /**
     * Get the localized display name for this sorting.
     *
     * @param landPlayer the player to localize for, or null for default locale
     * @return display name
     */
    public abstract @NotNull String getDisplayName(@Nullable LandPlayer landPlayer);

    /**
     * Parse a single hologram line for the given entry.
     *
     * @param place 1-based leaderboard position
     * @param t     the entry at that place
     * @return the parsed line
     */
    @NotNull
    protected abstract String parseHologramLine(int place, T t);

    /**
     * Parse sign lines for the given entry.
     *
     * @param place 1-based leaderboard position
     * @param t     the entry at that place
     * @return sign lines for display on a sign
     */
    @NotNull
    protected abstract String[] parseSignLines(int place, T t);
}
