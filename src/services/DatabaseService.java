package services;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Singleton class for handling database operations.
 */
public class DatabaseService {
    // "jdbc:mysql://127.0.0.1:3306/bankpao"
    // URL-ul este format din protocol + vendor + adresa
    private static String URL = "jdbc:mysql://localhost:3306/bankpao";
    private static String user = "root";
    private static String password = "";

    private static DatabaseService instance = null;

    private DatabaseService() {}

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    /**
     * READ Client
     */
    public List<List<String>> loadClientData() {
        try (Connection connection = DriverManager.getConnection(URL, user, password)) {
            ResultSet dbResult = getClients(connection);
            List<List<String>> data = new ArrayList<>();

            while (dbResult.next()) {
                List<String> clients = Arrays.asList(
                        dbResult.getString("id"),
                        dbResult.getString("firstName"),
                        dbResult.getString("lastName"),
                        dbResult.getString("emailAddress"),
                        dbResult.getString("streetAddress"),
                        dbResult.getString("city"),
                        dbResult.getString("country"),
                        dbResult.getString("postalCode"),
                        dbResult.getString("registrationDate")
                );
                data.add(clients);
            }
            return data;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static ResultSet getClients(Connection connection) throws SQLException {
        String query = "SELECT * FROM client";
        return connection.prepareStatement(query).executeQuery();
    }

    /**
     * READ Atm
     */
    public List<List<String>> loadAtmData() {
        try (Connection connection = DriverManager.getConnection(URL, user, password)) {
            ResultSet dbResult = getAtms(connection);
            List<List<String>> data = new ArrayList<>();

            while (dbResult.next()) {
                List<String> atms = Arrays.asList(
                        dbResult.getString("streetAddress"),
                        dbResult.getString("city"),
                        dbResult.getString("country"),
                        dbResult.getString("postalCode"),
                        dbResult.getString("funds"),
                        dbResult.getString("identifier")
                );
                data.add(atms);
            }
            return data;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static ResultSet getAtms(Connection connection) throws SQLException {
        String query = "SELECT * FROM atm";
        return connection.prepareStatement(query).executeQuery();
    }

    /**
     * READ transfer
     */
    public List<List<String>> loadTransferData() {
        try (Connection connection = DriverManager.getConnection(URL, user, password)) {
            ResultSet dbResult = getTransfers(connection);
            List<List<String>> data = new ArrayList<>();

            while (dbResult.next()) {
                List<String> transfers = Arrays.asList(
                        dbResult.getString("date"),
                        dbResult.getString("amount"),
                        dbResult.getString("description"),
                        dbResult.getString("sourceIban"),
                        dbResult.getString("destinationIban")
                );
                data.add(transfers);
            }
            return data;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static ResultSet getTransfers(Connection connection) throws SQLException {
        String query = "SELECT * FROM transfer";
        return connection.prepareStatement(query).executeQuery();
    }

    /**
     * CREATE client
     */
    public void insertClient(UUID id,
                             String firstName,
                             String lastName,
                             String emailAddress,
                             String streetAddress,
                             String city,
                             String country,
                             String postalCode,
                             LocalDate registrationDate) {
        try (Connection connection = DriverManager.getConnection(URL, user, password)) {

            String query = "INSERT INTO client VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, id.toString());
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, emailAddress);
            preparedStatement.setString(5, streetAddress);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, country);
            preparedStatement.setString(8, postalCode);
            preparedStatement.setDate(9, Date.valueOf(registrationDate));

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * CREATE atm
     */
    public void insertAtm(String identifier,
                          String streetAddress,
                          String city,
                          String country,
                          String postalCode,
                          BigDecimal funds) {
        try (Connection connection = DriverManager.getConnection(URL, user, password)) {

            String query = "INSERT INTO atm VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, identifier);
            preparedStatement.setString(2, streetAddress);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, country);
            preparedStatement.setString(5, postalCode);
            preparedStatement.setBigDecimal(6, funds);

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * CREATE transfer
     */
    public void insertTransfer(LocalDate date,
                               BigDecimal amount,
                               String description,
                               String sourceIban,
                               String destinationIban) {
        try (Connection connection = DriverManager.getConnection(URL, user, password)) {

            String query = "INSERT INTO atm VALUES (NULL, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setBigDecimal(2, amount);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, sourceIban);
            preparedStatement.setString(5, destinationIban);

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * UPDATE atm (funds)
     */
    public void updateAtmFunds(String atmIdentifier, BigDecimal newFunds) {
        try (Connection connection = DriverManager.getConnection(URL, user, password)) {

            String query = "UPDATE atm SET funds = ? WHERE identifier = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setBigDecimal(1, newFunds);
            preparedStatement.setString(2, atmIdentifier);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * DELETE atm
     */
    public void deleteAtm(String atmIdentifier) {
        try (Connection connection = DriverManager.getConnection(URL, user, password)) {

            String query = "DELETE FROM atm WHERE identifier = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, atmIdentifier);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
