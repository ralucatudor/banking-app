package services;

import models.accounts.Account;
import models.atm.Atm;
import models.atm.AtmTransaction;
import models.atm.Deposit;
import models.atm.Withdrawal;
import utils.Address;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages ATM services.
 */
// TODO make this singleton?
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

    public void depositToAtm(String atmIdentifier,
                             BigDecimal amount) throws Exception {
        Atm atm = this.getAtm(atmIdentifier);
        atm.addFunds(amount);
    }

    public void withdrawFromAtm(String atmIdentifier,
                                BigDecimal amount) throws Exception {
        Atm atm = this.getAtm(atmIdentifier);
        atm.deductFunds(amount);
    }

    public void depositToAccount(String atmIdentifier,
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

    public void loadDataFromCsv(CsvReaderService reader) throws FileNotFoundException {
        List<List<String>> dbData = reader.read("data\\atms.csv");

        for (List<String> data : dbData) {
            Atm atm = new Atm(
                    new Address(data.get(0), data.get(1), data.get(2), data.get(3)),
                    new BigDecimal(data.get(4)),
                    data.get(5)
            );
            atms.add(atm);
        }
    }

    public void updateCsvData(CsvWriterService writer) throws IOException {
        writer.emptyFile("data\\atms.csv");
        for (Atm atm : atms) {
            List<String> data = new ArrayList<>();

            Address atmAddress = atm.getAddress();
            data.add(atmAddress.getStreetAddress());
            data.add(atmAddress.getCity());
            data.add(atmAddress.getCountry());
            data.add(atmAddress.getPostalCode());

            data.add(atm.getFunds().toString());

            data.add(atm.getIdentifier());

            writer.write("data\\atms.csv", data, true);
        }
    }
}
