package expenses_tracker.services;

import expenses_tracker.data.WithdrawState;
import expenses_tracker.models.WithdrawModel;

import java.math.BigDecimal;
import java.sql.*;

public class WithdrawService {
    static Statement statement = null;
    static ResultSet resultSetUsers = null;

    public static void createNewWithdraw
            (int id, int accountId, BigDecimal amount, Timestamp time) {
        WithdrawModel newWithdraw = new WithdrawModel(
                id, accountId, amount, time
        );
        WithdrawState.withdrawHashMap.put(newWithdraw.getId(), newWithdraw);
    }

    public static void insertNewWithdrawIntoDatabase(
            int accountId, double withdrawAmount,
            Connection dbConnection)  throws SQLException {

        String sql = "INSERT INTO expense_tracker.withdraw " +
                "(accountId, amount) " +
                "VALUES (?, ?);";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);

            ps.setInt(1, accountId);
            ps.setDouble(2, withdrawAmount);

            System.out.println(ps);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateWithdrawHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        resultSetUsers = statement
                .executeQuery("select * from expense_tracker.withdraw");
        while (resultSetUsers.next()) {
            int id = resultSetUsers.getInt("idwithdraw");
            int accountId = resultSetUsers.getInt("accountId");
            BigDecimal amount = resultSetUsers.getBigDecimal("amount");
            Timestamp time = resultSetUsers.getTimestamp("time");
            createNewWithdraw(id, accountId, amount, time);
        }
    }

    public static void updateWithdrawHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        // Result set get the result of the SQL query
        resultSetUsers = statement
                .executeQuery("select * from expense_tracker.withdraw ORDER BY idwithdraw DESC LIMIT 1");
        while (resultSetUsers.next()) {
            int id = resultSetUsers.getInt("idwithdraw");
            int accountId = resultSetUsers.getInt("accountId");
            BigDecimal amount = resultSetUsers.getBigDecimal("amount");
            Timestamp time = resultSetUsers.getTimestamp("time");
            createNewWithdraw(id, accountId, amount, time);
        }
    }
}
