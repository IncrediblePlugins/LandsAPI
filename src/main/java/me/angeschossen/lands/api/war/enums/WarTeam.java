package me.angeschossen.lands.api.war.enums;

public enum WarTeam {

    /**
     * The initial attacker that sent the war declaration.
     */
    ATTACKER,
    /**
     * The defender that received the war declaration.
     */
    DEFENDER,
    /**
     * Not engaged in this war.
     */
    NEUTRAL;

    public WarTeam getOpposite() {
        return this == ATTACKER ? DEFENDER : this == DEFENDER ? ATTACKER : NEUTRAL;
    }

    public void ensureIsAttackerOrDefender() {
        if (this != ATTACKER && this != DEFENDER) {
            throw new IllegalArgumentException("Must be an attacker or defender");
        }
    }
}
