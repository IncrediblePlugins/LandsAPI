package me.angeschossen.lands.api.war;

import me.angeschossen.lands.api.war.captureflag.CaptureFlag;

public interface WarStats {

    /**
     * Get points that weren't received by capturing a capture flag or killing enemies.
     * This includes positive point modification using {@link #modifyPoints(int)} and exploding or breaking an capture flag.
     *
     * @return Never negative
     */
    int getPointsAdditional();

    /**
     * Get total amount of points, including {@link #getPointsAdditional()}.
     * This also includes points received by killing enemies and capturing capture flags.
     *
     * @return Never negative
     */
    float getPointsTotal();

    /**
     * Get win-lost wars ratio.
     *
     * @return win-lost wars ratio
     */
    double getWLRatio();

    /**
     * Get amount of wars won.
     *
     * @return Amount of wars that they have won
     */
    int getWon();

    /**
     * Get amount of wars lost.
     *
     * @return Amount of wars that they have lost
     */
    int getLost();

    /**
     * Get kill-death wars ratio.
     *
     * @return kill-death ratio
     */
    double getKDRatio();

    /**
     * Get amount of kills.
     *
     * @return Amount of kills
     */
    int getKills();

    /**
     * Get amount of deaths.
     *
     * @return Amount of deaths
     */
    int getDeaths();

    /**
     * Modify points of the team.
     *
     * @param mod Can be positive or negative
     */
    void modifyPoints(int mod);

    /**
     * Get amount of captures. See {@link CaptureFlag} for more information.
     *
     * @return Amount of captures
     */
    int getCaptures();

    /**
     * Set current kills.
     *
     * @param kills kills to set
     */
    void setKills(int kills);

    /**
     * Set current deaths.
     *
     * @param deaths deaths to set
     */
    void setDeaths(int deaths);

    /**
     * Set current captures of {@link CaptureFlag}.
     *
     * @param captures captures to set
     */
    void setCaptures(int captures);
}
