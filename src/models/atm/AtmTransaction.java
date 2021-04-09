package models.atm;

import models.accounts.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents the transaction that a customer can make at an ATM - managing the funds
 * from his own account.
 * Skeleton for Deposit and Withdrawal.
 */
public abstract class AtmTransaction {
    protected LocalDateTime date;
    protected BigDecimal amount;
    protected Account account;
    protected Atm atm;

    public AtmTransaction(BigDecimal amount, Account account, Atm atm) {
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.account = account;
        this.atm = atm;
    }

    public abstract void execute() throws Exception;
}
