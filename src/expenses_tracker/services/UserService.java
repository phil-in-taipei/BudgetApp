package expenses_tracker.services;
import expenses_tracker.controller.PrintInfoClass;
import expenses_tracker.models.UserModel;
import expenses_tracker.data.UserState;

//import java.sql.Connection;
import java.sql.*;
import java.text.ParseException;

public class UserService {

    static Statement statement = null;
    static ResultSet resultSetUsers = null;
    public static UserModel createNewUser(String[] inputData) {
        UserModel newUser = new UserModel(
                Integer.parseInt(inputData[0]),
                inputData[1], inputData[2],
                inputData[3]
        );
        UserState.usersHashMap.put(newUser.getId(), newUser);
        return  newUser;
    }

    public static void insertUserIntoDatabase(
            String givenName, String surname,
            String email, Connection dbConnection)  throws SQLException {

        String sql = "INSERT INTO expense_tracker.user " +
                "(given_name, surname, email) " +
                "VALUES (?, ?, ?);";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);

            ps.setString(1, givenName);
            ps.setString(2, surname);
            ps.setString(3, email);

            System.out.println(ps);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserModel updateExistingUser(String[] inputData, int userId) { //
        UserModel updatedUser = UserState.usersHashMap.get(userId);
        updatedUser.setGivenName(inputData[1]);
        updatedUser.setSurname(inputData[2]);
        updatedUser.setEmail(inputData[3]);
        UserState.usersHashMap.replace(userId, updatedUser);
        return updatedUser;
    }

    public static void populateUserHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        // Result set get the result of the SQL query
        resultSetUsers = statement
                .executeQuery("select * from expense_tracker.user");
        while (resultSetUsers.next()) {
            int id = resultSetUsers.getInt("userid");
            String givenName = resultSetUsers.getString("given_name");
            String surname = resultSetUsers.getString("surname");
            String email = resultSetUsers.getString("email");
            String[] inputData = { String.valueOf(id), givenName, surname, email };
            createNewUser(inputData);
        }
    }

    public static void updateUserHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        // Result set get the result of the SQL query
        resultSetUsers = statement
                .executeQuery("select * from expense_tracker.user ORDER BY userid DESC LIMIT 1");
        while (resultSetUsers.next()) {
            int id = resultSetUsers.getInt("userid");
            String givenName = resultSetUsers.getString("given_name");
            String surname = resultSetUsers.getString("surname");
            String email = resultSetUsers.getString("email");
            String[] inputData = { String.valueOf(id), givenName, surname, email };
            createNewUser(inputData);
        }
    }

}
