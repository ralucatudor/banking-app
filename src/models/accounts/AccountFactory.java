package models.accounts;

public class AccountFactory {

    public static Account getAccount(String accountType) {
        if (accountType == null) {
            return null;
        }
        if (accountType.equalsIgnoreCase("checking")) {
            return new CheckingAccount();
        }
        if (accountType.equalsIgnoreCase("savings")) {
            return new SavingsAccount();
        }
        return null;
    }
}
