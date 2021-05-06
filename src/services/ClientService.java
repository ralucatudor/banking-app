package services;

import models.client.Client;
import utils.Address;

import java.io.FileNotFoundException;
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

    public void registerNewClient(String firstName,
                                  String lastName,
                                  String emailAddress,
                                  Address address) {
        Client client = new Client(firstName, lastName, emailAddress, address);
        clients.add(client);
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

    public void openCheckingAccountForClient(String clientEmailAddress) throws Exception {
        Client client = this.getClientByEmailAddress(clientEmailAddress);
        accountService.openCheckingAccount(client);
    }

    public void openSavingsAccountForClient(String clientEmailAddress) throws Exception {
        Client client = this.getClientByEmailAddress(clientEmailAddress);
        accountService.openSavingsAccount(client);
    }

    public void showClientAccounts(String clientEmailAddress) throws Exception {
        Client client = this.getClientByEmailAddress(clientEmailAddress);
        accountService.showClientAccounts(client);
    }

    public void loadDataFromCsv(CsvReaderService reader) throws FileNotFoundException {
        List<List<String>> dbData = reader.read("data\\clients.csv");

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
}
