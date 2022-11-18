package expenses_tracker.controller;

import expenses_tracker.services.ExpenseService;
import expenses_tracker.services.IncomeSourceService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ExpensesInput {
    public static void handleCreateExpenseInput(
            String[] fields, String submenuName, String[] inputData, Connection dbConnection)
            throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        for (int i = 1; i < fields.length; i++) {
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        ExpenseService.insertExpenseIntoDatabase(inputData[2],
                Integer.parseInt(inputData[1]), dbConnection);
        //PrintInfoClass.printDividerLine();
        ExpenseService.updateExpenseHashmap(dbConnection);
        PrintInfoClass.printDividerLine();
    }
}
