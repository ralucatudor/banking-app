package services;

import models.client.Client;
import utils.Address;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Singleton class for managing bank clients.
 */
public class ClientService {
    private final List<Client> clients = new ArrayList<>();
    private static ClientService instance = null;
    private final AccountService accountService;

    private ClientService() {
        this.accountService = AccountService.getInstance();
    }

    public static ClientService getInstance() {
        if (instance == null) {
            instance = new ClientService();
        }
        return instance;
    }

    public void registerNewClient(DatabaseService databaseService,
                                  String firstName,
                                  String lastName,
                                  String emailAddress,
                                  Address address) {
        Client client = new Client(firstName, lastName, emailAddress, address);
        clients.add(client);

        databaseService.insertClient(
                client.getId(),
                firstName,
                lastName,
                emailAddress,
                address.getStreetAddress(),
                address.getCity(),
                address.getCountry(),
                address.getPostalCode(),
                client.getRegistrationDate()
        );
    }

    public void showClients() {
        System.out.println("Clients:");
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    public Client getClientByEmailAddress(String clientEmailAddress) throws Exception {
        for (Client client : clients) {
            if (client.getEmailAddress().equals(clientEmailAddress)) {
                return client;
            }
        }
        throw new Exception(
                "Client with email address " + clientEmailAddress + " does not exist.");
    }

    public void openCheckingAccountForClient(
            DatabaseService databaseService,
            String clientEmailAddress) throws Exception {
        Client client = this.getClientByEmailAddress(clientEmailAddress);
        accountService.openCheckingAccount(databaseService, client);
    }

    public void openSavingsAccountForClient(
            DatabaseService databaseService,
            String clientEmailAddress) throws Exception {
        Client client = this.getClientByEmailAddress(clientEmailAddress);
        accountService.openSavingsAccount(databaseService, client);
    }

    public void showClientAccounts(String clientEmailAddress) throws Exception {
        Client client = this.getClientByEmailAddress(clientEmailAddress);
        accountService.showClientAccounts(client);
    }

    public void loadDataFromStringList(List<List<String>> dbData) {
        for (List<String> data : dbData) {
            Client client = new Client(
                    UUID.fromString(data.get(0)),
                    data.get(1),
                    data.get(2),
                    data.get(3),
                    new Address(data.get(4), data.get(5), data.get(6), data.get(7)),
                    LocalDate.parse(data.get(8))
            );
            clients.add(client);
        }
    }

    public void loadDataFromCsv(CsvReaderService reader) throws FileNotFoundException {
        List<List<String>> dbData = reader.read("data\\clients.csv");

        this.loadDataFromStringList(dbData);
    }

    public void updateCsvData(CsvWriterService writer) throws IOException {
        writer.emptyFile("data\\clients.csv");
        for (Client client : clients) {
            List<String> data = new ArrayList<>();

            data.add(client.getId().toString());
            data.add(client.getFirstName());
            data.add(client.getLastName());
            data.add(client.getEmailAddress());

            Address clientAddress = client.getAddress();
            data.add(clientAddress.getStreetAddress());
            data.add(clientAddress.getCity());
            data.add(clientAddress.getCountry());
            data.add(clientAddress.getPostalCode());

            data.add(client.getRegistrationDate().toString());

            writer.write("data\\clients.csv", data, true);
        }
    }
}
