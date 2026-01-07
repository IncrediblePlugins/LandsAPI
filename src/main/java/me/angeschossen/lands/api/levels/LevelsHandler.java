package me.angeschossen.lands.api.levels;

import me.angeschossen.lands.api.levels.requirement.Requirement;
import me.angeschossen.lands.api.memberholder.HolderType;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Used to handle levels and their progression.
 */
public interface LevelsHandler {
    /**
     * Get all levels that can be applied to lands ({@link me.angeschossen.lands.api.land.Land}).
     *
     * @return collection of levels that can be applied to lands
     */
    @NotNull List<? extends Level> getLandLevels();

    /**
     * Get all levels that can be applied to nations ({@link me.angeschossen.lands.api.nation.Nation}).
     *
     * @return collection of levels that can be applied to nations
     */
    @NotNull List<? extends Level> getNationLevels();

    /**
     * Get the land configuration section from the "levels.yml" configuration file.
     *
     * @return land level configuration section
     */
    @Nullable Map<Level, ConfigurationSection> getLandSection();

    /**
     * Get the nation configuration section from the "levels.yml" configuration file.
     *
     * @return nation level configuration section
     */
    @Nullable Map<Level, ConfigurationSection> getNationSection();

    /**
     * Register the requirement.
     *
     * @param requirement to register
     * @return the registred requirement
     */
    Requirement registerRequirement(@NotNull Requirement requirement);

    /**
     * Get all registered requirements.
     *
     * @param target either {@link HolderType#LAND} or {@link HolderType#NATION}
     * @return all registred requirements
     */
    @NotNull Collection<Requirement> getRegisteredRequirements(@NotNull HolderType target);

    /**
     * Get a requirement by its name.
     *
     * @param name   the name to look for. Case insensitive
     * @param target either {@link HolderType#LAND} or {@link HolderType#NATION}
     * @return null, if none found with that name
     */
    @Nullable Requirement getRequirementById(@NotNull HolderType target, @NotNull String name);
}
