package services;

import models.client.Client;
import utils.Address;

import java.util.ArrayList;
import java.util.List;

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

    public void showClientAccounts(String clientEmailAddress) throws Exception {
        Client client = this.getClientByEmailAddress(clientEmailAddress);
        accountService.showClientAccounts(client);
    }
}
