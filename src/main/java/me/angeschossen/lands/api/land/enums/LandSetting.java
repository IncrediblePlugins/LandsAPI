package me.angeschossen.lands.api.land.enums;

/**
 * Use {@link me.angeschossen.lands.api.flags.type.Flags} instead.
 */
@Deprecated
public enum LandSetting {
    /** Controls whether entities (e.g. creepers, endermen) can modify blocks. */
    ENTITY_GRIEFING(0, "ENTITY_GRIEFING", true, false, true),
    /** Controls whether TNT can destroy blocks. */
    TNT_GRIEFING(1, "TNT_GRIEFING", true, false, true),
    /** Controls whether pistons can push or pull blocks across land boundaries. */
    PISTON_GRIEFING(2, "PISTON_GRIEFING", true, false, true),
    /** Controls whether hostile mobs can spawn. */
    MONSTER_SPAWN(3, "MONSTER_SPAWN", true, false, true),
    /** Controls whether passive animals can spawn. */
    ANIMAL_SPAWN(4, "ANIMAL_SPAWN", true, false, true),
    /** Controls whether water can flow into the land from outside. */
    WATERFLOW_ALLOW(5, "WATERFLOW_ALLOW", true, false, true),
    /** Controls whether the land title (action bar / title message) is hidden when entering. */
    TITLE_HIDE(6, "TITLE_HIDE", true, false, false),
    /** Controls whether fire can spread within the land. */
    FIRE_SPREAD(7, "FIRE_SPREAD", true, false, true),
    /** Controls whether leaves decay naturally. */
    LEAF_DECAY(8, "LEAF_DECAY", true, false, true),
    /** Controls whether plants (crops, saplings, etc.) can grow. */
    PLANT_GROWTH(9, "PLANT_GROWTH", true, false, true),
    /** Controls whether snow melts (inverted: when enabled snow is kept). */
    SNOW_KEEP(10, "SNOW_MELT", true, false, true, true);

    private final int iD;
    private final boolean isWilderness;
    private final boolean displayable, forceEnable;
    private final boolean isInverted;
    private final String cfgName;
    private boolean allowInWar;

    LandSetting(int iD, String cfgName, boolean displayable, boolean forceEnable, boolean isWilderness) {
        this(iD, cfgName, displayable, forceEnable, isWilderness, false);
    }

    LandSetting(int iD, String cfgName, boolean displayable, boolean forceEnable, boolean isWilderness, boolean isInverted) {
        this.iD = iD;
        this.cfgName = cfgName;
        this.displayable = displayable;
        this.forceEnable = forceEnable;
        this.isWilderness = isWilderness;
        this.isInverted = isInverted;
    }

    /**
     * Whether this setting's stored value is inverted relative to its display name.
     *
     * @return true if inverted
     */
    public boolean isInverted() {
        return isInverted;
    }

    /**
     * Get the config key used to persist this setting.
     *
     * @return config key name
     */
    public String getCfgName() {
        return cfgName;
    }

    /**
     * Get a {@link LandSetting} by its numeric ID.
     *
     * @param iD the numeric ID
     * @return the matching setting
     * @throws IllegalArgumentException if no setting with this ID exists
     */
    public static LandSetting getById(int iD) {
        for (LandSetting setting : values()) {
            if (setting.getId() == iD)
                return setting;
        }

        throw new IllegalArgumentException("No LandSetting with iD '" + iD + "' found.");
    }

    /**
     * Get a {@link LandSetting} by its config key name.
     *
     * @param name the config key
     * @return the matching setting
     * @throws IllegalArgumentException if no setting with this name exists
     */
    public static LandSetting getByCfgName(String name) {
        for (LandSetting setting : values()) {
            if (setting.cfgName.equals(name))
                return setting;
        }

        throw new IllegalArgumentException("No LandSetting with name '" + name + "' found.");
    }

    /**
     * @deprecated No-op; kept for binary compatibility.
     */
    @Deprecated
    public static void init() {

    }

    /**
     * Resolve the effective boolean state, accounting for inversion.
     *
     * @param b the raw stored value
     * @return the effective state after applying inversion
     */
    public boolean getStatus(boolean b) {
        return isInverted != b;
    }

    /**
     * Get the numeric ID of this setting.
     *
     * @return numeric ID
     */
    public int getId() {
        return iD;
    }

    /**
     * Whether this setting is applicable in wilderness (unclaimed) areas.
     *
     * @return true if applicable in wilderness
     */
    public boolean isWilderness() {
        return isWilderness;
    }

    /**
     * Whether this setting is allowed to be changed during an active war.
     *
     * @return true if the setting can be modified during war
     */
    public boolean isAllowInWar() {
        return allowInWar;
    }

    /**
     * Whether this setting should be shown in the land settings GUI.
     *
     * @return true if displayable
     */
    public boolean isDisplayable() {
        return displayable;
    }

    /**
     * Whether this setting is forced to be enabled and cannot be toggled off.
     *
     * @return true if force-enabled
     */
    public boolean isForceEnable() {
        return forceEnable;
    }
}
