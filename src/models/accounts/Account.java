package models.accounts;

import models.card.Card;
import utils.RandomGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    // {@code clientId} refers to the account holder.
    protected UUID clientId;
    // {@code iban} refers to account number.
    protected String iban;
    protected LocalDate openDate;
    protected BigDecimal balance;
    // Note: when an account is opened, a card is linked to it by default.
    protected List<Card> cards = new ArrayList<>();

    public Account(UUID clientId) {
        this.clientId = clientId;
        this.iban = RandomGenerator.getNumericString(AccountDetails.IBAN_SIZE.value);
        this.openDate = LocalDate.now();
        this.balance = new BigDecimal(AccountDetails.INITIAL_BALANCE.value);
    }

    public Account(UUID clientId, String iban, LocalDate openDate, BigDecimal balance) {
        this.clientId = clientId;
        this.iban = iban;
        this.openDate = openDate;
        this.balance = balance;
        createCard();
    }

    public UUID getClientId() {
        return clientId;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public BigDecimal getBalance() {
        return balance;
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
                "clientId=" + clientId +
                ", iban='" + iban + '\'' +
                ", openDate=" + openDate +
                ", balance=" + balance +
                ", cards=" + cards +
                '}';
    }
}
