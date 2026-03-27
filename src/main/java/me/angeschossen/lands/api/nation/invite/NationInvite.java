package me.angeschossen.lands.api.nation.invite;

import com.github.angeschossen.pluginframework.api.holder.Changeable;
import me.angeschossen.lands.api.exceptions.LandAlreadyInNationException;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an invitation sent by a nation to a land to join it.
 */
public interface NationInvite extends Changeable {

    /**
     * Get the nation that sent this invite.
     *
     * @return The sender
     */
    @NotNull
    Nation getSender();

    /**
     * Get the land that received this invite.
     *
     * @return The receiving land
     */
    @NotNull
    Land getReceiver();

    /**
     * Accept this invite.
     *
     * @return false, if the land or nation does not exist anymore
     * @throws LandAlreadyInNationException If the land is already part of another nation
     */
    boolean accept() throws LandAlreadyInNationException;

    /**
     * Deny this invite.
     */
    void deny();

    /**
     * Revoke this invite.
     */
    void revoke();
}
