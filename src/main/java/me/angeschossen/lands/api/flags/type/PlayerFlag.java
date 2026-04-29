package me.angeschossen.lands.api.flags.type;

import me.angeschossen.lands.api.flags.type.parent.DefaultStateFlag;

/**
 * Represents a flag that controls a player's personal settings (e.g. receiving invites).
 * Player flags are distinct from role flags: they belong to an individual player rather than a land role.
 * All state and display methods are inherited from {@link DefaultStateFlag}.
 */
public interface PlayerFlag extends DefaultStateFlag<PlayerFlag> {
}
