package me.angeschossen.lands.api.events;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.player.PlayerEvent;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * Called whenever a player sends a message in land or nation chat.
 */
public class LandChatEvent extends PlayerEvent implements Cancellable {
    /** Handler list for this event. */
    public static HandlerList handlerList = new HandlerList();
    private final @NotNull String message;
    private final @NotNull Collection<LandPlayer> recipients;
    private final @NotNull MessageSource messageSource;
    private final @NotNull MemberHolder memberHolder;
    private boolean cancelled;

    /**
     * Create an instance of this event.
     *
     * @param memberHolder  land or nation
     * @param sender        the sender
     * @param recipients    the recipients
     * @param message       message content
     * @param messageSource where they message was sent
     */
    public LandChatEvent(@NotNull MemberHolder memberHolder, @NotNull UUID sender, @NotNull Collection<LandPlayer> recipients, @NotNull String message, @NotNull MessageSource messageSource) {
        super(sender);

        Checks.requireNonNull(memberHolder, "memberHolder");
        Checks.requireNonNull(message, "message");
        Checks.requireNonNull(recipients, "recipients");
        Checks.requireNonNull(messageSource, "messageSource");
        this.memberHolder = memberHolder;
        this.message = message;
        this.recipients = recipients;
        this.messageSource = messageSource;
    }

    /**
     * Returns the handler list for this event type.
     *
     * @return the handler list; never null
     */
    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Get the land or nation in which this message was sent.
     *
     * @return land or nation
     */
    @NotNull
    public MemberHolder getMemberHolder() {
        return memberHolder;
    }

    /**
     * Get the message content.
     *
     * @return message including color codes
     */
    @NotNull
    public String getMessage() {
        return message;
    }

    /**
     * Get the recipients of this message.
     *
     * @return usually all online players of the land or nation
     */
    @NotNull
    public Collection<LandPlayer> getReceivers() {
        return recipients;
    }

    /**
     * Get the context in which the message was sent.
     *
     * @return source of message
     */
    @NotNull
    public MessageSource getSource() {
        return messageSource;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public String toString() {
        return "LandChatEvent{" +
                "sender=" + getPlayerUID() + "," +
                "memberHolder=" + memberHolder.getName() + "}";
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        super.setAffectedPlayers(builder);
        builder.put("receivers", getReceivers().stream().map(LandPlayer::getUID).collect(Collectors.toList()));
        memberHolder.setAffectedPlayers(memberHolder.getExpressionPrefix(), builder);
    }

    /**
     * Defines the origin of the message.
     */
    public enum MessageSource {
        /**
         * Message was sent ingame.
         */
        MINECRAFT,
        /**
         * Message was sent in a Discord server.
         */
        DISCORD,
    }
}
