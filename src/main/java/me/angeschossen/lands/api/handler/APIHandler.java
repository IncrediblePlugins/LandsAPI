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

    public static FlagFactory getFlagFactory() {
        return flagFactory;
    }

    public ModuleConfig getNationsConfig() {
        return nationsConfig;
    }

    public @NotNull ModuleConfig getWarsConfig() {
        return warsConfig;
    }

    public @NotNull Configuration getConfig() {
        return config;
    }

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

    @NotNull
    public static LandsIntegrationFactory getLandsIntegrationFactory() {
        return landsIntegrationFactory;
    }

    public @NotNull LandsIntegration getLegacySupport() {
        return legacySupport;
    }

    public @NotNull Plugin getPlugin() {
        return plugin;
    }

    public @NotNull PlayerUtils getPlayerUtils() {
        return playerUtils;
    }

    public @NotNull LevelsHandler getLevelsHandler() {
        return levelsHandler;
    }

    public @NotNull StringUtils getStringUtils() {
        return stringUtils;
    }

    public static FlagRegistry getFlagRegistry() {
        return flagRegistry;
    }

    public static APIHandler getInstance() {
        return instance;
    }

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
