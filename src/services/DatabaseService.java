package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Singleton class for handling database operations.
 */
public class DatabaseService {
    private static DatabaseService instance = null;

    private DatabaseService() {}

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    public void test() {
        // String URL = "jdbc:mysql://127.0.0.1:3306/bankpao";
        String URL = "jdbc:mysql://localhost:3306/bankpao";
        // URL-ul este format din protocol + vendor + adresa
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             Statement statement = connection.createStatement()) {

            String query = "insert into client values (null, 'Tom', 'Tomescu', 'tomescu@pao.ro', 'Str. FMI nr. 10', 'Bucharest', 'Romania', '010101', '2021-04-10')";
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
