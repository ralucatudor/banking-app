package models.atm;

import models.accounts.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public abstract void execute();
}
