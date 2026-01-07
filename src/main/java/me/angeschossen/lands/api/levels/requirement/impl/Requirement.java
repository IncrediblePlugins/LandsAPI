package me.angeschossen.lands.api.levels.requirement.impl;

import com.github.angeschossen.pluginframework.api.utils.StringUtils;
import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.memberholder.HolderType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * Requirements define which requirements a land or nation needs to fullfil in order to progress to the next level.
 */
public abstract class Requirement implements me.angeschossen.lands.api.levels.requirement.Requirement {

    protected final @NotNull String id, title;
    protected final @NotNull List<String> description;
    protected final float required;
    protected final @NotNull LandsIntegration plugin;
    protected final @NotNull HolderType holderType;

    /**
     * Create an instance of this requirement.
     *
     * @param plugin          plugin that provides this requirement
     * @param holderType      either {@link HolderType#LAND} or {@link HolderType#NATION}
     * @param id              the unique ID of this requirement
     * @param title           title of this requirement. Used in menus
     * @param description     description of this requirement. Used in menus
     * @param required        required value (not percentage) to consider this requirement as fulfilled
     * @param requiredDisplay displayed as the required value in menus
     */
    public Requirement(@NotNull LandsIntegration plugin, @NotNull HolderType holderType, @NotNull String id, @NotNull String title, @NotNull List<String> description, float required, @NotNull String requiredDisplay) {
        Objects.requireNonNull(plugin);
        Objects.requireNonNull(holderType);
        Objects.requireNonNull(id);
        Objects.requireNonNull(title);
        Objects.requireNonNull(description);
        Objects.requireNonNull(requiredDisplay);

        StringUtils stringUtils = APIHandler.getInstance().getStringUtils();
        description.replaceAll(s -> stringUtils.colorize(s.replace("{req}", requiredDisplay)));

        this.plugin = plugin;
        this.holderType = holderType;
        this.id = StringUtils.toLowerCase(id);
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
    public @NotNull LandsIntegration getIntegration() {
        return plugin;
    }

    @Override
    public @NotNull HolderType getTarget() {
        return holderType;
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
    public final String getId() {
        return id;
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
