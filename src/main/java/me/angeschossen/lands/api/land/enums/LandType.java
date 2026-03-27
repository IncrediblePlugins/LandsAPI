package me.angeschossen.lands.api.land.enums;

/**
 * There are different types of lands.
 */
public enum LandType {
    /**
     * Normal player land. Used at "/lands create".
     */
    LAND(0),
    /**
     * The normal land ({@link #LAND} was converted into an admin land.
     * Lands of this type, are not owned by any player.
     * Admin lands can set special flags and can't engage in wars.
     * However, they can still be part of a nation.
     */
    ADMIN(1),
    /**
     * A camp is a temporary land that will be deleted once it expires.
     * This is great for starters that simply want to protect their chests or initial base.
     */
    CAMP(2);

    private static final LandType[] map = new LandType[]{LAND, ADMIN, CAMP};

    /** Numeric identifier used for database storage. */
    public final int id;

    LandType(int id) {
        this.id = id;
    }

    /**
     * Get the land type by its ID.
     * @param id The ID
     * @return This will return {@link #LAND} if there's no type with this ID
     */
    public static LandType getById(int id) {
        if (id >= map.length) {
            return LAND;
        }

        return map[id];
    }
}
