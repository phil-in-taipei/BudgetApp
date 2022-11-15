package expenses_tracker.data;

import java.sql.*;

public class DatabaseConnection {
    Connection connection = null;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String connectionString = "jdbc:mysql://localhost/expense_tracker?"
                    + "user=root&password=#####" // replace before running code
                    + "&useSSL=false&allowPublicKeyRetrieval=true";

            connection = DriverManager
                    .getConnection(connectionString);

        } catch (SQLException exc) {
            System.out.println("Exception occurred");
            exc.printStackTrace();
            System.out.println("Did you remember password? Replace #######!");
        } catch (ClassNotFoundException e) {
            System.out.println("Exception occurred - driver not found on classpath");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeDatabaseConnection() {
        try {
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error closing database connection");
            e.printStackTrace();
        }

    }
}
