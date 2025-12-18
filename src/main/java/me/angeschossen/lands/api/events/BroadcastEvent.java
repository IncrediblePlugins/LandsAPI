package me.angeschossen.lands.api.events;

import com.github.angeschossen.pluginframework.api.locale.Environment;
import com.github.angeschossen.pluginframework.api.player.PlayerData;
import com.github.angeschossen.pluginframework.api.player.PlayerDataBase;
import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.google.common.collect.ImmutableMap;
import me.angeschossen.lands.api.events.plugin.LandsEvent;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Called when a message is sent to all players on the server.
 * This might exlude a few specific players, to prevent them getting two messages about the same topic.
 */
public class BroadcastEvent extends LandsEvent {
    public static HandlerList handlerList = new HandlerList();

    private final @Nullable String messageKey;
    private final @NotNull Category category;
    private final @NotNull Collection<? extends PlayerDataBase> recipients;
    private final @NotNull Function<@NotNull MessageParseRequest, String> parseMessage;

    /**
     * Create an instance of this event.
     *
     * @param recipients   all recipients
     * @param messageKey   Message key in the Lands language file. Use null if the message isn't from Lands
     * @param parseMessage parses the message for a specific player. Lands supports per player language
     */
    public BroadcastEvent(@NotNull Collection<? extends PlayerDataBase> recipients, @NotNull Category category, @Nullable String messageKey, @NotNull Function<@NotNull MessageParseRequest, String> parseMessage) {
        this.messageKey = messageKey;
        this.category = category;
        this.recipients = recipients;
        this.parseMessage = parseMessage;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Get the message content.
     *
     * @return message content
     */
    @Deprecated
    @NotNull
    public String getMessage() {
        return "deprecated";
    }

    public @NotNull Category getCategory() {
        return category;
    }

    public enum Category {
        /**
         * Sent when a land was deleted.
         */
        LAND_CREATED,
        /**
         * A land was created.
         */
        LAND_DELETED,
        /**
         * A relation between lands and/or nations changed.
         */
        RELATION_CHANGED,
        /**
         * Events such as war start and end.
         */
        WAR_STATE_CHANGED,
        /**
         * Global reminder that upkeep will be collected soon.
         */
        UPKEEP_REMINDER,
        /**
         * Upkeep was collected.
         */
        UPKEEP_COLLECTED
    }

    public static final class MessageParseRequest {
        private final @NotNull Environment environment;
        private final @Nullable PlayerData recipient;

        public MessageParseRequest(@NotNull Environment environment, @Nullable PlayerData recipient) {
            this.environment = Checks.requireNonNull(environment, "environment");
            this.recipient = recipient;
        }

        public @NotNull Environment getEnvironment() {
            return environment;
        }

        public @Nullable PlayerData getRecipient() {
            return recipient;
        }
    }

    /**
     * Parse the message.
     *
     * @param messageParseRequest parameters
     * @return never null
     */
    @NotNull
    public String parseMessage(@NotNull MessageParseRequest messageParseRequest) {
        return parseMessage.apply(Checks.requireNonNull(messageParseRequest, "messageParseRequest"));
    }

    /**
     * Get the message key.
     *
     * @return null, if message isn't from Lands and a 3rd party plugin called this event
     */
    @Nullable
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * Get a collection of recipients.
     *
     * @return all recipients
     */
    @NotNull
    public Collection<? extends PlayerDataBase> getRecipients() {
        return recipients;
    }

    @Override
    public void setAffectedPlayers(ImmutableMap.@NotNull Builder<String, Collection<UUID>> builder) {
        builder.put("recipients", recipients.stream().map(PlayerDataBase::getUUID).collect(Collectors.toList()));
    }

    @Override
    public void setExpressionVariables(ImmutableMap.@NotNull Builder<String, Object> builder) {

    }
}

