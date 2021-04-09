package services;

import utils.Address;

import java.util.Scanner;

public class BankingInteractor {
    public final ClientService clientService;

    // Actions
    enum Queries {
        registerNewClient,
        showClients,
        exit
    }

    public BankingInteractor() {
        this.clientService = ClientService.getInstance();
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
        String streetAddress = scanner.next();
        System.out.println("City:");
        String city = scanner.next();
        System.out.println("Country:");
        String country = scanner.next();
        System.out.println("Postal code:");
        String postalCode = scanner.next();

        return new Address(streetAddress, city, country, postalCode);
    }
}