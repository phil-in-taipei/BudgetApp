package expenses_tracker.services;

import expenses_tracker.data.WithdrawState;
import expenses_tracker.models.WithdrawModel;

import java.math.BigDecimal;
import java.sql.*;

public class WithdrawService {
    static Statement statement = null;
    static ResultSet resultSetWithdraws = null;

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
        resultSetWithdraws = statement
                .executeQuery("select * from expense_tracker.withdraw");
        while (resultSetWithdraws.next()) {
            int id = resultSetWithdraws.getInt("idwithdraw");
            int accountId = resultSetWithdraws.getInt("accountId");
            BigDecimal amount = resultSetWithdraws.getBigDecimal("amount");
            Timestamp time = resultSetWithdraws.getTimestamp("time");
            createNewWithdraw(id, accountId, amount, time);
        }
    }
    public static void populateWithdrawHashmap(
            String userID, String[] monthAndYear, Connection dbConnection
    ) throws SQLException {
        String monthYearDate = "'" + monthAndYear[1] + "-" + monthAndYear[0] + "%'";
        statement = dbConnection.createStatement();
        String sql = "SELECT withdraw.idwithdraw, withdraw.accountId, " +
                "withdraw.amount, withdraw.time " +
                "FROM expense_tracker.withdraw " +
                "JOIN expense_tracker.account " +
                "ON withdraw.accountId = account.idaccount " +
                "JOIN expense_tracker.user " +
                "ON user.userid = account.userId " +
                "WHERE user.userid =" + userID + " " +
                "AND withdraw.time LIKE " + monthYearDate;
        resultSetWithdraws = statement
                .executeQuery(sql);
        while (resultSetWithdraws.next()) {
            int id = resultSetWithdraws.getInt("idwithdraw");
            int accountId = resultSetWithdraws.getInt("accountId");
            BigDecimal amount = resultSetWithdraws.getBigDecimal("amount");
            Timestamp time = resultSetWithdraws.getTimestamp("time");
            createNewWithdraw(id, accountId, amount, time);
        }
    }

    public static void updateWithdrawHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        // Result set get the result of the SQL query
        resultSetWithdraws = statement
                .executeQuery("select * from expense_tracker.withdraw ORDER BY idwithdraw DESC LIMIT 1");
        while (resultSetWithdraws.next()) {
            int id = resultSetWithdraws.getInt("idwithdraw");
            int accountId = resultSetWithdraws.getInt("accountId");
            BigDecimal amount = resultSetWithdraws.getBigDecimal("amount");
            Timestamp time = resultSetWithdraws.getTimestamp("time");
            createNewWithdraw(id, accountId, amount, time);
        }
    }
}
