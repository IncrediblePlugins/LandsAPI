package me.angeschossen.lands.api.flags;

import com.google.common.base.Preconditions;
import me.angeschossen.lands.api.flags.types.LandFlag;
import me.angeschossen.lands.api.flags.types.PlayerFlag;
import me.angeschossen.lands.api.flags.types.RoleFlag;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Use {@link me.angeschossen.lands.api.flags.type.Flags} instead.
 * <p>
 * Legacy registry of all built-in Lands flags. Fields are populated by
 * {@link #initializeLegacySupport()} during plugin startup.
 */
@Deprecated
public final class Flags {

    // -----------------------------------------------------------------------
    // Action role flags
    // -----------------------------------------------------------------------

    /** Allows the role to break blocks. */
    public static RoleFlag BLOCK_BREAK;
    /** Allows the role to place blocks. */
    public static RoleFlag BLOCK_PLACE;
    /** Allows the role to attack players. */
    public static RoleFlag ATTACK_PLAYER;
    /** Allows the role to attack animals. */
    public static RoleFlag ATTACK_ANIMAL;
    /** Allows the role to attack monsters. */
    public static RoleFlag ATTACK_MONSTER;
    /** Allows the role to ignite blocks (place fire). */
    public static RoleFlag BLOCK_IGNITE;
    /** Allows all general interactions not covered by a more specific INTERACT flag. */
    public static RoleFlag INTERACT_GENERAL;
    /** Allows the role to use redstone, levers, pressure plates, etc. */
    public static RoleFlag INTERACT_MECHANISM;
    /** Allows the role to access chests and other containers. */
    public static RoleFlag INTERACT_CONTAINER;
    /** Allows the role to open and close doors. */
    public static RoleFlag INTERACT_DOOR;
    /** Allows the role to open and close trapdoors. */
    public static RoleFlag INTERACT_TRAPDOOR;
    /** Allows the role to trade with villagers. */
    public static RoleFlag INTERACT_VILLAGER;
    /** Allows the role to fly within the area. */
    public static RoleFlag FLY;
    /** Allows the role to use an elytra within the area. */
    public static RoleFlag ELYTRA;
    /** Allows the role to teleport to the land spawn. */
    public static RoleFlag SPAWN_TELEPORT;
    /** Allows the role to enter the land area. */
    public static RoleFlag LAND_ENTER;
    /** Allows the role to place and use vehicles. */
    public static RoleFlag VEHICLE_USE;
    /** Allows the role to pick up dropped items. */
    public static RoleFlag ITEM_PICKUP;
    /** Allows the role to use ender pearls. */
    public static RoleFlag ENDER_PEARL;
    /** Allows the role to trample farmland. */
    public static RoleFlag TRAMPLE_FARMLAND;
    /** Allows the role to harvest crops. */
    public static RoleFlag HARVEST;
    /** Allows the role to plant crops and saplings. */
    public static RoleFlag PLANT;
    /** Allows the role to shear animals. */
    public static RoleFlag SHEAR;

    // -----------------------------------------------------------------------
    // Management role flags
    // -----------------------------------------------------------------------

    /** Allows the role to trust other players. */
    public static RoleFlag PLAYER_TRUST;
    /** Allows the role to untrust players with a lower role priority. */
    public static RoleFlag PLAYER_UNTRUST;
    /** Allows the role to change the role of players with a lower role priority. */
    public static RoleFlag PLAYER_SETROLE;
    /** Allows the role to claim chunks for the land. */
    public static RoleFlag LAND_CLAIM;
    /** Allows claiming directly adjacent to another land, ignoring the distance config. */
    public static RoleFlag LAND_CLAIM_BORDER;
    /** Allows the role to set the land spawn point. */
    public static RoleFlag SPAWN_SET;
    /** Allows the role to edit natural land flags (e.g. mob spawning). */
    public static RoleFlag SETTING_EDIT_LAND;
    /** Allows the role to edit settings of roles with a lower priority. */
    public static RoleFlag SETTING_EDIT_ROLE;
    /** Allows the role to edit tax settings. */
    public static RoleFlag SETTING_EDIT_TAXES;
    /** Allows the role to rename the land and change its title. */
    public static RoleFlag SETTING_EDIT_VARIOUS;
    /** Allows the role to withdraw from the land bank. */
    public static RoleFlag BALANCE_WITHDRAW;
    /** Allows the role to create and assign sub-areas. */
    public static RoleFlag AREA_ASSIGN;
    /** Allows the role to ban players from the land. */
    public static RoleFlag PLAYER_BAN;
    /** Allows the role to declare and manage wars. */
    public static RoleFlag WAR_MANAGE;

    /** Provides damage immunity to players with this role (hidden by default). */
    public static RoleFlag NO_DAMAGE;

    // -----------------------------------------------------------------------
    // Natural / land flags
    // -----------------------------------------------------------------------

    /** Controls whether entities (e.g. creepers) can grief blocks. */
    public static LandFlag ENTITY_GRIEFING;
    /** Controls whether TNT can destroy blocks. */
    public static LandFlag TNT_GRIEFING;
    /** Controls whether pistons from outside the land can push blocks into it. */
    public static LandFlag PISTON_GRIEFING;
    /** Controls whether monsters can spawn (excludes spawners). */
    public static LandFlag MONSTER_SPAWN;
    /** Controls whether phantoms can spawn. */
    public static LandFlag PHANTOM_SPAWN;
    /** Controls whether animals can spawn (excludes spawners). */
    public static LandFlag ANIMAL_SPAWN;
    /** Controls whether water can flow. */
    public static LandFlag WATERFLOW_ALLOW;
    /** Controls whether enter/leave title messages are hidden. */
    public static LandFlag TITLE_HIDE;
    /** Controls whether players can send join requests to the land. */
    public static LandFlag REQUEST_ACCEPT;
    /** Controls whether fire can spread within the land. */
    public static LandFlag FIRE_SPREAD;
    /** Controls whether leaves decay naturally. */
    public static LandFlag LEAF_DECAY;
    /** Controls whether plants and trees grow. */
    public static LandFlag PLANT_GROWTH;
    /** Controls whether snow and ice melt. */
    public static LandFlag SNOW_MELT;

    // -----------------------------------------------------------------------
    // Nation role flags
    // -----------------------------------------------------------------------

    /** Allows the role to edit nation settings. */
    public static RoleFlag NATION_EDIT;

    // -----------------------------------------------------------------------
    // Player flags
    // -----------------------------------------------------------------------

    /** Controls whether the player sees land enter/leave messages. */
    public static PlayerFlag ENTER_MESSAGES;
    /** Controls whether the player can receive land invites. */
    public static PlayerFlag RECEIVE_INVITES;


    /**
     * Initializes all legacy flag fields by looking them up from the flag registry.
     * Called automatically during Lands startup.
     */
    public static void initializeLegacySupport() {
        BLOCK_BREAK = RoleFlag.of("BLOCK_BREAK");
        BLOCK_PLACE = RoleFlag.of("BLOCK_PLACE");
        ATTACK_PLAYER = RoleFlag.of("ATTACK_PLAYER");
        ATTACK_ANIMAL = RoleFlag.of("ATTACK_ANIMAL");
        ATTACK_MONSTER = RoleFlag.of("ATTACK_MONSTER");
        BLOCK_IGNITE = RoleFlag.of("BLOCK_IGNITE");
        INTERACT_GENERAL = RoleFlag.of("INTERACT_GENERAL");
        INTERACT_MECHANISM = RoleFlag.of("INTERACT_MECHANISM");
        INTERACT_CONTAINER = RoleFlag.of("INTERACT_CONTAINER");
        INTERACT_DOOR = RoleFlag.of("INTERACT_DOOR");
        INTERACT_TRAPDOOR = RoleFlag.of("INTERACT_TRAPDOOR");
        INTERACT_VILLAGER = RoleFlag.of("INTERACT_VILLAGER");
        FLY = RoleFlag.of("FLY");
        SPAWN_TELEPORT = RoleFlag.of("SPAWN_TELEPORT");
        LAND_ENTER = RoleFlag.of("LAND_ENTER");
        VEHICLE_USE = RoleFlag.of("VEHICLE_USE");
        ITEM_PICKUP = RoleFlag.of("ITEM_PICKUP");
        ENDER_PEARL = RoleFlag.of("ENDER_PEARL");
        TRAMPLE_FARMLAND = RoleFlag.of("TRAMPLE_FARMLAND");
        HARVEST = RoleFlag.of("HARVEST");
        PLANT = RoleFlag.of("PLANT");
        SHEAR = RoleFlag.of("SHEAR");
        PLAYER_TRUST = RoleFlag.of("PLAYER_TRUST");
        PLAYER_UNTRUST = RoleFlag.of("PLAYER_UNTRUST");
        PLAYER_SETROLE = RoleFlag.of("PLAYER_SETROLE");
        LAND_CLAIM = RoleFlag.of("LAND_CLAIM");
        LAND_CLAIM_BORDER = RoleFlag.of("LAND_CLAIM_BORDER");
        SPAWN_SET = RoleFlag.of("SPAWN_SET");
        SETTING_EDIT_LAND = RoleFlag.of("SETTING_EDIT_LAND");
        SETTING_EDIT_ROLE = RoleFlag.of("SETTING_EDIT_ROLE");
        SETTING_EDIT_TAXES = RoleFlag.of("SETTING_EDIT_TAXES");
        SETTING_EDIT_VARIOUS = RoleFlag.of("SETTING_EDIT_VARIOUS");
        BALANCE_WITHDRAW = RoleFlag.of("BALANCE_WITHDRAW");
        AREA_ASSIGN = RoleFlag.of("AREA_ASSIGN");
        PLAYER_BAN = RoleFlag.of("PLAYER_BAN");
        WAR_MANAGE = RoleFlag.of("WAR_MANAGE");
        NO_DAMAGE = RoleFlag.of("NO_DAMAGE");
        NATION_EDIT = RoleFlag.of("NATION_EDIT");
        ELYTRA = RoleFlag.of("ELYTRA");

        ENTITY_GRIEFING = LandFlag.of("ENTITY_GRIEFING");
        TNT_GRIEFING = LandFlag.of("TNT_GRIEFING");
        PISTON_GRIEFING = LandFlag.of("PISTON_GRIEFING");
        MONSTER_SPAWN = LandFlag.of("MONSTER_SPAWN");
        PHANTOM_SPAWN = LandFlag.of("PHANTOM_SPAWN");
        ANIMAL_SPAWN = LandFlag.of("ANIMAL_SPAWN");
        WATERFLOW_ALLOW = LandFlag.of("WATERFLOW_ALLOW");
        TITLE_HIDE = LandFlag.of("TITLE_HIDE");
        REQUEST_ACCEPT = LandFlag.of("REQUEST_ACCEPT");
        FIRE_SPREAD = LandFlag.of("FIRE_SPREAD");
        LEAF_DECAY = LandFlag.of("LEAF_DECAY");
        PLANT_GROWTH = LandFlag.of("PLANT_GROWTH");
        SNOW_MELT = LandFlag.of("SNOW_MELT");

        ENTER_MESSAGES = PlayerFlag.of("ENTER_MESSAGES");
        RECEIVE_INVITES = PlayerFlag.of("RECEIVE_INVITES");
    }

    private Flags() {
    }

    /**
     * Get a flag by its name.
     *
     * @param name the flag name (case-insensitive)
     * @return the flag, or {@code null} if not found
     */
    @Nullable
    public static Flag<?> get(@NotNull String name) {
        Preconditions.checkNotNull(name, "Name cannot be null");
        return null;
    }

    /**
     * Get the interaction flag that applies to a block.
     *
     * @param block the block being interacted with
     * @return the applicable role flag, or {@code null} if none applies
     */
    @Nullable
    public static RoleFlag getInteract(@NotNull Block block) {
        return null;
    }

    /**
     * Get the interaction flag that applies to a block when a specific item is used.
     *
     * @param block the block being interacted with
     * @param item  the item held during the interaction, or {@code null} if no item
     * @return the applicable role flag, or {@code null} if none applies
     */
    @Nullable
    public static RoleFlag getInteract(@NotNull Block block, @Nullable ItemStack item) {
        return null;
    }
}
