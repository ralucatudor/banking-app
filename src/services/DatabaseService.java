package services;

import java.sql.*;
import java.time.LocalDate;

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

    public void insertClient(String firstName,
                             String lastName,
                             String emailAddress,
                             String streetAddress,
                             String city,
                             String country,
                             String postalCode,
                             LocalDate registrationDate) {
        try (Connection connection = DriverManager.getConnection(URL, user, password)) {

            String query = "INSERT INTO client VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, emailAddress);
            preparedStatement.setString(4, streetAddress);
            preparedStatement.setString(5, city);
            preparedStatement.setString(6, country);
            preparedStatement.setString(7, postalCode);
            preparedStatement.setDate(8, Date.valueOf(registrationDate));

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
