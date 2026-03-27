package me.angeschossen.lands.api.war;

/**
 * Stats during active war.
 */
public interface ActiveWarStats extends WarStats {
    /**
     * The current points.
     *
     * @return current points
     */
    int getPoints();

    /**
     * Get progress.
     *
     * @param warState the war to check for
     * @return progress until has points that are needed in percent
     */
    float getProgress(WarState warState);
}
