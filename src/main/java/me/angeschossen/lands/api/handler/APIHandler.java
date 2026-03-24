package me.angeschossen.lands.api.handler;

import com.github.angeschossen.pluginframework.api.configuration.Configuration;
import com.github.angeschossen.pluginframework.api.utils.StringUtils;
import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.configuration.modules.ModuleConfig;
import me.angeschossen.lands.api.flags.FlagRegistry;
import me.angeschossen.lands.api.levels.LevelsHandler;
import me.angeschossen.lands.api.utils.PlayerUtils;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This class is not intended for direct usage and might change at any time.
 */
public class APIHandler {

    private static APIHandler instance;
    private final @NotNull Configuration config;
    private final @NotNull ModuleConfig warsConfig, nationsConfig;
    private final @NotNull Plugin plugin;
    private static LandsIntegrationFactory landsIntegrationFactory;
    private final @NotNull LevelsHandler levelsHandler;
    private final @NotNull LandsIntegration legacySupport;
    private static FlagRegistry flagRegistry;
    private final @NotNull StringUtils stringUtils;
    private final @NotNull PlayerUtils playerUtils;
    private static FlagFactory flagFactory;

    /**
     * Get the factory used for creating flags.
     *
     * @return the flag factory, or {@code null} if not yet initialized
     */
    public static FlagFactory getFlagFactory() {
        return flagFactory;
    }

    /**
     * Get the nations module configuration.
     *
     * @return nations module config
     */
    public ModuleConfig getNationsConfig() {
        return nationsConfig;
    }

    /**
     * Get the wars module configuration.
     *
     * @return wars module config; never null
     */
    public @NotNull ModuleConfig getWarsConfig() {
        return warsConfig;
    }

    /**
     * Get the main plugin configuration.
     *
     * @return main config; never null
     */
    public @NotNull Configuration getConfig() {
        return config;
    }

    /**
     * Set the base factories used by the API. Can only be called once.
     *
     * @param fac      the lands integration factory
     * @param flagFac  the flag factory
     * @param flagReg  the flag registry
     */
    public static void setBase(LandsIntegrationFactory fac, FlagFactory flagFac, FlagRegistry flagReg) {
        Objects.requireNonNull(fac);
        Objects.requireNonNull(flagFac);
        Objects.requireNonNull(flagReg);

        if (landsIntegrationFactory == null) {
            landsIntegrationFactory = fac;
            flagFactory = flagFac;
            flagRegistry = flagReg;
        }
    }

    private APIHandler(@NotNull Plugin plugin,
                       @NotNull Configuration config,
                       @NotNull ModuleConfig warsConfig, @NotNull ModuleConfig nationsConfig,
                       @NotNull LevelsHandler levelsHandler,
                       @NotNull LandsIntegration legacySupport,
                       @NotNull FlagRegistry flagRegistry,
                       @NotNull StringUtils stringUtils,
                       @NotNull PlayerUtils playerUtils) {
        this.config = config;
        this.playerUtils = playerUtils;
        this.legacySupport = legacySupport;
        this.warsConfig = warsConfig;
        this.nationsConfig = nationsConfig;
        this.plugin = plugin;
        APIHandler.flagRegistry = flagRegistry;
        this.levelsHandler = levelsHandler;
        this.stringUtils = stringUtils;
    }

    /**
     * Get the factory used for creating {@link LandsIntegration} instances.
     *
     * @return the integration factory; never null
     */
    @NotNull
    public static LandsIntegrationFactory getLandsIntegrationFactory() {
        return landsIntegrationFactory;
    }

    /**
     * Get the legacy support integration instance.
     *
     * @return legacy support instance; never null
     */
    public @NotNull LandsIntegration getLegacySupport() {
        return legacySupport;
    }

    /**
     * Get the Lands plugin instance.
     *
     * @return the plugin; never null
     */
    public @NotNull Plugin getPlugin() {
        return plugin;
    }

    /**
     * Get player-related utility methods.
     *
     * @return player utils; never null
     */
    public @NotNull PlayerUtils getPlayerUtils() {
        return playerUtils;
    }

    /**
     * Get the handler for the land and nation levels system.
     *
     * @return levels handler; never null
     */
    public @NotNull LevelsHandler getLevelsHandler() {
        return levelsHandler;
    }

    /**
     * Get string-related utility methods.
     *
     * @return string utils; never null
     */
    public @NotNull StringUtils getStringUtils() {
        return stringUtils;
    }

    /**
     * Get the flag registry, which holds all registered flags.
     *
     * @return the flag registry, or {@code null} if not yet initialized
     */
    public static FlagRegistry getFlagRegistry() {
        return flagRegistry;
    }

    /**
     * Get the singleton instance of this handler.
     *
     * @return the handler instance, or {@code null} if not yet initialized via {@link #init}
     */
    public static APIHandler getInstance() {
        return instance;
    }

    /**
     * Initialize the singleton. Must be called exactly once during plugin startup.
     *
     * @param plugin        the Lands plugin
     * @param config        the main plugin configuration
     * @param warsConfig    the wars module configuration
     * @param nationsConfig the nations module configuration
     * @param levelsHandler the levels handler
     * @param legacySupport the legacy support integration
     * @param flagRegistry  the flag registry
     * @param stringUtils   string utility instance
     * @param playerUtils   player utility instance
     * @throws IllegalStateException if already initialized
     */
    public static void init(@NotNull Plugin plugin,
                            @NotNull Configuration config,
                            @NotNull ModuleConfig warsConfig, @NotNull ModuleConfig nationsConfig,
                            @NotNull LevelsHandler levelsHandler,
                            @NotNull LandsIntegration legacySupport,
                            @NotNull FlagRegistry flagRegistry,
                            @NotNull StringUtils stringUtils,
                            @NotNull PlayerUtils playerUtils) {
        Objects.requireNonNull(config);
        Objects.requireNonNull(warsConfig);
        Objects.requireNonNull(nationsConfig);

        if (instance != null) {
            throw new IllegalStateException("Already initialized.");
        }

        instance = new APIHandler(plugin,
                config, warsConfig, nationsConfig,
                levelsHandler,
                legacySupport,
                flagRegistry,
                stringUtils,
                playerUtils);
    }
}
