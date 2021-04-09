package models.transfer;

import models.accounts.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Move funds from one account to another.
 */
public class Transfer {
    private LocalDateTime date;
    private BigDecimal amount;
    private String description;
    private Account sourceAccount;
    private Account destinationAccount;

    public Transfer(BigDecimal amount,
                    String description,
                    Account sourceAccount,
                    Account destinationAccount) {
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.description = description;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }

    public void execute() {
        sourceAccount.deductFunds(amount);
        destinationAccount.addFunds(amount);
    }
}
