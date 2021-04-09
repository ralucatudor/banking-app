package services;

import models.accounts.Account;
import models.atm.Atm;
import models.atm.AtmTransaction;
import models.atm.Deposit;
import models.atm.Withdrawal;
import utils.Address;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AtmService {
    private final List<Atm> atms = new ArrayList<>();
    private final List<AtmTransaction> atmTransactions = new ArrayList<>();
    private final AccountService accountService;

    public AtmService() {
        this.accountService = AccountService.getInstance();
    }

    public void createAtm(Address address, BigDecimal initialFunds, String identifier) {
        Atm atm = new Atm(address, initialFunds, identifier);
        atms.add(atm);
    }

    /**
     * Display atms.
     */
    public void showAtms() {
        System.out.println("ATMs:");
        for (Atm atm : atms) {
            System.out.println(atm);
        }
    }

    public Atm getAtm(String identifier) throws Exception {
        for (Atm atm : atms) {
            if (atm.getIdentifier().equals(identifier)) {
                return atm;
            }
        }
        throw new Exception("ATM identifier " + identifier + " is invalid.");
    }

    public void depositInAtm(String atmIdentifier,
                             BigDecimal amount) throws Exception {
        Atm atm = this.getAtm(atmIdentifier);
        atm.addFunds(amount);
    }

    public void withdrawFromAtm(String atmIdentifier,
                                BigDecimal amount) throws Exception {
        Atm atm = this.getAtm(atmIdentifier);
        atm.deductFunds(amount);
    }

    public void depositInAccount(String atmIdentifier,
                                 String cardNumber,
                                 BigDecimal amount) throws Exception {
        Atm atm = this.getAtm(atmIdentifier);
        Account account = this.accountService.getAccountByCardNumber(cardNumber);

        AtmTransaction atmTransaction = new Deposit(amount, account, atm);
        atmTransaction.execute();
        atmTransactions.add(atmTransaction);
    }

    public void withdrawFromAccount(String atmIdentifier,
                                    String cardNumber,
                                    BigDecimal amount) throws Exception {
        Atm atm = this.getAtm(atmIdentifier);
        Account account = this.accountService.getAccountByCardNumber(cardNumber);

        AtmTransaction atmTransaction = new Withdrawal(amount, account, atm);
        atmTransaction.execute();
        atmTransactions.add(atmTransaction);
    }

    public List<AtmTransaction> getAtmTransactions() {
        return atmTransactions;
    }
}
