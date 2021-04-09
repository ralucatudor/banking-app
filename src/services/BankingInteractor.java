package services;

import utils.Address;

import java.math.BigDecimal;
import java.util.Scanner;

public class BankingInteractor {
    // private ??
    public final ClientService clientService;
    public final AtmService atmService;

    // Actions
    enum Queries {
        registerNewClient,
        showClients,
        openCheckingAccountForClient,
        showClientAccounts,
        createAtm,
        showAtms,
        depositInAccount,
        exit
    }

    public BankingInteractor() {
        this.clientService = ClientService.getInstance();
        this.atmService = new AtmService(); // make singleton?
    }

    public void run(Scanner scanner) {
        boolean stopRunning = false;
        System.out.println("Welcome to PAO Bank!");
        while (!stopRunning) {
            System.out.println("Choose one of the following actions: ");
            for (int i = 0; i < Queries.values().length; i++) {
                System.out.println((i+1) + ") " + Queries.values()[i]);
            }
            System.out.println("I'll go with option: ");

            int queryIdx = scanner.nextInt() - 1;
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
                default ->
                        // TODO Handle this
                        System.out.println("hopa");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
