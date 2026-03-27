package me.angeschossen.lands.api.flags.type;

import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.flags.enums.FlagTarget;
import me.angeschossen.lands.api.flags.type.parent.DefaultStateFlag;
import me.angeschossen.lands.api.flags.type.parent.Flag;
import me.angeschossen.lands.api.handler.APIHandler;
import org.jetbrains.annotations.NotNull;

/**
 * Natural flags are used for events that don't primarily involve a player. For example an exploding tnt etc.
 */
public interface NaturalFlag extends Flag<NaturalFlag>, DefaultStateFlag<NaturalFlag> {

    /**
     * Create instance of a natural flag.
     *
     * @param landsIntegration the integration
     * @param flagTarget       target of the flag
     * @param name             unique name of the flag
     * @return created flag instance
     */
    @NotNull
    static NaturalFlag of(@NotNull LandsIntegration landsIntegration, @NotNull FlagTarget flagTarget, @NotNull String name) {
        return APIHandler.getFlagFactory().naturalFlagOf(landsIntegration, flagTarget, name);
    }
}
