package expenses_tracker.controller;

import java.util.Objects;
import java.util.Scanner;
import java.sql.*;
import java.text.ParseException;

import expenses_tracker.data.BankState;
import expenses_tracker.data.DatabaseConnection;
import expenses_tracker.models.UserModel;
import expenses_tracker.data.UserState;
import expenses_tracker.models.BankModel;
import expenses_tracker.services.BankService;
import expenses_tracker.services.UserService;



public class InterfaceController {
    public static Connection dbConnection;
    static boolean continueMainLoop = true;
    static boolean continueSubMenuLoop = true;
    public static void main(String[] args) throws SQLException {
        DatabaseConnection database = new DatabaseConnection();

        dbConnection = database.getConnection();
        while(continueMainLoop) {
            PrintInfoClass.printIntro("Budget Tracker Main Menu");
            PrintInfoClass.printMainMenuOptionPrompt();
            Scanner eventOptionScanner = new Scanner(System.in);
            String menuOptionInput = getMenuOption(eventOptionScanner);
            continueMainLoop = handleMainMenuInput(menuOptionInput);
            //System.out.println("Continue Main Loop: " + continueMainLoop);
        }
        PrintInfoClass.printExit("Budget Tracker Main Menu");
        database.closeDatabaseConnection();
    }

    public static String getMenuOption(Scanner eventOptionScanner) {
        String loopOption = eventOptionScanner.nextLine();
        PrintInfoClass.printDividerLine();
        System.out.println("You entered this menu option: " + loopOption);
        PrintInfoClass.printDividerLine();
        return loopOption;
    }

    public static void handleCreateBankInput(
            String[] fields, String submenuName, String[] inputData)
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

    public static void handleCreateInput(
            String[] fields, String submenuName) throws SQLException {
        String[] inputData = new String[fields.length];
        //System.out.println("The is the obj to be created: " + submenuName);
        PrintInfoClass.printDividerLine();
        if (submenuName == "User") {
            handleCreateUserInput(fields, submenuName, inputData);
        } else if (submenuName == "Bank") {
            handleCreateBankInput(fields, submenuName, inputData);
        } else {
            System.out.println("No more options");
        }
    }

    public static void handleCreateUserInput(
            String[] fields, String submenuName, String[] inputData)
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

    public static void handleDeleteBankInput()
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

    public static void handleDeleteInput(
            String submenuName) throws SQLException {
        handleDisplayOfObjects(submenuName);
        if (submenuName == "User") {
            handleDeleteUserInput();
        } else if (submenuName == "Bank") {
            handleDeleteBankInput();
        } else {
            System.out.println("No more options");
        }
    }

    public static void handleDeleteUserInput()
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

    public static void handleDisplayOfObjects(String submenuName) {
        //System.out.println("These are the objs to be displayed: " + submenuName);
        if (submenuName == "User") {
            PrintInfoClass.printUserObjectsInState(UserState.usersHashMap);
        } else if (submenuName == "Bank") {
            PrintInfoClass.printBankObjectsInState(BankState.banksHashMap);
        } else {
            System.out.println("No option");
        }
    }

    public static boolean handleMainMenuInput(String menuOptionInput) throws SQLException {
        //System.out.println("Calling Handle Main Menu Method with input: " + menuOptionInput);
        if (Objects.equals(menuOptionInput, "1")) {
            UserService.populateUserHashmap(dbConnection);
            continueSubMenuLoop = true;
            while(continueSubMenuLoop) {
                System.out.println("User Info Menu");
                String[] fields = UserModel.getModelFields();
                PrintInfoClass.printSubMenuOptionPrompt("Users");
                Scanner eventOptionScanner = new Scanner(System.in);
                String subMenuOption = getMenuOption(eventOptionScanner);
                continueSubMenuLoop = handleSubMenuInput(subMenuOption, "User", fields);
                //System.out.println("Continue submenu loop: " + continueSubMenuLoop);
            }
            return true;
        } else if (Objects.equals(menuOptionInput, "2")) {
            System.out.println("Savings Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Savings");
            return true;
        } else if (Objects.equals(menuOptionInput, "3")) {
            BankService.populateBankHashmap(dbConnection);
            continueSubMenuLoop = true;
            while(continueSubMenuLoop) {
                System.out.println("Banks Menu");
                PrintInfoClass.printSubMenuOptionPrompt("Banks");
                String[] fields = BankModel.getModelFields();
                Scanner eventOptionScanner = new Scanner(System.in);
                String subMenuOption = getMenuOption(eventOptionScanner);
                continueSubMenuLoop = handleSubMenuInput(subMenuOption, "Bank", fields);
                //System.out.println("Continue submenu loop: " + continueSubMenuLoop);
            }
            return true;
        } else if (Objects.equals(menuOptionInput, "4")) {
            System.out.println("Expenses Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Expenses");
            return true;
        } else if (Objects.equals(menuOptionInput, "5")) {
            System.out.println("Financial Goal Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Financial Goals");
            return true;
        } else if (Objects.equals(menuOptionInput, "6")) {
            System.out.println("Reports Menu");
            return true;
        }else {
            System.out.println("Exit Menu");
            return false;
        }
    }

    public static boolean handleSubMenuInput(
            String subMenuOptionInput, String submenuName,
            String[] fields
        ) throws SQLException {
        //System.out.println("These are the fields:");
        //for (String field : fields) {
        //    System.out.print(" " + field);
        //}
        //System.out.println("\n");
        if (Objects.equals(subMenuOptionInput, "1")) {
            System.out.println("Create " +submenuName + " Menu");
            handleCreateInput(fields, submenuName);
            return true;
        } else if (Objects.equals(subMenuOptionInput, "2")) {
            System.out.println("Update " +submenuName + " Menu");
            handleUpdateInput(fields, submenuName);
            return true;
        } else if (Objects.equals(subMenuOptionInput, "3")) {
            System.out.println("Delete " +submenuName + " Menu");
            handleDeleteInput(submenuName);
            return true;
        } else if (Objects.equals(subMenuOptionInput, "4")) {
            System.out.println("Display " +submenuName + " Menu");
            handleDisplayOfObjects(submenuName);
            return true;
        } else {
            System.out.println("Back to Main Menu");
            return false;
        }
    }

    public static void handleUpdateBankInput(
            String[] fields, String submenuName, String[] inputData)
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

    public static void handleUpdateInput(String[] fields, String submenuName) throws SQLException {
        String[] inputData = new String[fields.length];
        handleDisplayOfObjects(submenuName);
        //System.out.println("The is the obj to be updated: " + submenuName);
        if (submenuName == "User") {
            handleUpdateUserInput(fields, submenuName,inputData);
        } else if (submenuName == "Bank") {
            handleUpdateBankInput(fields, submenuName, inputData);
        } else {
            System.out.println("No more options");
        }
    }

    public static void handleUpdateUserInput(
            String[] fields, String submenuName, String[] inputData)
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
