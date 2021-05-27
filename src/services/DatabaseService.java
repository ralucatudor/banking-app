package services;

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
     * READ
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
//                System.out.println(
//                        dbResult.getString("id") + " " +
//                        dbResult.getString("firstName") + " " +
//                        dbResult.getString("lastName") + " " +
//                        dbResult.getString("emailAddress") + " " +
//                        dbResult.getString("streetAddress") + " " +
//                        dbResult.getString("city") + " " +
//                        dbResult.getString("country") + " " +
//                        dbResult.getString("postalCode") + " " +
//                        dbResult.getString("registrationDate")
//                );
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
}
