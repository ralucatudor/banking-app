package models.accounts;

import models.client.Client;

import java.math.BigDecimal;

public class SavingsAccount extends Account {
    public SavingsAccount(Client client) {
        super(client);
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
}
