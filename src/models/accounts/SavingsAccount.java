package models.accounts;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class SavingsAccount extends Account {
    public SavingsAccount(UUID clientId) {
        super(clientId);
    }

    public SavingsAccount(UUID clientId, String iban, LocalDate openDate, BigDecimal balance) {
        super(clientId, iban, openDate, balance);
    }

    public SavingsAccount(UUID id, UUID clientId, String iban, LocalDate openDate, BigDecimal balance) {
        super(id, clientId, iban, openDate, balance);
    }

    @Override
    public BigDecimal getDepositFee(BigDecimal amount) {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getWithdrawalFee(BigDecimal amount) {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getTransferFee(BigDecimal amount) {
        return BigDecimal.ZERO;
    }

    @Override
    public void addFunds(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    @Override
    public void deductFunds(BigDecimal amount) throws Exception {
        throw new Exception("Unable to withdraw money from savings account!");
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "clientId=" + clientId +
                ", iban='" + iban + '\'' +
                ", openDate=" + openDate +
                ", balance=" + balance +
                ", cards=" + cards +
                '}';
    }
}
