package me.angeschossen.lands.api.war;

/**
 * Extends {@link WarStats} with live progress information available only while the war is ongoing.
 * Provides the team's current point total and the progress towards the winning threshold.
 */
public interface ActiveWarStats extends WarStats {
    /**
     * Get the team's current point total in this war.
     *
     * @return current points; never negative
     */
    int getPoints();

    /**
     * Get the team's progress towards the winning point threshold.
     *
     * @param warState the active war to calculate against
     * @return progress as a percentage (0–100) towards {@link WarState#getPointsToWin()}
     */
    float getProgress(WarState warState);
}
