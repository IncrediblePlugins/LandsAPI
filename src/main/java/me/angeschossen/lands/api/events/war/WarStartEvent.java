package me.angeschossen.lands.api.events.war;

import me.angeschossen.lands.api.war.War;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a war starts. This might be after the configured preparation time.
 * The preparation time is configured by the server in the configuration. The preparation time starts after
 * a declaration has been sent or has been accepted if the server has mututal declarations enabled.
 * @see WarDeclareEvent
 */
public class WarStartEvent extends WarEvent {
    /** Required by Bukkit's event system. */
    public static HandlerList handlerList = new HandlerList();

    /**
     * Constructor
     * @param war the affected war
     */
    public WarStartEvent(@NotNull War war) {
        super(war);
    }

    /**
     * Returns the handler list for this event type, as required by Bukkit.
     *
     * @return the static handler list
     */
    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }
}
