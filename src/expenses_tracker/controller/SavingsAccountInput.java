package expenses_tracker.controller;

import expenses_tracker.data.BankState;
import expenses_tracker.data.UserState;
import expenses_tracker.models.BankModel;
import expenses_tracker.models.UserModel;
import expenses_tracker.services.BankService;
import expenses_tracker.services.SavingsAccountService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class SavingsAccountInput {

    public static void handleCreateAccountInput(
            String[] fields, String submenuName, String[] inputData, Connection dbConnection)
            throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        for (int i = 1; i < fields.length; i++) {
            if (Objects.equals(fields[i], "bankId")) {
                System.out.println("These are the bank selections");
                PrintInfoClass.printBankObjectsInState(BankState.banksHashMap);
            }
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        UserModel userObj = UserState.usersHashMap.get(Integer.parseInt(inputData[1]));
        BankModel bankObj = BankState.banksHashMap.get(Integer.parseInt(inputData[2]));
        System.out.println(fields[1] + ": "
                + userObj
                + " \n" + fields[2] + ": "
                + bankObj);
        SavingsAccountService.insertNewAccountIntoDatabase(
                Integer.parseInt(inputData[1]),
                Integer.parseInt(inputData[2]), dbConnection);
        SavingsAccountService.updateAccountsHashmap(dbConnection);
        PrintInfoClass.printDividerLine();
    }

}
