package expenses_tracker.services;

import expenses_tracker.data.SpendingRecordState;
import expenses_tracker.models.SpendingRecordModel;

import java.math.BigDecimal;
import java.sql.*;

public class SpendingRecordService {
    static Statement statement = null;
    static ResultSet resultSetSpendingRecord = null;

    public static void createNewSpendingRecord
            (int id, int expenseId, BigDecimal amount, Timestamp time) {
        SpendingRecordModel newSpendingRecord = new SpendingRecordModel(
                id, expenseId, amount, time
        );
        //System.out.println("New spending record created: " + newSpendingRecord.getId());
        SpendingRecordState.spendindRecordHashMap.put(
                newSpendingRecord.getId(), newSpendingRecord
        );
    }

    public static void insertNewSpendingRecordIntoDatabase(
            int expenseId, double spendingAmount,
            Connection dbConnection)  throws SQLException {

        String sql = "INSERT INTO expense_tracker.spendingRecord " +
                "(expense_id, amount) " +
                "VALUES (?, ?);";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);

            ps.setInt(1, expenseId);
            ps.setDouble(2, spendingAmount);

            System.out.println(ps);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateSpendingRecordHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        resultSetSpendingRecord = statement
                .executeQuery("select * from expense_tracker.spendingRecord");
        while (resultSetSpendingRecord.next()) {
            int id = resultSetSpendingRecord.getInt("spendingRecordId");
            int expenseId = resultSetSpendingRecord.getInt("expense_id");
            BigDecimal amount = resultSetSpendingRecord.getBigDecimal("amount");
            Timestamp time = resultSetSpendingRecord.getTimestamp("time");
            createNewSpendingRecord(id, expenseId, amount, time);
        }
    }

    public static void populateSpendingRecordHashmap(
            String userID, String[] monthAndYear,
            Connection dbConnection) throws SQLException {
        String monthYearDate = "'" + monthAndYear[1] + "-" + monthAndYear[0] + "%'";
        statement = dbConnection.createStatement();
        String sql = "SELECT spendingRecord.spendingRecordId, spendingRecord.expense_id, " +
                "spendingRecord.amount, spendingRecord.time " +
                "FROM expense_tracker.spendingRecord " +
                "JOIN expense_tracker.expense " +
                "ON expense.idexpense = spendingRecord.expense_id " +
                "JOIN expense_tracker.user " +
                "ON user.userid = expense.user " +
                "WHERE user.userId = " + userID + " " +
                "AND spendingRecord.time LIKE " + monthYearDate;
        //System.out.println(sql);
        resultSetSpendingRecord = statement
                .executeQuery(sql);
        while (resultSetSpendingRecord.next()) {
            int id = resultSetSpendingRecord.getInt("spendingRecordId");
            int expenseId = resultSetSpendingRecord.getInt("expense_id");
            BigDecimal amount = resultSetSpendingRecord.getBigDecimal("amount");
            Timestamp time = resultSetSpendingRecord.getTimestamp("time");
            //System.out.println(id + " " + expenseId + " " + amount + " " + time);
            createNewSpendingRecord(id, expenseId, amount, time);
        }
    }

    public static void updateSpendingRecordHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        resultSetSpendingRecord = statement
                .executeQuery(
                        "select * from expense_tracker.spendingRecord "
                        + "ORDER BY spendingRecordId DESC LIMIT 1"
                );
        while (resultSetSpendingRecord.next()) {
            int id = resultSetSpendingRecord.getInt("spendingRecordId");
            int expenseId = resultSetSpendingRecord.getInt("expense_id");
            BigDecimal amount = resultSetSpendingRecord.getBigDecimal("amount");
            Timestamp time = resultSetSpendingRecord.getTimestamp("time");
            createNewSpendingRecord(id, expenseId, amount, time);
        }
    }
}
