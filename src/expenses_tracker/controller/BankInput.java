package expenses_tracker.controller;

import expenses_tracker.data.BankState;
import expenses_tracker.models.BankModel;
import expenses_tracker.services.BankService;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;

public class BankInput {

    public static void handleCreateBankInput(
            String[] fields, String submenuName, String[] inputData, Connection dbConnection)
            throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        for (int i = 1; i < fields.length; i++) {
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        BankService.insertBankIntoDatabase(inputData[1], dbConnection);
        BankService.updateBankHashmap(dbConnection);
        PrintInfoClass.printDividerLine();
    }

    public static void handleDeleteBankInput(Connection dbConnection)
            throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        System.out.println("Which bank would you like to delete (enter bank id)?");
        PrintInfoClass.printDividerLine();
        String userIdInput = eventOptionScanner.nextLine();
        int deleteIndex = Integer.parseInt(userIdInput);
        PrintInfoClass.printDividerLine();
        System.out.println("Are you sure that you want to delete "
                + BankState.banksHashMap.get(deleteIndex) + " ?");
        PrintInfoClass.printDividerLine();
        System.out.println("Enter 'y' or 'n'");
        PrintInfoClass.printDividerLine();
        String confirmationInput = eventOptionScanner.nextLine();
        if (confirmationInput.equalsIgnoreCase("y")) {
            System.out.println("Deleting "
                    + BankState.banksHashMap.get(deleteIndex));
            BankService.deleteBank(deleteIndex, dbConnection);
            BankService.deleteBank(deleteIndex, dbConnection);
        } else {
            System.out.println("Deletion cancelled!");
        }
    }

    public static void handleUpdateBankInput(
            String[] fields, String submenuName, String[] inputData, Connection dbConnection)
            throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        System.out.println("Which bank would you like to update (enter bank id)?");
        PrintInfoClass.printDividerLine();
        String userIdInput = eventOptionScanner.nextLine();
        int updatedIndex = Integer.parseInt(userIdInput);
        for (int i = 1; i < fields.length; i++) {
            PrintInfoClass.printDividerLine();
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        BankModel updatedBank = BankService.updateExistingBank(inputData, updatedIndex);
        BankService.updateExistingBankInDatabase(inputData, updatedIndex, dbConnection);
        PrintInfoClass.printDividerLine();
        System.out.println("Bank updated: " + updatedBank.toString());
        PrintInfoClass.printDividerLine();
        System.out.println("Bank state updated: " + BankState.banksHashMap.get(updatedBank.getId()));
        PrintInfoClass.printDividerLine();
    }
}
