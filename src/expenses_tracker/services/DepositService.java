package expenses_tracker.services;

import expenses_tracker.data.DepositState;
import expenses_tracker.data.SavingsAccountState;
import expenses_tracker.models.DepositModel;
import expenses_tracker.models.SavingsAccountModel;

import java.math.BigDecimal;
import java.sql.*;

public class DepositService {
    static Statement statement = null;
    static ResultSet resultSetUsers = null;

    public static void createNewDeposit(int id, int accountId, BigDecimal amount, Timestamp time) {
        DepositModel newDeposit = new DepositModel(
                id, accountId, amount, time
        );
        DepositState.depositHashMap.put(newDeposit.getId(), newDeposit);
    }
    public static void insertNewDepositIntoDatabase(
            int accountId, double depositAmount,
            Connection dbConnection)  throws SQLException {

        String sql = "INSERT INTO expense_tracker.deposit " +
                "(accountId, amount) " +
                "VALUES (?, ?);";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);

            ps.setInt(1, accountId);
            ps.setDouble(2, depositAmount);

            System.out.println(ps);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateDepositHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        resultSetUsers = statement
                .executeQuery("select * from expense_tracker.deposit");
        while (resultSetUsers.next()) {
            int id = resultSetUsers.getInt("iddeposit");
            int accountId = resultSetUsers.getInt("accountId");
            BigDecimal amount = resultSetUsers.getBigDecimal("amount");
            Timestamp time = resultSetUsers.getTimestamp("time");
            createNewDeposit(id, accountId, amount, time);
        }
    }

    public static void updateDepositHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        // Result set get the result of the SQL query
        resultSetUsers = statement
                .executeQuery("select * from expense_tracker.deposit ORDER BY iddeposit DESC LIMIT 1");
        while (resultSetUsers.next()) {
            int id = resultSetUsers.getInt("iddeposit");
            int accountId = resultSetUsers.getInt("accountId");
            BigDecimal amount = resultSetUsers.getBigDecimal("amount");
            Timestamp time = resultSetUsers.getTimestamp("time");
            createNewDeposit(id, accountId, amount, time);
        }
    }


}
