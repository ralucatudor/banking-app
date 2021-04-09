package services;

import models.accounts.Account;
import models.accounts.AccountFactory;
import models.card.Card;
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

    public Account getAccountByIban(String iban) throws Exception {
        for (Account account : accounts) {
            if (account.getIban().equals(iban)) {
                return account;
            }
        }

        throw new Exception("Account having IBAN = " + iban + " not found.");
    }

    /**
     * Displays cards linked to an account.
     */
    public void showLinkedCards(String iban) throws Exception {
        Account account = getAccountByIban(iban);
        for (Card card : account.getCards()) {
            System.out.println(card);
        }
    }

    /**
     * Adds another card to an account.
     */
    public void addCard(String iban) throws Exception {
        Account account = getAccountByIban(iban);
        account.createCard();
    }

}
