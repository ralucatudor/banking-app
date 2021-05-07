package models.transfer;

import models.accounts.Account;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a transaction that holds place between two accounts -
 * when money is sent from one bank account to another.
 */
public class Transfer {
    private final LocalDate date;
    private final BigDecimal amount;
    private final String description;
    private final String sourceIban;
    private final String destinationIban;

    public Transfer(BigDecimal amount,
                    String description,
                    Account sourceAccount,
                    Account destinationAccount) throws Exception {
        this.date = LocalDate.now();
        this.amount = amount;
        this.description = description;
        this.sourceIban = sourceAccount.getIban();
        this.destinationIban = destinationAccount.getIban();

        // Execute transaction.
        // Move funds from one account to another.
        sourceAccount.deductFunds(amount.add(sourceAccount.getTransferFee(amount)));
        destinationAccount.addFunds(amount);
    }

    public Transfer(LocalDate date, BigDecimal amount, String description, String sourceIban, String destinationIban) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.sourceIban = sourceIban;
        this.destinationIban = destinationIban;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getSourceIban() {
        return sourceIban;
    }

    public String getDestinationIban() {
        return destinationIban;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "date=" + date +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", sourceIban='" + sourceIban + '\'' +
                ", destinationIban='" + destinationIban + '\'' +
                '}';
    }
}
