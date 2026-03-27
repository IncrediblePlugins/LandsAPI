package me.angeschossen.lands.api.land.rental;

/**
 * Used to determine display options for rental info.
 */
public enum InfoType {
    /**
     * Display info as a hologram.
     */
    HOLOGRAM(3),
    /**
     * Get info for signs.
     */
    SIGN(1),
    /**
     * Get info for chat.
     */
    CHAT(3);

    private final int maxTimeLength;

    InfoType(int maxTimeLength) {
        this.maxTimeLength = maxTimeLength;
    }

    /**
     * Defines the max amount of time units.
     *
     * @return if 1 it will only display x days or x hours etc. instead of 1 hour, 5 minutes etc.
     */
    public int getMaxTimeLength() {
        return maxTimeLength;
    }
}
