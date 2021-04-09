package models.client;

import models.accounts.Account;
import models.accounts.AccountFactory;
import utils.Address;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final LocalDateTime registrationDate;
    protected List<Account> accounts = new ArrayList<>();

    public Client(String firstName, String lastName, String emailAddress, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.registrationDate = LocalDateTime.now();
    }

    public Account openAccount() {
        Account account = AccountFactory.getAccount("checking");
        accounts.add(account);
        return account;
    }

    public Account openSavingsAccount() {
        Account account = AccountFactory.getAccount("savings");
        accounts.add(account);
        return account;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address=" + address +
                ", registrationDate=" + registrationDate +
                ", accounts=" + accounts +
                '}';
    }
}
