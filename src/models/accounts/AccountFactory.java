package models.accounts;

import models.client.Client;

/**
 * Provides a method to create an Account object.
 */
public class AccountFactory {
    public static Account getAccount(String accountType, Client client) {
        if (accountType == null) {
            return null;
        }
        if (accountType.equalsIgnoreCase("checking")) {
            return new CheckingAccount(client);
        }
        if (accountType.equalsIgnoreCase("savings")) {
            return new SavingsAccount(client);
        }
        return null;
    }
}
