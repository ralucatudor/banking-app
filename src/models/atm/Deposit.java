package models.atm;

import models.accounts.Account;

import java.math.BigDecimal;

public class Deposit extends AtmTransaction {
    public Deposit(BigDecimal amount, Account account, Atm atm) {
        super(amount, account, atm);
    }

    /**
     * Adds funds to account.
     */
    @Override
    public void execute() {
        account.addFunds(amount);
        atm.addFunds(amount);
    }
}
