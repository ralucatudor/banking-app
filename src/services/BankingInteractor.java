package services;

import utils.Address;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Makes the app run and do stuff, based on user input :)
 */
public class BankingInteractor {
    private final ClientService clientService;
    private final AtmService atmService;
    private final AccountService accountService;
    private final TransferService transferService;

    // Possible actions, held in an enum for cleaner code.
    enum Queries {
        registerNewClient("Register a new client."),
        showClients("Show all clients."),
        openCheckingAccountForClient("Open Checking Account for a client."),
        showClientAccounts("Show client accounts."),
        createAtm("Create ATM."),
        showAtms("Show all ATMs."),
        depositInAccount("Deposit funds in an account."),
        makeTransfer("Make a transfer between two accounts."),
        showTransfersForAccount("Show transfers for an account."),
        showAllTransfers("Show all transfers."),
        showLinkedCards("Show cards linked to an account."),
        addCardToAccount("Add another card to an account."),
        exit("Exit");

        public final String query;
        Queries(String query) {
            this.query = query;
        }
    }

    public BankingInteractor() {
        this.clientService = ClientService.getInstance();
        this.accountService = AccountService.getInstance();
        this.transferService = TransferService.getInstance();
        this.atmService = new AtmService(); // make singleton?
    }

    public void run(Scanner scanner) {
        boolean stopRunning = false;
        System.out.println("Welcome to PAO Bank!");
        while (!stopRunning) {
            System.out.println("Choose one of the following actions: ");
            for (int i = 0; i < Queries.values().length; i++) {
                System.out.println((i+1) + ") " + Queries.values()[i].query);
            }
            System.out.println("I'll go with option: ");

            int queryIdx = scanner.nextInt() - 1;

            if (queryIdx > Queries.values().length) {
                System.out.println("Please choose one of the available options!");
                continue;
            }

            switch (Queries.values()[queryIdx]) {
                case registerNewClient -> registerNewClientInteract(scanner);
                case showClients -> clientService.showClients();
                case exit -> {
                    stopRunning = true;
                    System.out.println("Thank you for your time managing PAO Bank!");
                }
                case openCheckingAccountForClient -> openCheckingAccountInteractor(scanner);
                case showClientAccounts -> showClientAccountsInteractor(scanner);
                case createAtm -> createAtmInteract(scanner);
                case showAtms -> atmService.showAtms();
                case depositInAccount -> depositInAccountInteractor(scanner);
                case makeTransfer -> makeTransferInteractor(scanner);
                case showTransfersForAccount -> showTransfersForAccountInteractor(scanner);
                case showAllTransfers -> transferService.showTransfers();
                case showLinkedCards -> showLinkedCardsInteractor(scanner);
                case addCardToAccount -> addCardToAccountInteractor(scanner);
            }
        }
    }

    /**
     * Has the functionality to get input from the user,
     * needed for registering a client.
     */
    private void registerNewClientInteract(Scanner scanner) {
        System.out.println("Please enter");

        System.out.println("First name:");
        String firstName = scanner.next();
        System.out.println("Last name:");
        String lastName = scanner.next();
        System.out.println("Email address:");
        String emailAddress = scanner.next();

        Address address = readAddress(scanner);

        clientService.registerNewClient(firstName,
                lastName,
                emailAddress,
                address);

        System.out.println("Successfully registered client!");
    }

    private Address readAddress(Scanner scanner) {
        System.out.println("Street address:");
        // Read the '\n',
        scanner.nextLine();
        // so that the next line is successfully read.
        String streetAddress = scanner.nextLine();
        System.out.println("City:");
        String city = scanner.next();
        System.out.println("Country:");
        String country = scanner.next();
        System.out.println("Postal code:");
        String postalCode = scanner.next();

        return new Address(streetAddress, city, country, postalCode);
    }

    private String getClientIdentifier(Scanner scanner) {
        System.out.println("Please enter the client's email address:");
        return scanner.next();
    }

    private void openCheckingAccountInteractor(Scanner scanner) {
        String clientIdentifier = getClientIdentifier(scanner);

        try {
            clientService.openCheckingAccountForClient(clientIdentifier);
            System.out.println("Successfully opened checking account!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showClientAccountsInteractor(Scanner scanner) {
        String clientIdentifier = getClientIdentifier(scanner);

        try {
            clientService.showClientAccounts(clientIdentifier);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createAtmInteract(Scanner scanner) {
        System.out.println("Please enter");

        System.out.println("Funds:");
        BigDecimal funds = new BigDecimal(scanner.next());

        System.out.println("ATM identifier:");
        String identifier = scanner.next();

        Address address = readAddress(scanner);

        atmService.createAtm(address, funds, identifier);
        System.out.println("Successfully created ATM!");
    }

    private void depositInAccountInteractor(Scanner scanner) {
        System.out.println("Please enter");

        System.out.println("ATM identifier:");
        String atmIdentifier = scanner.next();

        System.out.println("Card number:");
        String cardNumber = scanner.next();

        System.out.println("Amount:");
        BigDecimal amount = new BigDecimal(scanner.next());

        try {
            atmService.depositInAccount(atmIdentifier, cardNumber, amount);
            System.out.println("Successfully added funds in the linked account!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeTransferInteractor(Scanner scanner) {
        System.out.println("Please enter");

        System.out.println("Sender IBAN:");
        String senderIban = scanner.next();

        System.out.println("Receiver IBAN:");
        String receiverIban = scanner.next();

        System.out.println("Transfer description:");
        String description = scanner.next();

        System.out.println("Amount:");
        BigDecimal amount = new BigDecimal(scanner.next());

        try {
            transferService.makeTransfer(senderIban, receiverIban, amount, description);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showTransfersForAccountInteractor(Scanner scanner) {
        System.out.println("Please enter");

        System.out.println("IBAN:");
        String iban = scanner.next();

        try {
            transferService.showTransfersForAccount(iban);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showLinkedCardsInteractor(Scanner scanner) {
        System.out.println("Please enter");

        System.out.println("IBAN:");
        String iban = scanner.next();

        try {
            accountService.showLinkedCards(iban);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCardToAccountInteractor(Scanner scanner) {
        System.out.println("Please enter");

        System.out.println("IBAN:");
        String iban = scanner.next();

        try {
            accountService.addCard(iban);
            System.out.println("Successfully added another card to the specified account!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
