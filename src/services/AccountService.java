package services;

/**
 * Singleton class.
 */
public class AccountService {
    private static AccountService instance = null;

    private AccountService() {}

    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }
}
