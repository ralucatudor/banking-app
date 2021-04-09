package models.accounts;

import models.card.Card;
import models.client.Client;
import utils.RandomGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds information about a bank account, such as the account holder,
 * account number, account open date, account balance, and cards associated
 * with the account.
 */
public abstract class Account {
    // Use enum for holding constants.
    enum AccountDetails {
        IBAN_SIZE(16), INITIAL_BALANCE(0);

        public final int value;
        AccountDetails(int value) {
            this.value = value;
        }
    }

    // {@code client} refers to the account holder.
    protected Client client;
    // {@code iban} refers to account number.
    protected final String iban;
    protected final LocalDateTime openDate;
    protected BigDecimal balance;
    // Note: when an account is opened, a card is linked to it by default.
    protected final List<Card> cards = new ArrayList<>();

    public Account(Client client) {
        this.client = client;
        this.iban = RandomGenerator.getNumericString(AccountDetails.IBAN_SIZE.value);
        this.openDate = LocalDateTime.now();
        this.balance = new BigDecimal(AccountDetails.INITIAL_BALANCE.value);
    }

    public Client getClient() {
        return client;
    }

    public String getIban() {
        return iban;
    }

    public List<Card> getCards() {
        return cards;
    }

    public abstract BigDecimal getDepositFee(BigDecimal amount);

    public abstract BigDecimal getWithdrawalFee(BigDecimal amount);

    public abstract BigDecimal getTransferFee(BigDecimal amount);

    /**
     * Simulates making a deposit to the account, updating {@code balance}.
     */
    public abstract void addFunds(BigDecimal amount);

    /**
     * Simulates making a withdrawal from the account, updating {@code balance}.
     */
    public abstract void deductFunds(BigDecimal amount) throws Exception;

    /**
     * Appends a new card to the list of cards linked to the account.
     */
    public void createCard() {
        cards.add(new Card());
    }

    /**
     * Checks if the account has a specific card associated.
     */
    public boolean hasCard(String cardNumber) {
        for (Card card : cards) {
            if (card.getNumber().equals(cardNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the account has a balance that would allow a withdrawal of
     * a specified amount.
     */
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
