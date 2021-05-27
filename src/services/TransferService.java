package services;

import models.accounts.Account;
import models.transfer.Transfer;
import models.transfer.TransferComparator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Singleton class for managing banking transfers.
 */
public class TransferService {
    private static TransferService instance = null;
    private final SortedSet<Transfer> transfers = new TreeSet<>(new TransferComparator());
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

        if (!sourceAccount.hasEnoughFundsForWithdrawal(amount.add(sourceAccount.getTransferFee(amount)))) {
            throw new Exception("Cannot make transfer. Not enough funds.");
        }

        Transfer transfer = new Transfer(amount, description, sourceAccount, destinationAccount);
        transfers.add(transfer);
    }

    public List<Transfer> getTransfersForAccount(String iban) {
        return transfers.stream().filter(
                t -> t.getSourceIban().equals(iban) ||
                        t.getDestinationIban().equals(iban)
        ).collect(Collectors.toList());
    }

    public void showTransfers() {
        System.out.println("Transfers:");
        for (Transfer transfer : transfers) {
            System.out.println(transfer);
        }
    }

    public void showTransfersForAccount(String iban) {
        List<Transfer> accountTransfers = getTransfersForAccount(iban);
        for (Transfer transfer : accountTransfers) {
            System.out.println(transfer);
        }
    }

    public void loadDataFromStringList(List<List<String>> dbData) {
        for (List<String> data : dbData) {
            Transfer transfer = new Transfer(
                    LocalDate.parse(data.get(0)),
                    new BigDecimal(data.get(1)),
                    data.get(2),
                    data.get(3),
                    data.get(4)
            );

            transfers.add(transfer);
        }
    }

    public void loadDataFromCsv(CsvReaderService reader) throws FileNotFoundException {
        List<List<String>> dbData = reader.read("data\\transfers.csv");

        this.loadDataFromStringList(dbData);
    }

    public void updateCsvData(CsvWriterService writer) throws IOException {
        writer.emptyFile("data\\transfers.csv");
        for (Transfer transfer : transfers) {
            List<String> data = new ArrayList<>();

            data.add(transfer.getDate().toString());
            data.add(transfer.getAmount().toString());
            data.add(transfer.getDescription());
            data.add(transfer.getSourceIban());
            data.add(transfer.getDestinationIban());

            writer.write("data\\transfers.csv", data, true);
        }
    }
}
