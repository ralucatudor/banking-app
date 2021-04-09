package models.accounts;

import java.math.BigDecimal;

public class SavingsAccount extends Account {
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

    @Override
    public void addFunds(BigDecimal amount) {

    }

    @Override
    public void deductFunds(BigDecimal amount) {

    }
}
