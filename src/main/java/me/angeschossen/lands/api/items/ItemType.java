package me.angeschossen.lands.api.items;

import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum ItemType {
    /**
     * Block used to claim the chunk it is being placed in.
     */
    CLAIM_BLOCK,
    /**
     * Block that creates a capture flag upon placement in wars.
     */
    CAPTURE_FLAG,
    /**
     * Item that creates a capture flag for the KoTH gamemode.
     */
    CAPTURE_FLAG_KOTH,
    /**
     * Block that creates a temporary land.
     */
    CAMP {
        @Override
        public @NotNull ItemStack build(@Nullable LandPlayer owner) {
            return build(owner, 1);
        }

        @NotNull
        public ItemStack build(@Nullable LandPlayer owner, int radius) {
            return APIHandler.getLandsIntegrationFactory().buildCampItem(owner, radius);
        }
    },
    /**
     * Tool used to select areas for claiming or sub area creation.
     */
    SELECTION,
    /**
     * Tool used to show info about chunk that it points at.
     */
    INFO;

    /**
     * Create a new item stack of this type.
     *
     * @param owner if null, no owner applied to item
     * @return never null
     */
    @NotNull
    public ItemStack build(@Nullable LandPlayer owner) {
        return APIHandler.getLandsIntegrationFactory().buildItemStack(this, owner);
    }
}
