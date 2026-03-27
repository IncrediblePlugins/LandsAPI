package me.angeschossen.lands.api.land.enums;

/**
 * Use {@link me.angeschossen.lands.api.sorting.Sorting} instead.
 */
@Deprecated
public enum SortMode {

    /** Sort by number of claimed chunks. */
    CHUNKS("chunks"),
    /** Sort by land bank balance. */
    BALANCE("balance"),
    /** Sort by number of members. */
    MEMBERS("members"),
    /** Sort by land level. */
    LEVEL("level");

    /** The config/API identifier for this sort mode. */
    public final String name;
    /** Whether this sort mode is currently enabled. */
    public boolean enabled = true;

    SortMode(String name) {
        this.name = name;
    }
}