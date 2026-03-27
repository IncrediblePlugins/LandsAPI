package me.angeschossen.lands.api.inbox;

import com.github.angeschossen.pluginframework.api.player.PlayerData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Needed to add custom inbox messages to lands or nation.
 */
public interface InboxMessageProvider {

    /**
     * This method is used to retrieve the raw inbox messages by their unique key
     *
     * @param key        unique key
     * @param landPlayer The player that is viewing the inbox message. Can be used for multi locale setups.
     *                   If null, the message is retrieved by system to check if its still valid and other purposes.
     * @return null, if the message no longer exists and should be deleted at the next server restart
     */
    @Nullable
    String getRawMessage(@NotNull String key, @Nullable PlayerData landPlayer);
}
