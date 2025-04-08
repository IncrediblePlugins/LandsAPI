package me.angeschossen.lands.api.player.invite;

import com.github.angeschossen.pluginframework.api.holder.Changeable;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.player.invite.result.InviteResult;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface Invite<T extends InviteResult> extends Changeable {

    /**
     * Get the result of the acceptance of this invite.
     * @return Intent of this invite
     */
    @NotNull
    InviteIntent getIntent();

    /**
     * Get the UUID of the sender.
     *
     * @return UUID of the sender
     */
    @NotNull
    UUID getSender();

    /**
     * Get UUID of the receiver.
     *
     * @return UUID of the receiver
     */
    @NotNull
    UUID getReceiver();

    /**
     * Get sent date
     *
     * @return Date when the invite was sent
     */
    long getTime();

    /**
     * Accept this invite.
     * @param player The player that should get a response in chat about this acceptance
     * @return false, if the invite fails because of max members permission (lands.members.number).
     */
    @NotNull CompletableFuture<T> accept(@NotNull LandPlayer player);

    /**
     * Deny this invite.
     */
    void deny();

    /**
     * Get the land that sent the invite.
     *
     * @return Land that sent the invite
     */
    @NotNull
    Land getLand();
}
