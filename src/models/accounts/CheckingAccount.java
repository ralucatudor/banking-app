package models.accounts;

import java.math.BigDecimal;

public class CheckingAccount extends Account {
    @Override
    public BigDecimal getDepositFee(BigDecimal amount) {
        return null;
    }

    @Override
    public BigDecimal getWithdrawalFee(BigDecimal amount) {
        return null;
    }

    @Override
    public BigDecimal getTransferFee(BigDecimal amount) {
        return null;
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
