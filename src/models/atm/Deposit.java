package models.atm;

import models.accounts.Account;

import java.math.BigDecimal;

/**
 * Add funds to an account.
 */
public class Deposit extends AtmTransaction {
    public Deposit(BigDecimal amount, Account account, Atm atm) {
        super(amount, account, atm);
    }

    @Override
    public void execute() {
        account.addFunds(amount);
        atm.addFunds(amount);
    }
}
