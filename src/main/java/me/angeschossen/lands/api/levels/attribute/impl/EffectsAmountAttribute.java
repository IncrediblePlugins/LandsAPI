package me.angeschossen.lands.api.levels.attribute.impl;

import me.angeschossen.lands.api.levels.attribute.LevelAttribute;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.nation.Nation;
import org.jetbrains.annotations.NotNull;

/**
 * Allows a nation to have a specified amount of potion effects active at the same time.
 */
public class EffectsAmountAttribute extends LevelAttribute {

    private final int amount;

    /**
     * Create instance.
     * @param name Name of the effect level attribute
     * @param description The description
     * @param amount Amount of active effects
     */
    public EffectsAmountAttribute(@NotNull String name, @NotNull String description, int amount) {
        super(name, description);

        this.amount = amount;
    }

    /**
     * Get the amount of potion effects, than can be active at the same time.
     * @return Amount of potion effects
     */
    public int getValue() {
        return amount;
    }

    /**
     * Check if this attribute can be applied.
     * @param memberHolder Land or nation
     * @return false, if it's not a nation
     */
    @Override
    public boolean shouldApply(@NotNull MemberHolder memberHolder) {
        return memberHolder instanceof Nation;
    }
}
