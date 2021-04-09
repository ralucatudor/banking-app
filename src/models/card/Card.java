package models.card;

import utils.RandomGenerator;

import java.time.LocalDate;

/**
 * Holds information about cards - which are linked to each account.
 * Note: when an account is opened, a card is linked to it by default.
 */
public class Card {
    // Use enum for holding constants.
    enum CardDetails {
        YEARS_OF_VALIDITY(3),
        NUMBER_NO_OF_DIGITS(16),
        CVV_NO_OF_DIGITS(3),
        PIN_NO_OF_DIGITS(4);

        public final int value;
        CardDetails(int value) {
            this.value = value;
        }
    }

    private final LocalDate issueDate;
    private final LocalDate expirationDate;
    // Use String type for {@code number} and {@code CVV}, as these might
    // start with zeros.
    private final String number;
    private final String cvv;
    private final String pin;
    private boolean isBlocked;

    public Card() {
        this.issueDate = LocalDate.now();
        this.expirationDate = LocalDate.now().plusYears(
                CardDetails.YEARS_OF_VALIDITY.value);
        this.number = RandomGenerator.getNumericString(
                CardDetails.NUMBER_NO_OF_DIGITS.value);
        this.cvv = RandomGenerator.getNumericString(
                CardDetails.CVV_NO_OF_DIGITS.value);
        this.pin = RandomGenerator.getNumericString(
                CardDetails.PIN_NO_OF_DIGITS.value);
        this.isBlocked = false;
    }

    public void block() {
        this.isBlocked = true;
    }

    public void unblock() {
        this.isBlocked = false;
    }

    public boolean isExpired() {
        return LocalDate.now().isBefore(this.expirationDate);
    }

    public boolean isValid() {
        return !this.isBlocked && !isExpired();
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Card{" +
                "issueDate=" + issueDate +
                ", expirationDate=" + expirationDate +
                ", number='" + number + '\'' +
                ", cvv='" + cvv + '\'' +
                ", pin='" + pin + '\'' +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
