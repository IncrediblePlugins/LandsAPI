package me.angeschossen.lands.api.levels;

import com.github.angeschossen.pluginframework.api.events.ExpressionEntity;
import me.angeschossen.lands.api.levels.attribute.LevelAttribute;
import me.angeschossen.lands.api.levels.requirement.Requirement;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * Represents a level.
 * Usually configured in the plugin's "levels.yml" configuration file.
 */
public interface Level extends ExpressionEntity {
    /**
     * Add an attribute to this level, which will be rewarded to the land or nation.
     * This just provides the description etc. The functionality must be implemented by yourself.
     *
     * @param attribute the attribute to add
     * @throws IllegalStateException a attribute with this name already exists
     */
    void addAttribute(@NotNull LevelAttribute attribute) throws IllegalStateException;

    /**
     * Add a requirement that is required for a land or nation to progress to this level.
     *
     * @param requirement the requirement
     * @throws IllegalArgumentException if a requirement with that name already exists
     * @throws IllegalStateException    If Lands is already enabled. Requirements must be added before Lands is enabled.
     *                                  You can use {@link me.angeschossen.lands.api.LandsIntegration#onLoad(Runnable)} to achieve that.
     */
    void addRequirement(@NotNull Requirement requirement) throws IllegalArgumentException, IllegalStateException;

    /**
     * Get the unique name of this requirement.
     *
     * @return unique name
     */
    @NotNull
    String getName();

    /**
     * Get the next level.
     *
     * @return null, if there's no next level
     */
    @Nullable Level getNext();

    /**
     * Get the previous level.
     *
     * @return null, if there's no previous level
     */
    @Nullable Level getPrevious();

    /**
     * Get the order / position of this level.
     * Related to {@link #getNext()} and {@link #getPrevious()}.
     *
     * @return the order of this level
     */
    int getPosition();

    /**
     * Evaluate the progression for level up.
     *
     * @param memberHolder land or nation
     * @return progression in percentage (0-100)
     */
    float getProgress(@NotNull MemberHolder memberHolder);

    /**
     * Get all requirements.
     *
     * @return collection of requirements
     */
    @NotNull Collection<? extends Requirement> getRequirements();

    /**
     * Check if this level can be applied to the land or nation.
     * Usually this just differentiates between land or nation.
     *
     * @param memberHolder land or nation
     * @return true, if this level can be applied to the land or nation
     */
    CompletableFuture<Boolean> matches(@NotNull MemberHolder memberHolder);
}
