package me.angeschossen.lands.api.relations;

/**
 * Represents the diplomatic relation between two lands or nations.
 */
public enum Relation {
    /**
     * Both parties agreed to be allies.
     */
    ALLY,
    /**
     * One party declared to other an enemy.
     */
    ENEMY,
    /**
     * Both parties don't have any relation.
     */
    NEUTRAL
}
