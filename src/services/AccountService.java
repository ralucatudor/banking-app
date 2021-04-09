package services;

import models.accounts.Account;
import models.accounts.AccountFactory;
import models.client.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class.
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

    public void openCheckingAccount(Client client) {
        Account account = AccountFactory.getAccount("checking", client);
        account.createCard();
        accounts.add(account);
    }

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
            if (account.getClient().equals(client)) {
                System.out.println(account);
            }
        }
    }

    public void showCards() {

    }

    // TODO add another card to an account?
}
