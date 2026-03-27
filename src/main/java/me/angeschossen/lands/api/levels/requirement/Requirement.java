package me.angeschossen.lands.api.levels.requirement;

import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.memberholder.HolderType;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Requirements define which requirements a land or nation needs to fullfil in order to progress to the next level.
 * There can only be one requirement with the name from {@link #getId()}.
 */
public interface Requirement {

    /**
     * Get the unique ID of this requirement. For display name, use {@link #getTitle()} instead.
     *
     * @return unique and never null
     */
    @NotNull
    String getId();

    /**
     * Get the target of the requirement.
     *
     * @return either {@link HolderType#LAND} or {@link HolderType#NATION}
     */
    @NotNull
    HolderType getTarget();

    /**
     * Should the requirement apply to the land or nation?
     * Overriding this method allows you to exclude specific lands or nations
     * from the requirement.
     *
     * @param memberHolder land or nation
     * @return false, if should not apply
     */
    default boolean shouldApply(@NotNull MemberHolder memberHolder) {
        return true;
    }

    /**
     * Get the title of this requirement.
     *
     * @return might include parsed color
     */
    @NotNull
    String getTitle();

    /**
     * Retrieve the value.
     *
     * @param memberHolder land or nation
     * @return example: 2 placed hoppers
     */
    @NotNull
    CompletableFuture<Float> retrieveValue(@NotNull MemberHolder memberHolder);

    /**
     * Retrieves the value if {@link #getCachedValue(MemberHolder)} returns null
     *
     * @param memberHolder land or nation
     * @return never null
     */
    default @NotNull CompletableFuture<Float> getCachedValueOrRetrieve(@NotNull MemberHolder memberHolder) {
        @Nullable Float cachedValue = getCachedValue(memberHolder);
        if (cachedValue != null) {
            return CompletableFuture.completedFuture(cachedValue);
        }

        return retrieveValue(memberHolder).thenApply(res -> {
            memberHolder.updateRequirementCache(getId(), res);
            return res;
        });
    }

    /**
     * The the cached value that was requested previously via {@link #retrieveValue(MemberHolder)}.
     *
     * @param memberHolder land or nation
     * @return null, if not cached
     */
    default @Nullable Float getCachedValue(@NotNull MemberHolder memberHolder) {
        return memberHolder.getCachedRequirement(getId());
    }

    /**
     * Get the progress percentage.
     *
     * @param memberHolder land or nation
     * @param value        the value from for example {@link #getCachedValueOrRetrieve(MemberHolder)}
     * @return example: 50 because 2/4 required hoppers placed. Will return 0, if value is not cached
     */
    default float calculateProgress(@NotNull MemberHolder memberHolder, float value) {
        return (value / getRequired()) * 100;
    }

    /**
     * Get the description of this requirement.
     *
     * @return used for menus
     */
    @NotNull
    List<String> getDescription();

    /**
     * Get the required value.
     *
     * @return example: 4 hoppers placed
     */
    float getRequired();

    /**
     * Used for menus to display the requirement progress.
     *
     * @param memberHolder land or nation
     * @param value        the current value from for example {@link #getCachedValueOrRetrieve(MemberHolder)}
     * @return displays the progress
     */
    default @NotNull String makeProgressDisplay(@NotNull MemberHolder memberHolder, float value) {
        return value + "/" + getRequired();
    }

    /**
     * Get the plugin that provides this requirement.
     *
     * @return plugin that provides this requirement
     */
    @NotNull LandsIntegration getIntegration();

    /**
     * Check if this land or nation already fulfills this requirement.
     *
     * @param memberHolder land or nation
     * @param value        the current value
     * @return true, if the land or nation already fulfills this requirement
     */
    default boolean fulfillsRequirement(@NotNull MemberHolder memberHolder, float value) {
        return value >= getRequired();
    }
}
