package models.accounts;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CheckingAccount extends Account {
    private static final BigDecimal DEPOSIT_FEE = BigDecimal.ONE;
    private static final BigDecimal WITHDRAWAL_FEE_PERCENT = new BigDecimal("0.03");
    private static final BigDecimal TRANSFER_FEE = BigDecimal.ZERO;

    public CheckingAccount(UUID clientId) {
        super(clientId);
    }

    public CheckingAccount(UUID clientId, String iban, LocalDate openDate, BigDecimal balance) {
        super(clientId, iban, openDate, balance);
    }

    @Override
    public BigDecimal getDepositFee(BigDecimal amount) {
        return DEPOSIT_FEE;
    }

    @Override
    public BigDecimal getWithdrawalFee(BigDecimal amount) {
        return (amount.multiply(WITHDRAWAL_FEE_PERCENT));
    }

    @Override
    public BigDecimal getTransferFee(BigDecimal amount) {
        return TRANSFER_FEE;
    }

    public void addFunds(BigDecimal amount) {
        this.balance = this.balance.add(amount).add(getDepositFee(amount));
    }

    public void deductFunds(BigDecimal amount) throws Exception {
        assert amount.signum() > 0;
        // Check if current balance is at least equal to {@code amount}.
        BigDecimal toDeduct = amount.add(getWithdrawalFee(amount));
        if (this.balance.compareTo(toDeduct) < 0) {
            throw new Exception("Not enough funds for withdrawal.");
        }
        this.balance = this.balance.subtract(toDeduct);
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "clientId=" + clientId +
                ", iban='" + iban + '\'' +
                ", openDate=" + openDate +
                ", balance=" + balance +
                ", cards=" + cards +
                '}';
    }
}
