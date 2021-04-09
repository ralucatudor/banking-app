package models.atm;

import models.accounts.Account;

import java.math.BigDecimal;

/**
 * Deduct funds from an account.
 */
public class Withdrawal extends AtmTransaction {

    public Withdrawal(BigDecimal amount, Account account, Atm atm) {
        super(amount, account, atm);
    }

    @Override
    public void execute() {
        try {
            atm.deductFunds(amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        account.deductFunds(amount);
    }
}
