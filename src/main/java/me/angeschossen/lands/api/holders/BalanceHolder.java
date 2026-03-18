package me.angeschossen.lands.api.holders;

import org.jetbrains.annotations.NotNull;

public interface BalanceHolder {

    /**
     * Get the balance.
     *
     * @return never negative
     */
    double getBalance();

    /**
     * Set the balance
     *
     * @param balance The balance will be set to 0 if the provided balance is negative
     * @return false, if the provided balance was negative.
     */
    boolean setBalance(double balance);

    /**
     * Set the balance
     *
     * @param balance The balance will be set to 0 if the provided balance is negative
     * @param notify  if false, doesn't publish balance change to other servers connected
     *                via Redis and doesn't trigger {@link me.angeschossen.lands.api.events.land.bank.LandBankBalanceChangedEvent},
     *                in case this is a {@link me.angeschossen.lands.api.land.Land}
     * @return false, if the provided balance was negative.
     */
    boolean setBalance(double balance, boolean notify);

    /**
     * Modify the balance by a specific amount.
     *
     * @param modify can be negative to substract that amount
     * @return The new balance will never go below 0
     */
    boolean modifyBalance(double modify);

    /**
     * Modify the balance by a specific amount.
     *
     * @param modify can be negative to substract that amount
     * @param notify if false, doesn't publish balance change to other servers connected
     *               via Redis and doesn't trigger {@link me.angeschossen.lands.api.events.land.bank.LandBankBalanceChangedEvent},
     *               in case this is a {@link me.angeschossen.lands.api.land.Land}
     * @return The new balance will never go below 0
     */
    boolean modifyBalance(double modify, boolean notify);

    /**
     * Get the balance in a formatted display.
     *
     * @return Formatted view of the balance. Depends on the server's config
     */
    @NotNull
    String getBalanceDisplay();

}
