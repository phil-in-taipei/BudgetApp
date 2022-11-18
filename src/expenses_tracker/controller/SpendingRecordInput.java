package expenses_tracker.controller;

import expenses_tracker.data.ExpenseState;
import expenses_tracker.data.SavingsAccountState;
import expenses_tracker.models.ExpenseModel;
import expenses_tracker.services.SpendingRecordService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class SpendingRecordInput {
    public static void handleSpendingRecordInput(
            String[] fields, String submenuName, String[] inputData,
            Connection dbConnection) throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        for (int i = 1; i < fields.length; i++) {
            PrintInfoClass.printDividerLine();
            if (Objects.equals(fields[i], "Expense ID")) {
                System.out.println("These are the expense selections");
                PrintInfoClass.printExpenseObjectsInState(
                        ExpenseState.expensesHashMap
                );
            }
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        ExpenseModel expenseObj = ExpenseState.expensesHashMap.get(
                Integer.parseInt(inputData[1])
        );
        System.out.println("Spending $" + inputData[2]);
        System.out.println("On: " + expenseObj.getExpenseName());
        SpendingRecordService.insertNewSpendingRecordIntoDatabase(Integer.parseInt(inputData[1]),
                Double.parseDouble(inputData[2]), dbConnection
        );
        SpendingRecordService.updateSpendingRecordHashmap(dbConnection);
        PrintInfoClass.printDividerLine();
    }
}
