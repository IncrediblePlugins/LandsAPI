package me.angeschossen.lands.api.relations;

import com.github.angeschossen.pluginframework.api.holder.Changeable;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a pending diplomatic relation request between two lands or nations.
 */
public interface RelationRequest extends Changeable {
    /**
     * Get the type of diplomatic relation that was requested (e.g. ally or enemy).
     *
     * @return the requested relation; never null
     */
    @NotNull Relation getIntent();
}
