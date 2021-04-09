package services;

import models.accounts.Account;
import models.transfer.Transfer;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Singleton class.
 */
public class TransferService {
    private static TransferService instance = null;
    private final TreeSet<Transfer> transfers = new TreeSet<>();
    private final AccountService accountService;

    private TransferService() {
        this.accountService = AccountService.getInstance();
    }

    public static TransferService getInstance() {
        if (instance == null) {
            instance = new TransferService();
        }
        return instance;
    }

    public void makeTransfer(String sourceIban,
                             String destinationIban,
                             BigDecimal amount,
                             String description) throws Exception {
        Account sourceAccount = this.accountService.getAccountByIban(sourceIban);
        Account destinationAccount = this.accountService.getAccountByIban(destinationIban);

        if (!sourceAccount.hasEnoughFundsForWithdrawal(amount)) {
            throw new Exception("Cannot make transfer. Not enough funds.");
        }

        Transfer transfer = new Transfer(amount, description, sourceAccount, destinationAccount);
        transfer.execute();
        transfers.add(transfer);
    }

    public List<Transfer> getTransfersForAccount(String iban) throws Exception {
        Account account = this.accountService.getAccountByIban(iban);

        return transfers.stream().filter(
                t -> t.getSourceAccount().equals(account) ||
                        t.getDestinationAccount().equals(account)
        ).collect(Collectors.toList());
    }

    public void showTransfers() {
        System.out.println("Transfers:");
        for (Transfer transfer : transfers) {
            System.out.println(transfer);
        }
    }

    public void showTransfersForAccount(String iban) throws Exception {
        List<Transfer> accountTransfers = getTransfersForAccount(iban);
        for (Transfer transfer : accountTransfers) {
            System.out.println(transfer);
        }
    }
}
