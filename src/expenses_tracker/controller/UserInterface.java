package expenses_tracker.controller;

import expenses_tracker.data.UserState;
import expenses_tracker.models.UserModel;
import expenses_tracker.services.UserService;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;


public class UserInterface {
    public static void handleCreateUserInput(
            String[] fields, String submenuName, String[] inputData, Connection dbConnection)
            throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        for (int i = 1; i < fields.length; i++) {
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        UserService.insertUserIntoDatabase(inputData[1], inputData[2],
                inputData[3], dbConnection);
        //PrintInfoClass.printDividerLine();
        UserService.updateUserHashmap(dbConnection);
        PrintInfoClass.printDividerLine();
    }

    public static void handleDeleteUserInput(Connection dbConnection)
            throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        System.out.println("Which user would you like to delete (enter user id)?");
        PrintInfoClass.printDividerLine();
        String userIdInput = eventOptionScanner.nextLine();
        int deleteIndex = Integer.parseInt(userIdInput);
        PrintInfoClass.printDividerLine();
        System.out.println("Are you sure that you want to delete "
                + UserState.usersHashMap.get(deleteIndex) + " ?");
        PrintInfoClass.printDividerLine();
        System.out.println("Enter 'y' or 'n'");
        PrintInfoClass.printDividerLine();
        String confirmationInput = eventOptionScanner.nextLine();
        if (confirmationInput.equalsIgnoreCase("y")) {
            System.out.println("Deleting "
                    + UserState.usersHashMap.get(deleteIndex));
            UserService.deleteUser(deleteIndex, dbConnection);
            UserService.deleteUser(deleteIndex, dbConnection);
        } else {
            System.out.println("Deletion cancelled!");
        }
    }

    public static void handleUpdateUserInput(
            String[] fields, String submenuName, String[] inputData, Connection dbConnection)
            throws SQLException {
        Scanner eventOptionScanner = new Scanner(System.in);
        System.out.println("Which user would you like to update (enter user id)?");
        PrintInfoClass.printDividerLine();
        String userIdInput = eventOptionScanner.nextLine();
        int updatedIndex = Integer.parseInt(userIdInput);
        for (int i = 1; i < fields.length; i++) {
            PrintInfoClass.printDividerLine();
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        UserModel updatedUser = UserService.updateExistingUser(inputData, updatedIndex);
        UserService.updateExistingUserInDatabase(inputData, updatedIndex, dbConnection);
        PrintInfoClass.printDividerLine();
        System.out.println("User updated: " + updatedUser.toString());
        PrintInfoClass.printDividerLine();
        System.out.println("In user state: " + UserState.usersHashMap.get(updatedUser.getId()));
    }
}
