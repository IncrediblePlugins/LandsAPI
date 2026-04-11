package me.angeschossen.lands.api.flags.type;

import com.google.common.base.Preconditions;
import me.angeschossen.lands.api.flags.type.parent.Flag;
import me.angeschossen.lands.api.handler.APIHandler;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Registry of all built-in Lands flags.
 * Fields are populated automatically during plugin startup.
 */
public final class Flags {

    // -----------------------------------------------------------------------
    // Action role flags
    // -----------------------------------------------------------------------

    /**
     * Allows the role to break blocks.
     */
    public static RoleFlag BLOCK_BREAK;
    /**
     * Allows the role to place blocks.
     */
    public static RoleFlag BLOCK_PLACE;
    /**
     * Allows the role to attack players.
     */
    public static RoleFlag ATTACK_PLAYER;
    /**
     * Allows the role to attack animals.
     */
    public static RoleFlag ATTACK_ANIMAL;
    /**
     * Allows the role to attack monsters.
     */
    public static RoleFlag ATTACK_MONSTER;
    /**
     * Allows the role to ignite blocks (place fire).
     */
    public static RoleFlag BLOCK_IGNITE;
    /**
     * Allows all general interactions not covered by a more specific INTERACT flag.
     */
    public static RoleFlag INTERACT_GENERAL;
    /**
     * Allows the role to use redstone, levers, pressure plates, etc.
     */
    public static RoleFlag INTERACT_MECHANISM;
    /**
     * Allows the role to access chests and other containers.
     */
    public static RoleFlag INTERACT_CONTAINER;
    /**
     * Allows the role to open and close doors.
     */
    public static RoleFlag INTERACT_DOOR;
    /**
     * Allows the role to open and close trapdoors.
     */
    public static RoleFlag INTERACT_TRAPDOOR;
    /**
     * Allows the role to trade with villagers.
     */
    public static RoleFlag INTERACT_VILLAGER;
    /**
     * Allows the role to fly within the area.
     */
    public static RoleFlag FLY;
    /**
     * Allows the role to use an elytra within the area.
     */
    public static RoleFlag ELYTRA;
    /**
     * Allows the role to teleport to the land spawn.
     */
    public static RoleFlag SPAWN_TELEPORT;
    /**
     * Allows the role to enter the land area.
     */
    public static RoleFlag LAND_ENTER;
    /**
     * Allows the role to place and use vehicles.
     */
    public static RoleFlag VEHICLE_USE;
    /**
     * Allows the role to pick up dropped items.
     */
    public static RoleFlag ITEM_PICKUP;
    /**
     * Allows the role to use ender pearls.
     */
    public static RoleFlag ENDER_PEARL;
    /**
     * Allows the role to trample farmland.
     */
    public static RoleFlag TRAMPLE_FARMLAND;
    /**
     * Allows the role to harvest crops.
     */
    public static RoleFlag HARVEST;
    /**
     * Allows the role to plant crops and saplings.
     */
    public static RoleFlag PLANT;
    /**
     * Allows the role to shear animals.
     */
    public static RoleFlag SHEAR;
    /**
     * Allows the role to use wind charge items and maces with wind burst enchantment.
     **/
    public static RoleFlag WIND_BURST;

    // -----------------------------------------------------------------------
    // Management role flags
    // -----------------------------------------------------------------------

    /**
     * Allows the role to trust other players.
     */
    public static RoleFlag PLAYER_TRUST;
    /**
     * Allows the role to untrust players with a lower role priority.
     */
    public static RoleFlag PLAYER_UNTRUST;
    /**
     * Allows the role to change the role of players with a lower role priority.
     */
    public static RoleFlag PLAYER_SETROLE;
    /**
     * Allows the role to claim chunks for the land.
     */
    public static RoleFlag LAND_CLAIM;
    /**
     * Allows claiming directly adjacent to another land, ignoring the distance config.
     */
    public static RoleFlag LAND_CLAIM_BORDER;
    /**
     * Allows the role to set the land spawn point.
     */
    public static RoleFlag SPAWN_SET;
    /**
     * Allows the role to edit natural land flags (e.g. mob spawning).
     */
    public static RoleFlag SETTING_EDIT_LAND;
    /**
     * Allows the role to edit settings of roles with a lower priority.
     */
    public static RoleFlag SETTING_EDIT_ROLE;
    /**
     * Allows the role to edit tax settings.
     */
    public static RoleFlag SETTING_EDIT_TAXES;
    /**
     * Allows the role to rename the land and change its title.
     */
    public static RoleFlag SETTING_EDIT_VARIOUS;
    /**
     * Allows the role to withdraw from the land bank.
     */
    public static RoleFlag BALANCE_WITHDRAW;
    /**
     * Allows the role to create and assign sub-areas.
     */
    public static RoleFlag AREA_ASSIGN;
    /**
     * Allows the role to ban players from the land.
     */
    public static RoleFlag PLAYER_BAN;
    /**
     * Allows the role to declare and manage wars.
     */
    public static RoleFlag WAR_MANAGE;

