package me.angeschossen.lands.api.configuration;

import me.angeschossen.lands.api.configuration.modules.NationsConfig;
import me.angeschossen.lands.api.configuration.modules.WarsConfig;
import org.jetbrains.annotations.NotNull;

/**
 * Provides access to the Lands plugin configuration modules.
 */
public interface Configuration {
    /**
     * Get configuration options for wars.
     *
     * @return never null
     */
    @NotNull
    WarsConfig getWarsModuleConfig();

    /**
     * Get configuration options for nations.
     *
     * @return never null
     */
    @NotNull
    NationsConfig getNationsModuleConfig();

    /**
     * Get the main configuration options.
     *
     * @return never null
     */
    @NotNull
    MainConfig getMainConfig();
}
