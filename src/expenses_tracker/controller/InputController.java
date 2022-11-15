package expenses_tracker.controller;

import java.util.Objects;
import java.util.Scanner;
import java.sql.*;

import expenses_tracker.data.BankState;
import expenses_tracker.data.DatabaseConnection;
import expenses_tracker.models.SavingsAccountModel;
import expenses_tracker.models.UserModel;
import expenses_tracker.data.UserState;
import expenses_tracker.models.BankModel;
import expenses_tracker.services.BankService;
import expenses_tracker.services.UserService;



public class InputController {
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
    
    public static void handleCreateInput(
            String[] fields, String submenuName) throws SQLException {
        String[] inputData = new String[fields.length];
        //System.out.println("The is the obj to be created: " + submenuName);
        PrintInfoClass.printDividerLine();
        if (submenuName == "User") { // Savings Accounts
            UserInput.handleCreateUserInput(fields, submenuName, inputData, dbConnection);
        } else if (submenuName == "Savings Accounts") {
            System.out.println("Call handle create accounts ....");
            //BankInput.handleCreateBankInput(fields, submenuName, inputData, dbConnection);
        } else if (submenuName == "Bank") {
            BankInput.handleCreateBankInput(fields, submenuName, inputData, dbConnection);
        } else {
            System.out.println("No more options");
        }
    }

    public static void handleDeleteInput(
            String submenuName) throws SQLException {
        handleDisplayOfObjects(submenuName);
        System.out.println("This is the submenu name: " + submenuName);
        if (submenuName == "User") {
            UserInput.handleDeleteUserInput(dbConnection);
        } else if (submenuName == "Savings Accounts") {
            System.out.println("Call handle deletion of accounts ....");
            //BankInput.handleCreateBankInput(fields, submenuName, inputData, dbConnection);
        } else if (submenuName == "Bank") {
            BankInput.handleDeleteBankInput(dbConnection);
        } else {
            System.out.println("No more options");
        }
    }

    public static void handleDisplayOfObjects(String submenuName) {
        //System.out.println("These are the objs to be displayed: " + submenuName);
        if (submenuName == "User") {
            PrintInfoClass.printUserObjectsInState(UserState.usersHashMap);
        } else if (submenuName == "Savings Accounts") {
            System.out.println("Call handle display of savings accounts ....");
            //BankInput.handleCreateBankInput(fields, submenuName, inputData, dbConnection);
        }  else if (submenuName == "Bank") {
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
            System.out.println("Savings Accounts Menu");
            UserModel currentUser = UserInput.handleGetUserInput(dbConnection);
            if (currentUser == null) {
                System.out.println("The user does not exist! Check Users for more details");
                return true;
            }
            System.out.println("You entered this user option: " + currentUser);
            while(continueSubMenuLoop) {
                System.out.println("Savings Accounts Menu");
                PrintInfoClass.printSubMenuOptionPrompt("Savings Accounts");
                String[] fields = SavingsAccountModel.getModelFields();
                Scanner eventOptionScanner = new Scanner(System.in);
                String subMenuOption = getMenuOption(eventOptionScanner);
                continueSubMenuLoop = handleSubMenuInput(subMenuOption, "Savings Accounts", fields);
                //System.out.println("Continue submenu loop: " + continueSubMenuLoop);
            }
            PrintInfoClass.printSubMenuOptionPrompt("Savings Accounts");
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
            System.out.println("Cash Withdraws Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Cash Withdraws");
            return true;
        } else if (Objects.equals(menuOptionInput, "5")) {
            System.out.println("Deposits Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Deposits");
            return true;
        }else if (Objects.equals(menuOptionInput, "6")) {
            System.out.println("Expenses Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Expenses");
            return true;
        } else if (Objects.equals(menuOptionInput, "7")) {
            System.out.println("Financial Goal Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Financial Goals");
            return true;
        } else if (Objects.equals(menuOptionInput, "8")) {
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

    public static void handleUpdateInput(String[] fields, String submenuName) throws SQLException {
        String[] inputData = new String[fields.length];
        handleDisplayOfObjects(submenuName);
        //System.out.println("The is the obj to be updated: " + submenuName);
        if (submenuName == "User") {
            UserInput.handleUpdateUserInput(fields, submenuName,inputData, dbConnection);
        } else if (submenuName == "Savings Accounts") {
            System.out.println("Call handle saving accounts update ....");
            //BankInput.handleCreateBankInput(fields, submenuName, inputData, dbConnection);
        } else if (submenuName == "Bank") {
            BankInput.handleUpdateBankInput(fields, submenuName, inputData, dbConnection);
        } else {
            System.out.println("No more options");
        }
    }
}
