package me.angeschossen.lands.api.war.enums;

/**
 * Settings that can be toggled for an active war.
 */
public enum WarSetting {

    /**
     * If enabled, team members can damage each other.
     */
    FRIENDLY_FIRE(1);

    private final int id;
    private boolean enabled;

    WarSetting(int id) {
        this.id = id;
    }

    /**
     * Get a {@link WarSetting} by its numeric ID.
     *
     * @param id the numeric ID
     * @return the matching setting
     * @throws IllegalArgumentException if no setting with this ID exists
     */
    public static WarSetting getById(int id) throws IllegalArgumentException {
        for (WarSetting warSetting : values()) {
            if (warSetting.getId() == id) {
                return warSetting;
            }
        }

        throw new IllegalArgumentException("No warsetting with id " + id + " found.");
    }

    /**
     * Whether this setting is enabled.
     *
     * @return true if enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Enable this setting.
     * Once enabled, there is no API method to disable it again at runtime.
     */
    public void setEnabled() {
        this.enabled = true;
    }

    /**
     * Get the numeric ID of this setting.
     *
     * @return numeric ID
     */
    public int getId() {
        return id;
    }
}
