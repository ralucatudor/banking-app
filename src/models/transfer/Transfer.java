package models.transfer;

import models.accounts.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Move funds from one account to another.
 */
public class Transfer implements Comparable<Transfer> {
    private final LocalDateTime date;
    private final BigDecimal amount;
    private final String description;
    private final Account sourceAccount;
    private final Account destinationAccount;

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

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void execute() {
        sourceAccount.deductFunds(amount.add(sourceAccount.getTransferFee(amount)));
        destinationAccount.addFunds(amount);
    }

    @Override
    public int compareTo(Transfer o) {
        return date.compareTo(o.date);
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "date=" + date +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", sourceAccount=" + sourceAccount +
                ", destinationAccount=" + destinationAccount +
                '}';
    }
}
