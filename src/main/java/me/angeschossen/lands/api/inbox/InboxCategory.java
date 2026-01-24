package me.angeschossen.lands.api.inbox;

import com.github.angeschossen.pluginframework.api.handler.APIHandler;
import me.angeschossen.lands.api.player.LandPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Inbox categories let players decide which type of inbox messages they want to see in the menu.
 * This allows them to filter inbox messages by a topic.
 */
public enum InboxCategory {

    /**
     * Displays all inbox messages, no matter which category they're of.
     */
    ALL(0, 0),
    /**
     * Economy related messages, such as upkeep, bank and taxes.
     */
    ECONOMY(1, 1),
    /**
     * Member related messages, such as newly trusted players or untrusted players.
     */
    MEMBERS(2, 2),
    /**
     * War related messages, such as incoming or outgoing war declarations or other war related information.
     */
    WAR(3, 3),
    /**
     * Messages about new relations, such as new allies or declaration of enemies.
     */
    RELATIONS(4, 4),
    /**
     * Messages posted by land members to inform other land members.
     */
    POSTS(5, 5),
    /**
     * Logs information about removed items etc.
     */
    STORAGE(6, 6);

    private static final Map<Integer, InboxCategory> categories = new HashMap<>();

    static {
        for (InboxCategory i : values()) {
            categories.put(i.priority, i);
        }
    }

    public final int id;
    public final int priority;
    private boolean enabled = true;

    InboxCategory(int id, int prio) {
        this.id = id;
        this.priority = prio;
    }

    /**
     * Categories can be disabled.
     *
     * @return true, if the category is enabled
     */
    public final boolean isEnabled() {
        return enabled;
    }

    /**
     * Disable this category from being selectable in the menu.
     * Messages of this category will still display, if {@link #ALL} is seleted.
     *
     * @param enabled false, to disable this category
     * @throws IllegalStateException The {@link #ALL} category can't be disabled
     */
    public final void setEnabled(boolean enabled) throws IllegalStateException {
        if (this == ALL && !enabled) {
            throw new IllegalStateException("Can't disable the category ALL");
        }

        this.enabled = enabled;
    }

    /**
     * Get a category by its ID.
     *
     * @param id The ID
     * @return never null. Will return {@link #ALL} if there's no category with such ID
     */
    @NotNull
    public static InboxCategory getById(int id) {
        for (InboxCategory type : values()) {
            if (type.id == id) {
                return type;
            }
        }

        return InboxCategory.ALL;
    }

    /**
     * Get a category by it's priority
     *
     * @param prio The priority
     * @return never null. Will return {@link #ALL} if there's no category with this priority
     */
    @NotNull
    public static InboxCategory getByPriority(int prio) {
        return categories.getOrDefault(prio, ALL);
    }

    /**
     * Cycle through the categories.
     *
     * @return never null
     */
    @NotNull
    public final InboxCategory getNext() {
        InboxCategory inboxCategory = getByPriority(priority + 1);
        return inboxCategory.enabled ? inboxCategory : inboxCategory.getNext();
    }

    /**
     * Get the ID of the category.
     *
     * @return The categories ID
     */
    public final int getId() {
        return id;
    }

    /**
     * Use {@link #getName(LandPlayer)} instead.
     *
     * @return The name
     */
    @NotNull
    @Deprecated
    public final String getName() {
        return getName(null);
    }

    /**
     * Get the name of the category.
     * @param landPlayer returned value may depend on player's locale
     * @return the name
     */
    @NotNull
    public final String getName(@Nullable LandPlayer landPlayer) {
        return APIHandler.getInstance().getLocaleHandler().getGUILocale(landPlayer).getString(this == ALL ? "all" : "inbox-filter." + toString());
    }
}
