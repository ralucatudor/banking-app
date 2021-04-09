package models.atm;

import models.accounts.Account;

import java.math.BigDecimal;

/**
 * Deduct funds from an account.
 */
public class Withdrawal extends AtmTransaction {

    public Withdrawal(BigDecimal amount, String description, Account account, Atm atm) {
        super(amount, description, account, atm);
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
