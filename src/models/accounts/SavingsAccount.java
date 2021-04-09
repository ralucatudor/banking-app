package models.accounts;

import models.client.Client;

import java.math.BigDecimal;

public class SavingsAccount extends Account {
    public SavingsAccount(Client client) {
        super(client);
    }

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
