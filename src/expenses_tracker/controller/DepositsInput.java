package expenses_tracker.controller;

import expenses_tracker.data.BankState;
import expenses_tracker.data.IncomeSourceState;
import expenses_tracker.data.SavingsAccountState;
import expenses_tracker.models.BankModel;
import expenses_tracker.models.SavingsAccountModel;
import expenses_tracker.services.DepositService;
import expenses_tracker.services.SavingsAccountService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class DepositsInput {
    public static void handleCreateDepositInput(
            String[] fields, String submenuName, String[] inputData,
            Connection dbConnection) throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        for (int i = 1; i < fields.length; i++) {
            PrintInfoClass.printDividerLine();
            System.out.println("This (" + i + ")is the field: " + fields[i]);
            if (Objects.equals(fields[i], "accountId")) {
                System.out.println("These are the account selections");
                PrintInfoClass.printSavingsAccountObjectsInState(
                        SavingsAccountState.savingsAccountHashMap
                );
            }
            if (Objects.equals(fields[i], "incomeSourceId")) {
                System.out.println("These are the income source selections");
                PrintInfoClass.printIncomeObjectsInState(
                        IncomeSourceState.incomeHashMap
                );
            }
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        SavingsAccountModel accountObj = SavingsAccountState.savingsAccountHashMap.get(
                Integer.parseInt(inputData[3])
        );
        System.out.println("Deposit $" + inputData[1]);
        BigDecimal originalBalance = accountObj.getAccountBalance();
        System.out.println("Into: " + accountObj + " balance: " + originalBalance);
        DepositService.insertNewDepositIntoDatabase(Double.parseDouble(inputData[1]),
                Integer.parseInt(inputData[2]), Integer.parseInt(inputData[3]),dbConnection
        );
        DepositService.updateDepositHashmap(dbConnection);
        SavingsAccountModel updatedAccount = SavingsAccountService.updateAccountBalanceAdd(
                Double.parseDouble(inputData[1]), accountObj.getId()
        );
        System.out.println("updating account in database...");
        SavingsAccountService.updateAccountInDatabase(
                updatedAccount.getAccountBalance(),
                updatedAccount.getId(), dbConnection
        );
        PrintInfoClass.printDividerLine();
    }
}
