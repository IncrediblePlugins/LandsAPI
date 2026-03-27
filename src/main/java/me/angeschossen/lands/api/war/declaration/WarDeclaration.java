package me.angeschossen.lands.api.war.declaration;

import me.angeschossen.lands.api.war.War;
import me.angeschossen.lands.api.war.WarState;
import me.angeschossen.lands.api.war.enums.WarTeam;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Represents a pending war declaration that has not yet started.
 */
public interface WarDeclaration extends WarState {

    /**
     * Set tribute, which the surrenderer must pay to the enemy if they surrender.
     *
     * @param tribute Depending on the server's economy this can be cash, items etc.
     */
    void setTribute(double tribute);

    /**
     * Set tribute, which the surrenderer must pay to the enemy if they surrender.
     *
     * @return Can't be negative
     */
    double getTribute();

    /**
     * Depending on the server's config there can be a maximum tribute.
     *
     * @param warTeam The team
     * @return Can't be negative
     */
    double getMaxTribute(@NotNull WarTeam warTeam);

    /**
     * Check if the war preparation time ends soon.
     * Depends on the server's config.
     *
     * @return true, if the war starts in a matter of seconds
     */
    boolean startsSoon();

    /**
     * Forcefully start this war.
     *
     * @return The war
     */
    CompletableFuture<War> start();
}
