package me.angeschossen.lands.api.war.enums;

public enum WarResult {
    /**
     * One team surrendered during the war or already in the preparation phase.
     */
    SURRENDERED,
    /**
     * Both teams got the same amount of points and the time ran out.
     */
    DRAW,
    /**
     * One team won.
     */
    NORMAL,
    /**
     * A server admin forcefully ended the war.
     */
    ADMIN
}
