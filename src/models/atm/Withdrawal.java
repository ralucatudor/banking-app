package models.atm;

import models.accounts.Account;

import java.math.BigDecimal;

public class Withdrawal extends AtmTransaction {
    public Withdrawal(BigDecimal amount, Account account, Atm atm) {
        super(amount, account, atm);
    }

    /**
     * Deducts funds from account.
     */
    @Override
    public void execute() throws Exception {
        atm.deductFunds(amount);
        account.deductFunds(amount);
    }
}
