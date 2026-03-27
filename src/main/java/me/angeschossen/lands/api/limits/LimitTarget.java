package me.angeschossen.lands.api.limits;

/**
 * Specifies which entity type a {@link Limit} applies to.
 */
public enum LimitTarget implements com.github.angeschossen.pluginframework.api.limit.holder.LimitTarget {
    /** The limit applies per player. */
    PLAYER,
    /** The limit applies per land. */
    LAND,
    /** The limit applies per nation. */
    NATION
}
