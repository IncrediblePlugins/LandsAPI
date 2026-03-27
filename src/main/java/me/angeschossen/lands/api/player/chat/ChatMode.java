package me.angeschossen.lands.api.player.chat;

/**
 * Represents the active chat mode for a player, routing their chat messages
 * to a land or nation channel.
 */
public enum ChatMode {
    /**
     * Equals /lands chat toggle, to permanently forward messages to the land chat.
     */
    LAND,
    /**
     * Equals /nations chat toggle, to permanently forward messages to the nation chat.
     */
    NATION
}
