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
