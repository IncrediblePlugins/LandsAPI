package me.angeschossen.lands.api.war.enums;

/**
 * Identifies a team's role in a war.
 */
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

    /**
     * Get the opposing team.
     *
     * @return {@link #DEFENDER} if this is {@link #ATTACKER}, {@link #ATTACKER} if this is {@link #DEFENDER}, otherwise {@link #NEUTRAL}
     */
    public WarTeam getOpposite() {
        return this == ATTACKER ? DEFENDER : this == DEFENDER ? ATTACKER : NEUTRAL;
    }

    /**
     * Throw an exception if this team is {@link #NEUTRAL}.
     *
     * @throws IllegalArgumentException if this is {@link #NEUTRAL}
     */
    public void ensureIsAttackerOrDefender() {
        if (this != ATTACKER && this != DEFENDER) {
            throw new IllegalArgumentException("Must be an attacker or defender");
        }
    }
}
