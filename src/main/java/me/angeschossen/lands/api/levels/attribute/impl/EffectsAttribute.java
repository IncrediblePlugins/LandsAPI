package me.angeschossen.lands.api.levels.attribute.impl;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.lands.api.levels.attribute.LevelAttribute;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.nation.Nation;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Rewards potion effect within a nation.
 * A nation can set the effect in their nation menu.
 */
public class EffectsAttribute extends LevelAttribute {

    private final List<PotionEffect> effects;

    /**
     * Create instance of this attribute.
     * @param name name of the attribute
     * @param description description of the attribute
     * @param effects effects that this attribute unlocks
     */
    public EffectsAttribute(@NotNull String name, @NotNull String description,@NotNull List<PotionEffect> effects) {
        super(name, description);

        Checks.requireNonNull(effects, "effectd");
        this.effects = effects;
    }

    /**
     * Get collection of effects that are unlocked by this attribute.
     * @return collection of unlocked effects
     */
    public List<PotionEffect> getEffects() {
        return effects;
    }

    /**
     * Check if this attribute should be applied.
     * @param memberHolder Land or nation
     * @return true, if this memberHolde is a nation
     */
    @Override
    public boolean shouldApply(@NotNull MemberHolder memberHolder) {
        return memberHolder instanceof Nation;
    }
}
