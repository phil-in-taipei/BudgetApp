package expenses_tracker.controller;

import expenses_tracker.data.BankState;
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
        System.out.println("Deposit $" + inputData[2]);
        System.out.println("Into: " + accountObj);
        DepositService.insertNewDepositIntoDatabase(Integer.parseInt(inputData[1]),
                Double.parseDouble(inputData[2]), dbConnection
        );
        DepositService.updateDepositHashmap(dbConnection);
        SavingsAccountModel updatedAccount = SavingsAccountService.updateAccountBalance(
                Double.parseDouble(inputData[2]), accountObj.getId()
        );
        SavingsAccountService.updateAccountInDatabase(
                updatedAccount.getAccountBalance(),
                updatedAccount.getId(), dbConnection
        );
        PrintInfoClass.printDividerLine();
    }
}
