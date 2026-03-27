package me.angeschossen.lands.api.inbox;


import com.github.angeschossen.pluginframework.api.events.ExpressionEntity;
import com.github.angeschossen.pluginframework.api.player.PlayerData;
import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Inbox messages notify the land about events, such as newly trusted players, paid upkeep etc.
 */
public interface InboxMessage extends ExpressionEntity {

    /**
     * Add an inbox message to a land or nation.
     * In order to create a inbox message, you must set a inbox message provider.
     * The message provider needs to be registered, before Lands loads data. Therefore, you need to do that in your {@link Plugin#onLoad()} method of your plugins main class.
     * You can use {@link LandsIntegration#onLoad(Runnable)} in combination with {@link LandsIntegration#setInboxMessageProvider(InboxMessageProvider)} to set the message provider.
     * {@link LandsIntegration#onLoad(Runnable)} ensures that your code is called once Lands is loaded and before Lands loads data.
     *
     * @param landsIntegration  your integration
     * @param memberHolder      land or nation
     * @param category          category of the message
     * @param key               this is not the message. this is just an unique key that is later used to retrieve the raw message before parsing
     * @param placeholders      placeholders for this message
     * @param placeholderValues placeholder values for this message
     * @param isAlert           should land members be alerted, in Discord, if DiscordBridge is installed?
     * @param broadcast         broadcast to land members ingame?
     * @param filterReceive     don't broadcast to this specific land member
     * @return the added inbox message
     * @throws IllegalArgumentException if the provided parameters are invalid
     */
    static InboxMessage of(@NotNull LandsIntegration landsIntegration, @NotNull MemberHolder memberHolder, @NotNull InboxCategory category, @NotNull String key, @Nullable String[] placeholders, String[] placeholderValues, boolean isAlert, boolean broadcast, @Nullable LandPlayer filterReceive) throws IllegalArgumentException {
        return APIHandler.getLandsIntegrationFactory().inboxMessageOf(landsIntegration, memberHolder, category, key, placeholders, placeholderValues, isAlert, broadcast, filterReceive);
    }

    /**
     * Get the category of this message.
     *
     * @return The category this message belongs to
     */
    @NotNull
    InboxCategory getCategory();

    /**
     * Get the message content {@link #getText(PlayerData)} with the messages date when it was received ({@link #getDateString(PlayerData)}).
     *
     * @param sender Used for per user language. If no player provided, uses default language.
     * @return Format: date ({@link #getDateString(PlayerData)} ()}): content ({@link #getText(PlayerData)})
     */
    @NotNull
    String getTextWithDate(@Nullable PlayerData sender);

    /**
     * Get the date in text format.
     *
     * @param player will take the players locale
     * @return formatted date
     */
    @NotNull
    String getDateString(@Nullable PlayerData player);

    /**
     * Timestamp in milliseconds when the message was received.
     *
     * @return Milliseconds
     */
    long getTime();

    /**
     * Get the message text without the date.
     *
     * @return Content of the message without the date
     * @deprecated Use {{@link #getText(PlayerData)}} instead.
     */
    @Deprecated
    @NotNull
    String getText();

    /**
     * Get the message text without the date.
     *
     * @param player depends on players locale
     * @return Content of the message without the date
     */
    @NotNull
    String getText(@Nullable PlayerData player);

    /**
     * Use {@link #getTextWithDate(PlayerData)} instead.
     *
     * @return Format: time ({@link #getTime()}): content ({@link #getText()})
     */
    @Deprecated
    @NotNull
    String getTextWithDate();

    /**
     * Check if this message is an alert. Some messages are really important, such as the reminder to deposit money for upkeep, if a land
     * is lacking funds.
     *
     * @return true, if this message is an alert
     */
    boolean shouldAlertMembers();

    /**
     * Check if this message belongs to a specific inbox category.
     *
     * @param type The inbox category
     * @return true, if the inbox category is the same or the provided inbox category equals {@link InboxCategory#ALL}
     */
    boolean appliesToFilter(@NotNull InboxCategory type);
}
