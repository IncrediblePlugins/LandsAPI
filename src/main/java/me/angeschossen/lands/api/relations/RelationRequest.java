package me.angeschossen.lands.api.relations;

import com.github.angeschossen.pluginframework.api.holder.Changeable;
import org.jetbrains.annotations.NotNull;

public interface RelationRequest extends Changeable {
    /**
     * Get the result of this request.
     *
     * @return never null
     */
    @NotNull Relation getIntent();
}
