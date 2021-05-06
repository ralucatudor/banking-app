package models.accounts;

import java.math.BigDecimal;
import java.util.UUID;

public class SavingsAccount extends Account {
    public SavingsAccount(UUID clientId) {
        super(clientId);
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
