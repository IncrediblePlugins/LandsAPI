package me.angeschossen.lands.api.events.memberholder;

import me.angeschossen.lands.api.inbox.InboxMessage;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


/**
 * Called whenever a land or nation receives an inbox message.
 */
public class InboxMessageReceivedEvent extends MemberHolderEvent {
    /** Handler list for this event. */
    public static final HandlerList handlerList = new HandlerList();

    private final @NotNull InboxMessage inboxMessage;

    /**
     * Create instance of this event.
     *
     * @param memberHolder land or nation
     * @param inboxMessage received inbox message
     */
    public InboxMessageReceivedEvent(@NotNull MemberHolder memberHolder, @NotNull InboxMessage inboxMessage) {
        super(memberHolder);

        Objects.requireNonNull(inboxMessage, "message can't be null");
        this.inboxMessage = inboxMessage;
    }

    /**
     * Returns the handler list for this event type.
     *
     * @return the handler list; never null
     */
    public static HandlerList getHandlerList() {
        return handlerList;
    }

    /**
     * Get the received message.
     *
     * @return received message
     */
    @NotNull
    public InboxMessage getMessage() {
        return inboxMessage;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}

