package expenses_tracker.controller;

import expenses_tracker.data.SavingsAccountState;
import expenses_tracker.models.SavingsAccountModel;
import expenses_tracker.services.SavingsAccountService;
import expenses_tracker.services.WithdrawService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class WithdrawsInput {
    public static void handleCreateWithdrawInput(
            String[] fields, String submenuName, String[] inputData,
            Connection dbConnection) throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        for (int i = 1; i < fields.length; i++) {
            PrintInfoClass.printDividerLine();
            if (Objects.equals(fields[i], "accountId")) {
                System.out.println("These are the account selections");
                PrintInfoClass.printSavingsAccountObjectsInState(
                        SavingsAccountState.savingsAccountHashMap
                );
            }
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        SavingsAccountModel accountObj = SavingsAccountState.savingsAccountHashMap.get(
                Integer.parseInt(inputData[1])
        );
        System.out.println("Withdraw $" + inputData[2]);
        System.out.println("From: " + accountObj);
        WithdrawService.insertNewWithdrawIntoDatabase(Integer.parseInt(inputData[1]),
                Double.parseDouble(inputData[2]), dbConnection
        );
        WithdrawService.updateWithdrawHashmap(dbConnection);
        SavingsAccountModel updatedAccount = SavingsAccountService.updateAccountBalanceSubtract(
                Double.parseDouble(inputData[2]), accountObj.getId()
        );
        SavingsAccountService.updateAccountInDatabase(
                updatedAccount.getAccountBalance(),
                updatedAccount.getId(), dbConnection
        );
        PrintInfoClass.printDividerLine();
    }

}
