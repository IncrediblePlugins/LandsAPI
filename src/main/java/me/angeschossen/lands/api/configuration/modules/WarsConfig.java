package me.angeschossen.lands.api.configuration.modules;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Config for nations.yml
 */
public interface WarsConfig extends ModuleConfig {
    /**
     * Get collection of placeable blocks during a war inside a land that is taking part in the war.
     * @return never null
     */
    @NotNull
    Set<Material>getBlockPlaceList();

    /**
     * Get collection of breakable blocks during a war inside a land that is taking part in the war.
     * @return never null
     */
    @NotNull
    Set<Material> getBlockBreakList();
}
