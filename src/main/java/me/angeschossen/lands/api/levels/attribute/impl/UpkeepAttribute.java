package me.angeschossen.lands.api.levels.attribute.impl;

import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.levels.attribute.LevelAttribute;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

/**
 * A level attribute that modifies the upkeep cost of a land or nation.
 */
public class UpkeepAttribute extends LevelAttribute {
    private final double value;

    /**
     * Create instance.
     * @param name Name of the effect level attribute
     * @param description The description
     * @param value Level of the effect
     */
    public UpkeepAttribute(@NotNull String name, @NotNull String description, double value) {
        super(name, description.replace("{value}", (value >= 0 ? ChatColor.RED + "+ " : ChatColor.GREEN + "- ") + Math.abs(value)));

        this.value = value;
    }

    /**
     * The the upkeep modification.
     * @return if negative, the upkeep is reduced by this attribute
     */
    public double getValue() {
        return value;
    }

    /**
     * Modify the upkeep of a land or nation.
     * @param value the current upkeep
     * @return modified upkeep
     */
    public double modifyUpkeep(double value) {
        if (this.value == 0) {
            return value;
        }

        double result = value + ((value / 100) * this.value);
        return Math.max(0, result); // prevent minus upkeep
    }

    /**
     * Check if this attribute should be applied.
     * @param memberHolder Land or nation
     * @return this is only applied to lands, that aren't part of nation, or nations.
     */
    @Override
    public boolean shouldApply(@NotNull MemberHolder memberHolder) {
        if (memberHolder instanceof Land) {
            return ((Land) memberHolder).getNation() == null;
        } else {
            return true;
        }
    }
}
