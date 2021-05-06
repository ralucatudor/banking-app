package services;

import models.accounts.Account;
import models.accounts.AccountFactory;
import models.accounts.CheckingAccount;
import models.accounts.SavingsAccount;
import models.card.Card;
import models.client.Client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Singleton class for managing banking accounts.
 */
public class AccountService {
    private static AccountService instance = null;
    private final List<Account> accounts = new ArrayList<>();

    private AccountService() {}

    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    /**
     * Opens a checking account for a specified client and implicitly link a
     * card to that account.
     *
     * Called from the ClientService method for opening a Checking Account.
     */
    public void openCheckingAccount(Client client) {
        Account account = AccountFactory.getAccount("checking", client);
        account.createCard();
        accounts.add(account);
    }

    /**
     * Opens a savings account for a specified client and implicitly link a
     * card to that account.
     *
     * Called from the ClientService method for opening a Savings Account.
     */
    public void openSavingsAccount(Client client) {
        Account account = AccountFactory.getAccount("savings", client);
        account.createCard();
        accounts.add(account);
    }

    public Account getAccountByCardNumber(String cardNumber) throws Exception {
        for (Account account : accounts) {
            if (account.hasCard(cardNumber)) {
                return account;
            }
        }

        throw new Exception("Account associated with card number" + cardNumber + " not found.");
    }

    public void showClientAccounts(Client client) {
        for (Account account : accounts) {
            if (account.getClientId().equals(client.getId())) {
                System.out.println(account);
            }
        }
    }

    public Account getAccountByIban(String iban) throws Exception {
        for (Account account : accounts) {
            if (account.getIban().equals(iban)) {
                return account;
            }
        }

        throw new Exception("Account having IBAN = " + iban + " not found.");
    }

    /**
     * Displays all cards linked to an account.
     */
    public void showLinkedCards(String iban) throws Exception {
        Account account = getAccountByIban(iban);
        for (Card card : account.getCards()) {
            System.out.println(card);
        }
    }

    /**
     * Adds/ links another card to an account.
     */
    public void addCard(String iban) throws Exception {
        Account account = getAccountByIban(iban);
        account.createCard();
    }

    public void loadDataFromCsv(CsvReaderService reader) throws FileNotFoundException {
        List<List<String>> dbData = reader.read("data\\accounts.csv");

        for (List<String> data : dbData) {
            Account account;
            if (data.get(0).equals("checking")) {
                account = new CheckingAccount(
                        UUID.fromString(data.get(1)),
                        data.get(2),
                        LocalDate.parse(data.get(3)),
                        new BigDecimal(data.get(4))
                );
            } else {
                account = new SavingsAccount(
                        UUID.fromString(data.get(1)),
                        data.get(2),
                        LocalDate.parse(data.get(3)),
                        new BigDecimal(data.get(4))
                );
            }

            accounts.add(account);
        }
    }

    public void updateCsvData(CsvWriterService writer) throws IOException {
        writer.emptyFile("data\\accounts.csv");
        for (Account account : accounts) {
            List<String> data = new ArrayList<>();

            if (account instanceof CheckingAccount) {
                data.add("checking");
            } else {
                data.add("savings");
            }

            data.add(account.getClientId().toString());
            data.add(account.getIban());
            data.add(account.getOpenDate().toString());
            data.add(account.getBalance().toString());

            writer.write("data\\accounts.csv", data, true);
        }
    }
}
