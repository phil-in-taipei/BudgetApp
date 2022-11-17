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

    public static void createNewDeposit(
            int id, BigDecimal amount,
            Timestamp time, int incomeSourceId, int accountId) {
        DepositModel newDeposit = new DepositModel(
                id, amount, time, incomeSourceId, accountId
        );
        DepositState.depositHashMap.put(newDeposit.getId(), newDeposit);
    }
    public static void insertNewDepositIntoDatabase(
            double depositAmount, int incomeSourceId, int accountId,
            Connection dbConnection)  throws SQLException {

        String sql = "INSERT INTO expense_tracker.deposit " +
                "(amount, incomeSourceId, accountId) " +
                "VALUES (?, ?, ?);";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);

            ps.setDouble(1, depositAmount);
            ps.setInt(2, incomeSourceId);
            ps.setInt(3, accountId);

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
            int incomeSourceId = resultSetUsers.getInt("incomeSourceId");
            int accountId = resultSetUsers.getInt("accountId");
            BigDecimal amount = resultSetUsers.getBigDecimal("amount");
            Timestamp time = resultSetUsers.getTimestamp("time");
            createNewDeposit(id, amount, time, accountId, incomeSourceId);
        }
    }

    public static void updateDepositHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        // Result set get the result of the SQL query
        resultSetUsers = statement
                .executeQuery("select * from expense_tracker.deposit ORDER BY iddeposit DESC LIMIT 1");
        while (resultSetUsers.next()) {
            int id = resultSetUsers.getInt("iddeposit");
            int incomeSourceId = resultSetUsers.getInt("incomeSourceId");
            BigDecimal amount = resultSetUsers.getBigDecimal("amount");
            Timestamp time = resultSetUsers.getTimestamp("time");
            int accountId = resultSetUsers.getInt("accountId");
            createNewDeposit(id, amount, time, incomeSourceId, accountId);
        }
    }


}
