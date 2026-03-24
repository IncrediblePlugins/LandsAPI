package me.angeschossen.lands.api.player;

/**
 * Represents a particle or block-based visualization shown to a player (e.g. for land borders).
 */
public interface Visualization {

    /**
     * Check if this visualization stays permanently.
     * @return true, if it's permanent
     */
    boolean isPermanent();

    /**
     * Check if the visualization has another display cycle.
     *
     * @return false, if the visualization is about to stop.
     */
    boolean hasNext();
}
