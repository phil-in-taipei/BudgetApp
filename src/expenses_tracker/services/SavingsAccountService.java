package expenses_tracker.services;

import expenses_tracker.data.SavingsAccountState;
import expenses_tracker.data.UserState;
import expenses_tracker.models.SavingsAccountModel;
import expenses_tracker.models.UserModel;

import java.math.BigDecimal;
import java.sql.*;

public class SavingsAccountService {

    static Statement statement = null;
    static ResultSet resultSetUsers = null;
    public static void createNewSavingsAccount(int id, int userId, int bankId, BigDecimal balance) {
        SavingsAccountModel newSavingsAccount = new SavingsAccountModel(
                id, userId, bankId, balance
        );
        SavingsAccountState.savingsAccountHashMap.put(newSavingsAccount.getId(), newSavingsAccount);
    }

    public static void insertNewAccountIntoDatabase(
            int userId, int bankId,
            Connection dbConnection)  throws SQLException {

        String sql = "INSERT INTO expense_tracker.account " +
                "(bankId, userId, balance) " +
                "VALUES (?, ?, ?);";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);

            ps.setInt(1, bankId);
            ps.setInt(2, userId);
            ps.setDouble(3, 0.0);

            System.out.println(ps);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateAccountsHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        resultSetUsers = statement
                .executeQuery("select * from expense_tracker.account");
        while (resultSetUsers.next()) {
            int id = resultSetUsers.getInt("idaccount");
            int bankId = resultSetUsers.getInt("bankId");
            int userId = resultSetUsers.getInt("userId");
            BigDecimal balance = resultSetUsers.getBigDecimal("balance");
            createNewSavingsAccount(id, userId, bankId, balance);
        }
    }

    public static void updateAccountsHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        // Result set get the result of the SQL query
        resultSetUsers = statement
                .executeQuery("select * from expense_tracker.account ORDER BY userid DESC LIMIT 1");
        while (resultSetUsers.next()) {
            int id = resultSetUsers.getInt("idaccount");
            int bankId = resultSetUsers.getInt("bankId");
            int userId = resultSetUsers.getInt("userId");
            BigDecimal balance = resultSetUsers.getBigDecimal("balance");
            createNewSavingsAccount(id, userId, bankId, balance);
        }
    }

}
