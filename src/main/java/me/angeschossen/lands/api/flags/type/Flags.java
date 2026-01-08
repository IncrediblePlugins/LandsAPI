package me.angeschossen.lands.api.flags.type;

import com.google.common.base.Preconditions;
import me.angeschossen.lands.api.flags.type.parent.Flag;
import me.angeschossen.lands.api.handler.APIHandler;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Flags {

    public static RoleFlag BLOCK_BREAK;
    public static RoleFlag BLOCK_PLACE;
    public static RoleFlag ATTACK_PLAYER;
    public static RoleFlag ATTACK_ANIMAL;
    public static RoleFlag ATTACK_MONSTER;
    public static RoleFlag BLOCK_IGNITE;
    public static RoleFlag INTERACT_GENERAL;
    public static RoleFlag INTERACT_MECHANISM;
    public static RoleFlag INTERACT_CONTAINER;
    public static RoleFlag INTERACT_DOOR;
    public static RoleFlag INTERACT_TRAPDOOR;
    public static RoleFlag INTERACT_VILLAGER;
    public static RoleFlag FLY, ELYTRA;
    public static RoleFlag SPAWN_TELEPORT;
    public static RoleFlag LAND_ENTER;
    public static RoleFlag VEHICLE_USE;
    public static RoleFlag ITEM_PICKUP;
    public static RoleFlag ENDER_PEARL;
    public static RoleFlag TRAMPLE_FARMLAND;
    public static RoleFlag HARVEST, PLANT, SHEAR;

    public static RoleFlag PLAYER_TRUST;
    public static RoleFlag PLAYER_UNTRUST;
    public static RoleFlag PLAYER_SETROLE;
    public static RoleFlag LAND_CLAIM;
    public static RoleFlag LAND_CLAIM_BORDER;
    public static RoleFlag SPAWN_SET;
    public static RoleFlag SETTING_EDIT_LAND;
    public static RoleFlag SETTING_EDIT_ROLE;
    public static RoleFlag SETTING_EDIT_TAXES;
    public static RoleFlag SETTING_EDIT_VARIOUS;
    public static RoleFlag BALANCE_WITHDRAW;
    public static RoleFlag AREA_ASSIGN;
    public static RoleFlag PLAYER_BAN;
    public static RoleFlag WAR_MANAGE;

    public static RoleFlag NO_DAMAGE;

    public static NaturalFlag ENTITY_GRIEFING;
    public static NaturalFlag TNT_GRIEFING;
    public static NaturalFlag PISTON_GRIEFING;
    public static NaturalFlag MONSTER_SPAWN;
    public static NaturalFlag PHANTOM_SPAWN;
    public static NaturalFlag ANIMAL_SPAWN;
    public static NaturalFlag WATERFLOW_ALLOW;
    public static NaturalFlag TITLE_HIDE;
    public static NaturalFlag REQUEST_ACCEPT;
    public static NaturalFlag FIRE_SPREAD;
    public static NaturalFlag LEAF_DECAY;
    public static NaturalFlag PLANT_GROWTH;
    public static NaturalFlag SNOW_MELT;
    public static NaturalFlag WITHER_ATTACK_ANIMAL;
    public static NaturalFlag BLOCK_SPREADING;
    public static NaturalFlag COPPER_GOLEM;

    public static NaturalFlag EXPIRATION_SHIELD;
    public static NaturalFlag PEACEFUL;

    // nation
    public static RoleFlag NATION_EDIT;

    // player
    public static PlayerFlag ENTER_MESSAGES;
    public static PlayerFlag RECEIVE_INVITES;
    public static PlayerFlag SHOW_INBOX;

    private Flags() {
    }

    /**
     * Get a flag by its name.
     *
     * @param name the name isn't case sensitive
     * @return null, if no flag with this name exists
     */
    @Nullable
    public static Flag<?> get(@NotNull String name) {
        Preconditions.checkNotNull(name, "Name cannot be null");
        return APIHandler.getFlagRegistry().get(name);
    }

    /**
     * Get the flag that would be used for an interaction with this block
     *
     * @param block the interacted block
     * @return null, if this block interaction isn't covered by any flag. To get more specific results, use {@link #getInteract(Block, ItemStack)}.
     */
    @Nullable
    public static RoleFlag getInteract(@NotNull Block block) {
        return getInteract(block, null);
    }

    /**
     * Get the flag that would be used for an interaction with this block
     *
     * @param block the interacted block
     * @param item  the item that is used to interaction with the block
     * @return null, if this block interaction isn't covered by any flag
     */
    @Nullable
    public static RoleFlag getInteract(@NotNull Block block, @Nullable ItemStack item) {
        return APIHandler.getFlagRegistry().getInteract(block, item);
    }
}
