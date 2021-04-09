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

    public abstract BigDecimal getDepositFee(BigDecimal amount);

    public abstract BigDecimal getWithdrawalFee(BigDecimal amount);

    public abstract BigDecimal getTransferFee(BigDecimal amount);

    /**
     * Deposit
     */
    public abstract void addFunds(BigDecimal amount);

    /**
     * Withdrawal
     */
    public abstract void deductFunds(BigDecimal amount);
}
