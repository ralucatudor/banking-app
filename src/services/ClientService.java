package services;

import models.client.Client;
import utils.Address;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class.
 */
public class ClientService {
    private List<Client> clients = new ArrayList<>();
    private static ClientService instance = null;

    private ClientService() {}

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
        System.out.println("Successfully registered client!");
        System.out.println(client);
    }

    public void showClients() {
        System.out.println("Clients:");
        for (Client client : clients) {
            System.out.println(client);
        }
    }
}
