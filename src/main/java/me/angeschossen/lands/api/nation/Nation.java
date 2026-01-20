package me.angeschossen.lands.api.nation;

import me.angeschossen.lands.api.exceptions.LandAlreadyInNationException;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.player.LandPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface Nation extends MemberHolder {

    /**
     * Add a land to this nation.
     *
     * @param land   the land to add
     * @param filter don't send land join inbox message to this player
     * @return false, if the land is already part of this nation
     * @throws LandAlreadyInNationException if the land is already part of a different nation
     */
    boolean addLand(@NotNull Land land, @Nullable LandPlayer filter) throws LandAlreadyInNationException;

    /**
     * Delete this nation.
     *
     * @param deleter The player that initiates the deletion.
     *                If provided: This is mainly used to prevent the player from receiving a message from your plugin and additionally a inbox message in lands of the nation. Just to prevent spam.
     * @return false, if a 3rd party plugin canceled the deletion.
     */
    CompletableFuture<Boolean> delete(@Nullable LandPlayer deleter);

    /**
     * Get the capital of this nation.
     *
     * @return The capital land of this nation
     */
    @NotNull
    Land getCapital();

    /**
     * Check if a land is part of this nation.
     *
     * @return false if land, is not a member of this nation
     */
    boolean isMember(@NotNull Land land);

    /**
     * Set the capital of this nation.
     *
     * @param land the new capital
     * @throws IllegalStateException If the land isn't part of this nation. You need to add it first via {@link #addLand(Land, LandPlayer)}.
     */
    void setCapital(@NotNull Land land) throws IllegalArgumentException;

    /**
     * Get the members of this nation.
     *
     * @return All lands, including the capital, that are part of this nation
     */
    @NotNull
    Collection<? extends Land> getLands();

    /**
     * Remove a land from this nation.
     *
     * @param land   the land to remove
     * @param filter don't send land leave inbox messages to this player
     * @return false, if the land wasn't part of this nation
     * @throws IllegalStateException if the land is the capital of this nation
     */
    boolean removeLand(@NotNull Land land, @Nullable LandPlayer filter) throws IllegalStateException;
}