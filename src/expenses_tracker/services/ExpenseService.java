package expenses_tracker.services;

import expenses_tracker.data.ExpenseState;
import expenses_tracker.data.IncomeSourceState;
import expenses_tracker.models.ExpenseModel;
import expenses_tracker.models.IncomeSourceModel;

import java.sql.*;

public class ExpenseService {
    static Statement statement = null;
    static ResultSet resultSetExpenses = null;

    public static ExpenseModel createNewExpense(int id, String expenseName, int userId) {
        ExpenseModel newExpense = new ExpenseModel(
                id, expenseName, userId
        );
        ExpenseState.expensesHashMap.put(newExpense.getId(), newExpense);
        return  newExpense;
    }

    public static void insertExpenseIntoDatabase(
            String expenseName, int userId, Connection dbConnection
    )  throws SQLException {

        String sql = "INSERT INTO expense_tracker.expense " +
                "(expense_name, user) " +
                "VALUES (?, ?);";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);

            ps.setString(1, expenseName);
            ps.setInt(2, userId);

            System.out.println(ps);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateExpenseHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        resultSetExpenses = statement
                .executeQuery("select * from expense_tracker.expense");
        while (resultSetExpenses.next()) {
            int id = resultSetExpenses.getInt("idexpense");
            String expenseName = resultSetExpenses.getString("expense_name");
            int userId = resultSetExpenses.getInt("user");
            createNewExpense(id, expenseName, userId);
        }
    }

    // SELECT * FROM expense_tracker.expense
    //WHERE expense.user = 1;

    public static void populateExpenseHashmap(String userID, Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        resultSetExpenses = statement
                .executeQuery("select * from expense_tracker.expense WHERE expense.user = " + userID);
        while (resultSetExpenses.next()) {
            int id = resultSetExpenses.getInt("idexpense");
            String expenseName = resultSetExpenses.getString("expense_name");
            int userId = resultSetExpenses.getInt("user");
            createNewExpense(id, expenseName, userId);
        }
    }

    public static void updateExpenseHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        // Result set get the result of the SQL query
        resultSetExpenses = statement
                .executeQuery("select * from expense_tracker.expense ORDER BY idexpense DESC LIMIT 1");
        while (resultSetExpenses.next()) {
            int id = resultSetExpenses.getInt("idexpense");
            String expenseName = resultSetExpenses.getString("expense_name");
            int userId = resultSetExpenses.getInt("user");
            createNewExpense(id, expenseName, userId);
        }
    }

}
