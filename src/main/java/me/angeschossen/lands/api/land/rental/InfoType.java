package me.angeschossen.lands.api.land.rental;

/**
 * Specifies where rental information will be displayed and controls format constraints such as time-unit verbosity.
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
     * Get the maximum number of time units shown in formatted duration strings.
     * For example, a value of {@code 1} renders only the largest unit ("3 hours") while a value of {@code 3}
     * may render "1 hour, 5 minutes, 10 seconds".
     *
     * @return the maximum number of time units; always at least 1
     */
    public int getMaxTimeLength() {
        return maxTimeLength;
    }
}
