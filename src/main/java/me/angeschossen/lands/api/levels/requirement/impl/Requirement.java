package me.angeschossen.lands.api.levels.requirement.impl;

import com.github.angeschossen.pluginframework.api.utils.StringUtils;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.memberholder.HolderType;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Requirements define which requirements a land or nation needs to fullfil in order to progress to the next level.
 */
public abstract class Requirement implements me.angeschossen.lands.api.levels.requirement.Requirement {

    protected final @NotNull String name, title;
    protected final @NotNull List<String> description;
    protected final float required;
    protected final @NotNull Plugin plugin;
    protected final @NotNull HolderType holderType;

    /**
     * Create an instance of this requirement.
     *
     * @param plugin          plugin that provides this requirement
     * @param holderType      either {@link HolderType#LAND} or {@link HolderType#NATION}
     * @param name            the unique name of this requirement
     * @param title           title of this requirement. Used in menus
     * @param description     description of this requirement. Used in menus
     * @param required        required value (not percentage) to consider this requirement as fullfilled
     * @param requiredDisplay displayed as the required value in menus
     */
    public Requirement(@NotNull Plugin plugin, @NotNull HolderType holderType, @NotNull String name, @NotNull String title, @NotNull List<String> description, float required, @NotNull String requiredDisplay) {
        Objects.requireNonNull(plugin);
        Objects.requireNonNull(holderType);
        Objects.requireNonNull(name);
        Objects.requireNonNull(title);
        Objects.requireNonNull(description);
        Objects.requireNonNull(requiredDisplay);

        StringUtils stringUtils = APIHandler.getInstance().getStringUtils();
        description.replaceAll(s -> stringUtils.colorize(s.replace("{req}", requiredDisplay)));

        this.plugin = plugin;
        this.holderType = holderType;
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
