package expenses_tracker.controller;

import java.lang.reflect.WildcardType;
import java.util.Objects;
import java.util.Scanner;
import java.sql.*;

import expenses_tracker.data.*;
import expenses_tracker.models.*;
import expenses_tracker.services.*;


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

    public static boolean confirmUser(String userID) {
        Scanner userConfimationScanner = new Scanner(System.in);
        PrintInfoClass.printDividerLine();
        System.out.println("The user is");
        PrintInfoClass.printDividerLine();
        System.out.println(UserState.usersHashMap.get(Integer.parseInt(userID)));
        PrintInfoClass.printDividerLine();
        System.out.println("Is this correct?  (Enter 'y' or 'n')");
        String userConfimation =  userConfimationScanner.nextLine();
        return userConfimation.equalsIgnoreCase("y");
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
        if (submenuName == "User") {
            UserInput.handleCreateUserInput(fields, submenuName, inputData, dbConnection);
        } else if (submenuName == "Savings Accounts") {
            SavingsAccountInput.handleCreateAccountInput(fields, submenuName, inputData, dbConnection);
        } else if (submenuName == "Deposits") {
            DepositsInput.handleCreateDepositInput(fields, submenuName, inputData, dbConnection);
        } else if (submenuName == "Withdraws") {
            WithdrawsInput.handleCreateWithdrawInput(fields, submenuName, inputData, dbConnection);
        } else if (submenuName == "Bank") {
            BankInput.handleCreateBankInput(fields, submenuName, inputData, dbConnection);
        } else if (submenuName == "Income Source") {
            IncomeSourceInput.handleCreateIncomeSourceInput(
                    fields, submenuName, inputData, dbConnection
            );
        } else if (submenuName == "Expenses") {
            ExpensesInput.handleCreateExpenseInput(
                    fields, submenuName, inputData, dbConnection
            );
        } else if (submenuName == "Spending Record") {
            SpendingRecordInput.handleSpendingRecordInput(
                    fields, submenuName, inputData, dbConnection
            );
        } else {
            System.out.println("There is no create option for: " + submenuName);
            PrintInfoClass.printDividerLine();
        }
    }

    public static void handleDeleteInput(
            String submenuName) throws SQLException {
        //handleDisplayOfObjects(submenuName);
        //System.out.println("This is the submenu name: " + submenuName);
        if (submenuName == "User") {
            handleDisplayOfObjects(submenuName);
            UserInput.handleDeleteUserInput(dbConnection);
        } else if (submenuName == "Savings Accounts") {
            handleDisplayOfObjects(submenuName);
            //System.out.println("Call handle deletion of accounts ....");
            SavingsAccountInput.handleDeleteAccountInput(dbConnection);
        } else if (submenuName == "Bank") {
            handleDisplayOfObjects(submenuName);
            BankInput.handleDeleteBankInput(dbConnection);
        } else {
            System.out.println("There is no delete option for " + submenuName);
            PrintInfoClass.printDividerLine();
        }
    }

    public static void handleDisplayOfObjects(String submenuName) {
        //System.out.println("These are the objs to be displayed: " + submenuName);
        if (submenuName == "User") {
            PrintInfoClass.printUserObjectsInState(UserState.usersHashMap);
        } else if (submenuName == "Savings Accounts") {
            PrintInfoClass.printSavingsAccountObjectsInState(
                    SavingsAccountState.savingsAccountHashMap
            );
        } else if (submenuName == "Bank") {
            PrintInfoClass.printBankObjectsInState(BankState.banksHashMap);
        } else if (submenuName == "Deposits") {
            PrintInfoClass.printDepositObjectsInState(DepositState.depositHashMap);
        } else if (submenuName == "Withdraws") {
            PrintInfoClass.printWithdrawObjectsInState(WithdrawState.withdrawHashMap);
        } else if (submenuName == "Income Source") {
            PrintInfoClass.printIncomeObjectsInState(IncomeSourceState.incomeHashMap);
        } else if (submenuName == "Expenses") {
            PrintInfoClass.printExpenseObjectsInState(ExpenseState.expensesHashMap);
        } else if (submenuName == "Spending Record") {
            PrintInfoClass.printSpendingRecordObjectsInState(
                    SpendingRecordState.spendindRecordHashMap);
        } else {
            System.out.println("There is no display option for " + submenuName);
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
            BankService.populateBankHashmap(dbConnection);
            SavingsAccountService.populateAccountsHashmap(dbConnection);
            UserService.populateUserHashmap(dbConnection);
            continueSubMenuLoop = true;
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
            BankService.populateBankHashmap(dbConnection);
            SavingsAccountService.populateAccountsHashmap(dbConnection);
            UserService.populateUserHashmap(dbConnection);
            WithdrawService.populateWithdrawHashmap(dbConnection);
            continueSubMenuLoop = true;
            while(continueSubMenuLoop) {
                System.out.println("Withdraws Menu");
                PrintInfoClass.printSubMenuOptionPrompt("Withdraws");
                String[] fields = WithdrawModel.getModelFields();
                Scanner eventOptionScanner = new Scanner(System.in);
                String subMenuOption = getMenuOption(eventOptionScanner);
                continueSubMenuLoop = handleSubMenuInput(subMenuOption, "Withdraws", fields);
            }
            return true;
        } else if (Objects.equals(menuOptionInput, "5")) {
            BankService.populateBankHashmap(dbConnection);
            SavingsAccountService.populateAccountsHashmap(dbConnection);
            UserService.populateUserHashmap(dbConnection);
            DepositService.populateDepositHashmap(dbConnection);
            IncomeSourceService.populateIncomeHashmap(dbConnection);
            continueSubMenuLoop = true;
            while(continueSubMenuLoop) {
                System.out.println("Deposits Menu");
                PrintInfoClass.printSubMenuOptionPrompt("Deposits");
                String[] fields = DepositModel.getModelFields();
                Scanner eventOptionScanner = new Scanner(System.in);
                String subMenuOption = getMenuOption(eventOptionScanner);
                continueSubMenuLoop = handleSubMenuInput(subMenuOption, "Deposits", fields);
                //System.out.println("Continue submenu loop: " + continueSubMenuLoop);
            }
            return true;
        } else if (Objects.equals(menuOptionInput, "6")) {
            UserService.populateUserHashmap(dbConnection);
            ExpenseService.populateExpenseHashmap(dbConnection);
            continueSubMenuLoop = true;
            while(continueSubMenuLoop) {
                System.out.println("Expenses Menu");
                PrintInfoClass.printSubMenuOptionPrompt("Expenses");
                String[] fields = ExpenseModel.getModelFields();
                Scanner eventOptionScanner = new Scanner(System.in);
                String subMenuOption = getMenuOption(eventOptionScanner);
                continueSubMenuLoop = handleSubMenuInput(subMenuOption, "Expenses", fields);
                //System.out.println("Continue submenu loop: " + continueSubMenuLoop);
            }

            return true;
        } else if (Objects.equals(menuOptionInput, "7")) {
            UserService.populateUserHashmap(dbConnection);
            IncomeSourceService.populateIncomeHashmap(dbConnection);
            continueSubMenuLoop = true;
            while(continueSubMenuLoop) {
                System.out.println("Income Source Menu");
                PrintInfoClass.printSubMenuOptionPrompt("Income Source");
                String[] fields = IncomeSourceModel.getModelFields();
                Scanner eventOptionScanner = new Scanner(System.in);
                String subMenuOption = getMenuOption(eventOptionScanner);
                continueSubMenuLoop = handleSubMenuInput(subMenuOption, "Income Source", fields);
                //System.out.println("Continue submenu loop: " + continueSubMenuLoop);
            }
            return true;
        } else if (Objects.equals(menuOptionInput, "8")) {
            //UserService.populateUserHashmap(dbConnection);
            ExpenseService.populateExpenseHashmap(dbConnection);
            SpendingRecordService.populateSpendingRecordHashmap(dbConnection);
            continueSubMenuLoop = true;
            while(continueSubMenuLoop) {
                System.out.println("Spending Record");
                PrintInfoClass.printSubMenuOptionPrompt("Spending Record");
                String[] fields = SpendingRecordModel.getModelFields();
                Scanner eventOptionScanner = new Scanner(System.in);
                String subMenuOption = getMenuOption(eventOptionScanner);
                continueSubMenuLoop = handleSubMenuInput(subMenuOption, "Spending Record", fields);
                //System.out.println("Continue submenu loop: " + continueSubMenuLoop);
            }
            return true;
        } else if (Objects.equals(menuOptionInput, "9")) {
            UserService.populateUserHashmap(dbConnection);
            BankService.populateBankHashmap(dbConnection);
            //continueSubMenuLoop = true;
            System.out.println("Welcome to Monthly Reports");
            Scanner reportsOptionScanner = new Scanner(System.in);
            PrintInfoClass.printUserObjectsInState(UserState.usersHashMap);
            System.out.println("Please enter your user ID:");
            PrintInfoClass.printDividerLine();
            String userID = reportsOptionScanner.nextLine();
            continueSubMenuLoop = confirmUser(userID);
            while(continueSubMenuLoop) {
                //continueSubMenuLoop = confirmUser(userID);
                PrintInfoClass.printDividerLine();
                System.out.println("Reports Menu");
                PrintInfoClass.printReportsMenuOptionPrompt();
                String reportsMenuOptionInput = reportsOptionScanner.nextLine();
                PrintInfoClass.printDividerLine();
                continueSubMenuLoop = handleReportsMenuInput(
                        reportsMenuOptionInput, userID
                );
            }
            return true;
        } else {
            System.out.println("Exit Menu");
            return false;
        }
    }

    public static String[] handleMonthAndYearInput() {
        Scanner monthAndYearScanner = new Scanner(System.in);
        System.out.println("Please enter the month:");
        PrintInfoClass.printDividerLine();
        String month = monthAndYearScanner.nextLine();
        PrintInfoClass.printDividerLine();
        System.out.println("Please enter the year:");
        PrintInfoClass.printDividerLine();
        String year = monthAndYearScanner.nextLine();
        return new String[]{month, year};
    }

    public static boolean handleReportsMenuInput(
            String reportsMenuOptionInput, String userID
    ) throws SQLException {
        if (Objects.equals(reportsMenuOptionInput, "1")) {
            String [] monthAndYear = handleMonthAndYearInput();
            PrintInfoClass.printDividerLine();
            System.out.println("Deposits for " + monthAndYear[0] + "/" + monthAndYear[1]);
            //handleCreateInput(fields, submenuName);
            return true;
        } else if (Objects.equals(reportsMenuOptionInput, "2")) {
            String [] monthAndYear = handleMonthAndYearInput();
            PrintInfoClass.printDividerLine();
            System.out.println("Withdraws for " + monthAndYear[0] + "/" + monthAndYear[1]);
            //handleUpdateInput(fields, submenuName);
            return true;
        } else if (Objects.equals(reportsMenuOptionInput, "3")) {
            String [] monthAndYear = handleMonthAndYearInput();
            PrintInfoClass.printDividerLine();
            System.out.println("Expenses for " + monthAndYear[0] + "/" + monthAndYear[1]);
            //handleDeleteInput(submenuName);
            return true;
        } else if (Objects.equals(reportsMenuOptionInput, "4")) {
            String [] monthAndYear = handleMonthAndYearInput();
            PrintInfoClass.printDividerLine();
            System.out.println("Analysis of " + monthAndYear[0] + "/" + monthAndYear[1]);
            //handleDisplayOfObjects(submenuName);
            return true;
        } else {
            System.out.println("Back to Main Menu");
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
            //System.out.println("Create " +submenuName + " Menu");
            handleCreateInput(fields, submenuName);
            return true;
        } else if (Objects.equals(subMenuOptionInput, "2")) {
            //System.out.println("Update " +submenuName + " Menu");
            handleUpdateInput(fields, submenuName);
            return true;
        } else if (Objects.equals(subMenuOptionInput, "3")) {
            //System.out.println("Delete " +submenuName + " Menu");
            handleDeleteInput(submenuName);
            return true;
        } else if (Objects.equals(subMenuOptionInput, "4")) {
            //System.out.println("Display " +submenuName + " Menu");
            handleDisplayOfObjects(submenuName);
            return true;
        } else {
            System.out.println("Back to Main Menu");
            return false;
        }
    }

    public static void handleUpdateInput(
            String[] fields, String submenuName) throws SQLException {
        String[] inputData = new String[fields.length];
        //handleDisplayOfObjects(submenuName);
        //System.out.println("The is the obj to be updated: " + submenuName);
        if (submenuName == "User") {
            handleDisplayOfObjects(submenuName);
            UserInput.handleUpdateUserInput(fields, submenuName,inputData, dbConnection);
        } else if (submenuName == "Savings Accounts") {
            handleDisplayOfObjects(submenuName);
            SavingsAccountInput.handleUpdateAccountInput(fields, submenuName, inputData, dbConnection);
        } else if (submenuName == "Bank") {
            handleDisplayOfObjects(submenuName);
            BankInput.handleUpdateBankInput(fields, submenuName, inputData, dbConnection);
        } else {
            System.out.println("There is no update option for " + submenuName);
            PrintInfoClass.printDividerLine();
        }
    }
}
