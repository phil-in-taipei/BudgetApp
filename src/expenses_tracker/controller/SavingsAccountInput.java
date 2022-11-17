package expenses_tracker.controller;

import expenses_tracker.data.BankState;
import expenses_tracker.data.SavingsAccountState;
import expenses_tracker.data.UserState;
import expenses_tracker.models.BankModel;
import expenses_tracker.models.SavingsAccountModel;
import expenses_tracker.models.UserModel;
import expenses_tracker.services.BankService;
import expenses_tracker.services.SavingsAccountService;
import expenses_tracker.services.UserService;

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
            PrintInfoClass.printDividerLine();
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

    public static void handleDeleteAccountInput(Connection dbConnection)
            throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        System.out.println("Which savings account would you like to delete (enter account id)?");
        PrintInfoClass.printDividerLine();
        String accountIdInput = eventOptionScanner.nextLine();
        int deleteIndex = Integer.parseInt(accountIdInput);
        PrintInfoClass.printDividerLine();
        System.out.println("Are you sure that you want to delete "
                + SavingsAccountState.savingsAccountHashMap.get(deleteIndex) + " ?");
        PrintInfoClass.printDividerLine();
        System.out.println("Enter 'y' or 'n'");
        PrintInfoClass.printDividerLine();
        String confirmationInput = eventOptionScanner.nextLine();
        if (confirmationInput.equalsIgnoreCase("y")) {
            System.out.println("Deleting "
                    + SavingsAccountState.savingsAccountHashMap.get(deleteIndex));
            SavingsAccountService.deleteSavingsAccount(deleteIndex, dbConnection);
        } else {
            System.out.println("Deletion cancelled!");
        }
    }

    public static void handleUpdateAccountInput(
            String[] fields, String submenuName, String[] inputData, Connection dbConnection)
            throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        System.out.println("Which savings account would you like to update (enter account id)?");
        PrintInfoClass.printDividerLine();
        String userIdInput = eventOptionScanner.nextLine();
        int updatedIndex = Integer.parseInt(userIdInput);
        for (int i = 1; i < fields.length; i++) {
            PrintInfoClass.printDividerLine();
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
        SavingsAccountModel updatedAccount = SavingsAccountService.updateAccountsStateObject(
                inputData, updatedIndex);
        SavingsAccountService.updateAccountInDatabase(inputData, updatedIndex, dbConnection);
        PrintInfoClass.printDividerLine();
        System.out.println("Account updated: " + updatedAccount.toString());
        PrintInfoClass.printDividerLine();
        System.out.println(
                "In user state: "
                + SavingsAccountState.savingsAccountHashMap.get(
                        updatedAccount.getId()));
    }

}
