package expenses_tracker.services;

import expenses_tracker.data.BankState;
import expenses_tracker.data.IncomeSourceState;
import expenses_tracker.models.BankModel;
import expenses_tracker.models.IncomeSourceModel;

import java.sql.*;

public class IncomeSourceService {
    static Statement statement = null;
    static ResultSet resultSetIncome = null;
    public static IncomeSourceModel createNewIncomeSource(String[] inputData) {
        IncomeSourceModel newIncomeSource = new IncomeSourceModel(
                Integer.parseInt(inputData[0]),
                Integer.parseInt(inputData[1]),
                inputData[3]
        );
        IncomeSourceState.incomeHashMap.put(newIncomeSource.getId(), newIncomeSource);
        return  newIncomeSource;
    }

    public static void insertIncomeSourceIntoDatabase(
            String incomeName, int userId, Connection dbConnection)  throws SQLException {

        String sql = "INSERT INTO expense_tracker.incomeSource " +
                "(income_name, user) " +
                "VALUES (?, ?);";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);

            ps.setString(1, incomeName);
            ps.setInt(2, userId);

            System.out.println(ps);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateIncomeHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        // Result set get the result of the SQL query
        resultSetIncome = statement
                .executeQuery("select * from expense_tracker.incomeSource");
        while (resultSetIncome.next()) {
            int id = resultSetIncome.getInt("idincomeSource");
            String incomeName = resultSetIncome.getString("income_name");
            int userId = resultSetIncome.getInt("user");
            String[] inputData = { String.valueOf(id), incomeName, String.valueOf(userId) };
            createNewIncomeSource(inputData);
        }
    }

    public static void updateIncomeHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        // Result set get the result of the SQL query
        resultSetIncome = statement
                .executeQuery("select * from expense_tracker.incomeSource ORDER BY idincomeSource DESC LIMIT 1");
        while (resultSetIncome.next()) {
            int id = resultSetIncome.getInt("idincomeSource");
            String incomeName = resultSetIncome.getString("income_name");
            int userId = resultSetIncome.getInt("user");
            String[] inputData = { String.valueOf(id), incomeName, String.valueOf(userId) };
            createNewIncomeSource(inputData);
        }
    }
}
