package me.angeschossen.lands.api.levels.requirement;

import com.github.angeschossen.pluginframework.api.utils.StringUtils;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * Requirements define which requirements a land or nation needs to fullfil in order to progress to the next level.
 * Used for requirements that are calculated each time, a plugin requests its progress.
 * There is an implementation ({@link CachedRequirement}) of this class that prevents to recalculation at each request.
 */
public abstract class Requirement implements me.angeschossen.lands.api.levels.Requirement {

    protected final @NotNull String name, title;
    protected final @NotNull List<String> description;
    protected final float required;
    protected final @NotNull Plugin plugin;

    /**
     * Create an instance of this requirement.
     *
     * @param plugin          plugin that provides this requirement
     * @param name            the unique name of this requirement
     * @param title           title of this requirement. Used in menus
     * @param description     description of this requirement. Used in menus
     * @param required        required progress (not percentage) to consider this requirement as fullfilled
     * @param requiredDisplay displayed as the required value in menus
     */
    public Requirement(@NotNull Plugin plugin, @NotNull String name, @NotNull String title, @NotNull List<String> description, float required, @NotNull String requiredDisplay) {
        Objects.requireNonNull(plugin);
        Objects.requireNonNull(name);
        Objects.requireNonNull(title);
        Objects.requireNonNull(description);
        Objects.requireNonNull(requiredDisplay);

        StringUtils stringUtils = APIHandler.getInstance().getStringUtils();
        description.replaceAll(s -> stringUtils.colorize(s.replace("{req}", requiredDisplay)));

        this.plugin = plugin;
        this.name = StringUtils.toLowerCase(name);
        this.required = required;
        this.title = title.replace("{req}", requiredDisplay);
        this.description = description;
    }


    /**
     * Get the plugin that provides this requirement.
     *
     * @return plugin that provides this requirement
     */
    @Override
    public @NotNull Plugin getPlugin() {
        return plugin;
    }

    /**
     * Get the required value.
     *
     * @return example: 4 hoppers placed
     */
    @Override
    public final float getRequired() {
        return required;
    }

    /**
     * Get the progress percentage.
     *
     * @param memberHolder land or nation
     * @return example: 50 because 2/4 required hoppers placed
     */
    @Override
    @NotNull
    public String getProgressDisplay(@NotNull MemberHolder memberHolder) {
        return getValue(memberHolder) + "/" + required;
    }

    /**
     * Get the progress in a numerical value.
     *
     * @param memberHolder land or nation
     * @return numerical progress
     */
    @Override
    public final float getProgress(@NotNull MemberHolder memberHolder) {
        return (getValue(memberHolder) / required) * 100;
    }

    public final boolean shouldRecalculateLevel(float previousValue, float currentValue) {
        if (previousValue < required) {
            return currentValue >= required;
        } else {
            return currentValue < required;
        }
    }

    /**
     * Check if this land or nation already fullfills this requirement.
     *
     * @param memberHolder land or nation
     * @return true, if the land or nation already fullfills this requirement
     */
    @Override
    public boolean matches(@NotNull MemberHolder memberHolder) {
        return getValue(memberHolder) >= required;
    }

    /**
     * Get the description of this requirement.
     *
     * @return used for menus
     */
    @Override
    public @NotNull List<String> getDescription() {
        return description;
    }

    /**
     * Get the unique name of this requirement. For display name, use {@link #getTitle()} instead.
     *
     * @return unique name
     */
    @Override
    @NotNull
    public final String getName() {
        return name;
    }

    /**
     * Get the title of this requirement.
     *
     * @return might include parsed color
     */
    @Override
    public @NotNull String getTitle() {
        return title;
    }
}
