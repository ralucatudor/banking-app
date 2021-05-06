package models.client;

import utils.Address;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Holds information about a bank customer (in this design, an account holder).
 */
public class Client {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final LocalDate registrationDate;

    public Client(String firstName,
                  String lastName,
                  String emailAddress,
                  Address address) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.registrationDate = LocalDate.now();
    }

    public Client(UUID id,
                  String firstName,
                  String lastName,
                  String emailAddress,
                  Address address,
                  LocalDate registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address=" + address +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
