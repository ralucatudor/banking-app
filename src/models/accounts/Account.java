package models.accounts;

import models.card.Card;
import utils.RandomGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    enum AccountDetails {
        IBAN_SIZE(16);

        public final int value;
        AccountDetails(int value) {
            this.value = value;
        }
    }

    // {@code iban} refers to account number.
    protected final String iban;
    protected final LocalDateTime openDate;
    protected BigDecimal balance;
    protected final List<Card> cards = new ArrayList<>();

    public Account() {
        this.iban = RandomGenerator.getNumericString(AccountDetails.IBAN_SIZE.value);
        this.openDate = LocalDateTime.now();
        this.balance = BigDecimal.ZERO;
    }

    /**
     * Deposit
     */
    public void addFunds(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    /**
     * Withdrawal
     */
    public void deductFunds(BigDecimal amount) {
        assert amount.signum() > 0;
        // TODO: Check if current balance is at least equal to {@code amount}.
        this.balance = this.balance.subtract(amount);
    }
}
