package me.angeschossen.lands.api.configuration.modules;

import com.github.angeschossen.pluginframework.api.configuration.Configuration;

/**
 * Interface for module configs such as wars and nations.
 */
public interface ModuleConfig extends Configuration {

    /**
     * Modules such as wars and nations can be disabled.
     *
     * @return true, if this module is disabled
     */
    boolean isEnabled();
}
