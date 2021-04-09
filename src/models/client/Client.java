package models.client;

import utils.Address;

import java.time.LocalDateTime;

/**
 * Holds information about a bank customer (in this design, an account holder).
 */
public class Client {
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final LocalDateTime registrationDate;

    public Client(String firstName,
                  String lastName,
                  String emailAddress,
                  Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.registrationDate = LocalDateTime.now();
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
