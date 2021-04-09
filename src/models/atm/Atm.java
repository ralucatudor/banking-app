package models.atm;

import utils.Address;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Atm {
    private final Address address;
    private BigDecimal funds;

    public Atm(Address address, BigDecimal funds) {
        this.address = address;
        this.funds = funds;
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public boolean hasEnoughFundsForWithdrawal(BigDecimal amount) {
        // {@code amount} must be <= {@code this.funds}.
        return amount.compareTo(this.funds) <= 0;
    }

    public void addFunds(BigDecimal amount) {
        this.setFunds(this.getFunds().add(amount));
    }

    public void deductFunds(BigDecimal amount) throws Exception {
        if (!hasEnoughFundsForWithdrawal(amount)) {
            throw new Exception("There are not enough funds in the ATM for withdrawal!");
        }
        this.setFunds(this.getFunds().subtract(amount));
    }

    @Override
    public String toString() {
        return "Atm{" +
                "address=" + address +
                ", funds=" + funds +
                '}';
    }
}