    /**
     * Provides damage immunity to players with this role (hidden by default).
     */
    public static RoleFlag NO_DAMAGE;

    // -----------------------------------------------------------------------
    // Natural / land flags
    // -----------------------------------------------------------------------

    /**
     * Controls whether entities (e.g. creepers) can grief blocks.
     */
    public static NaturalFlag ENTITY_GRIEFING;
    /**
     * Controls whether TNT can destroy blocks.
     */
    public static NaturalFlag TNT_GRIEFING;
    /**
     * Controls whether pistons from outside the land can push blocks into it.
     */
    public static NaturalFlag PISTON_GRIEFING;
    /**
     * Controls whether monsters can spawn (excludes spawners).
     */
    public static NaturalFlag MONSTER_SPAWN;
    /**
     * Controls whether phantoms can spawn.
     */
    public static NaturalFlag PHANTOM_SPAWN;
    /**
     * Controls whether animals can spawn (excludes spawners).
     */
    public static NaturalFlag ANIMAL_SPAWN;
    /**
     * Controls whether water can flow.
     */
    public static NaturalFlag WATERFLOW_ALLOW;
    /**
     * Controls whether enter/leave title messages are hidden.
     */
    public static NaturalFlag TITLE_HIDE;
    /**
     * Controls whether players can send join requests to the land.
     */
    public static NaturalFlag REQUEST_ACCEPT;
    /**
     * Controls whether fire can spread within the land.
     */
    public static NaturalFlag FIRE_SPREAD;
    /**
     * Controls whether leaves decay naturally.
     */
    public static NaturalFlag LEAF_DECAY;
    /**
     * Controls whether plants and trees grow.
     */
    public static NaturalFlag PLANT_GROWTH;
    /**
     * Controls whether snow and ice melt.
     */
    public static NaturalFlag SNOW_MELT;
    /**
     * Controls whether withers can damage animals.
     */
    public static NaturalFlag WITHER_ATTACK_ANIMAL;
    /**
     * Controls whether blocks like amethyst can spread within the land.
     */
    public static NaturalFlag BLOCK_SPREADING;
    /**
     * Controls whether copper golems from other areas can sort chests in this area.
     */
    public static NaturalFlag COPPER_GOLEM;

    /**
     * Controls whether the land expiration shield is active.
     */
    public static NaturalFlag EXPIRATION_SHIELD;
    /**
     * Controls whether the land is in peaceful mode (no PvP).
     */
    public static NaturalFlag PEACEFUL;

    // -----------------------------------------------------------------------
    // Nation role flags
    // -----------------------------------------------------------------------

    /**
     * Allows the role to edit nation settings.
     */
    public static RoleFlag NATION_EDIT;

    // -----------------------------------------------------------------------
    // Player flags
    // -----------------------------------------------------------------------

    /**
     * Controls whether the player sees land enter/leave messages.
     */
    public static PlayerFlag ENTER_MESSAGES;
    /**
     * Controls whether the player can receive land invites.
     */
    public static PlayerFlag RECEIVE_INVITES;
    /**
     * Controls whether inbox messages are shown in chat.
     */
    public static PlayerFlag SHOW_INBOX;

    private Flags() {
    }

    /**
     * Get a flag by its name.
     *
     * @param name the flag name (not case-sensitive)
     * @return the flag, or {@code null} if no flag with this name exists
     */
    @Nullable
    public static Flag<?> get(@NotNull String name) {
        Preconditions.checkNotNull(name, "Name cannot be null");
        return APIHandler.getFlagRegistry().get(name);
    }

    /**
     * Get the flag that would be used for an interaction with a block.
     *
     * @param block the interacted block
     * @return the applicable role flag, or {@code null} if this block interaction isn't covered by any flag.
     * For more specific results, use {@link #getInteract(Block, ItemStack)}.
     */
    @Nullable
    public static RoleFlag getInteract(@NotNull Block block) {
        return getInteract(block, null);
    }

    /**
     * Get the flag that would be used for an interaction with a block when a specific item is used.
     *
     * @param block the interacted block
     * @param item  the item used during the interaction, or {@code null} if no item
     * @return the applicable role flag, or {@code null} if this block interaction isn't covered by any flag
     */
    @Nullable
    public static RoleFlag getInteract(@NotNull Block block, @Nullable ItemStack item) {
        return APIHandler.getFlagRegistry().getInteract(block, item);
    }
}
