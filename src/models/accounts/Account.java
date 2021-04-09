package models.accounts;

import models.card.Card;
import models.client.Client;
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

    protected Client client;
    // {@code iban} refers to account number.
    protected final String iban;
    protected final LocalDateTime openDate;
    protected BigDecimal balance;
    protected final List<Card> cards = new ArrayList<>();

    public Account(Client client) {
        this.client = client;
        this.iban = RandomGenerator.getNumericString(AccountDetails.IBAN_SIZE.value);
        this.openDate = LocalDateTime.now();
        this.balance = BigDecimal.ZERO;
    }

    public abstract BigDecimal getDepositFee(BigDecimal amount);

    public abstract BigDecimal getWithdrawalFee(BigDecimal amount);

    public abstract BigDecimal getTransferFee(BigDecimal amount);

    public Client getClient() {
        return client;
    }

    public String getIban() {
        return iban;
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * Deposit
     */
    public abstract void addFunds(BigDecimal amount);

    /**
     * Withdrawal
     */
    public abstract void deductFunds(BigDecimal amount);

    public void createCard() {
        cards.add(new Card());
    }

    /**
     * Check if the account has a specific card associated.
     */
    public boolean hasCard(String cardNumber) {
        for (Card card : cards) {
            if (card.getNumber().equals(cardNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEnoughFundsForWithdrawal(BigDecimal amount) {
        // {@code amount} must be <= {@code this.balance}.
        return amount.compareTo(this.balance) <= 0;
    }

    @Override
    public String toString() {
        return "Account{" +
                "client=" + client +
                ", iban='" + iban + '\'' +
                ", openDate=" + openDate +
                ", balance=" + balance +
                ", cards=" + cards +
                '}';
    }
}
