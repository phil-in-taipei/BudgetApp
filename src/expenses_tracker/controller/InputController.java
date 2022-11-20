package expenses_tracker.controller;

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
            PrintInfoClass.printIntro("Spending Tracker Main Menu");
            PrintInfoClass.printMainMenuOptionPrompt();
            Scanner eventOptionScanner = new Scanner(System.in);
            String menuOptionInput = getMenuOption(eventOptionScanner);
            continueMainLoop = handleMainMenuInput(menuOptionInput);
        }
        PrintInfoClass.printExit("Spending Tracker Main Menu");
        database.closeDatabaseConnection();
    }

    public static void clearAllHashmaps() {
        BankState.banksHashMap.clear();
        DepositState.depositHashMap.clear();
        ExpenseState.expensesHashMap.clear();
        IncomeSourceState.incomeHashMap.clear();
        SavingsAccountState.savingsAccountHashMap.clear();
        SpendingRecordState.spendindRecordHashMap.clear();
        UserState.usersHashMap.clear();
        WithdrawState.withdrawHashMap.clear();
    }

    public static boolean confirmUser(String userID) {
        Scanner userConfimationScanner = new Scanner(System.in);
        PrintInfoClass.printDividerLine();
        System.out.println("The user is");
        PrintInfoClass.printDividerLine();
        System.out.println(UserState.usersHashMap.get(Integer.parseInt(userID)));
        PrintInfoClass.printDividerLine();
        System.out.println("Is this correct?  (Enter 'y' or 'n')");
        String userConfirmation =  userConfimationScanner.nextLine();
        return userConfirmation.equalsIgnoreCase("y");
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
        PrintInfoClass.printDividerLine();
        if (Objects.equals(submenuName, "User")) {
            UserInput.handleCreateUserInput(fields, submenuName, inputData, dbConnection);
        } else if (Objects.equals(submenuName, "Savings Accounts")) {
            SavingsAccountInput.handleCreateAccountInput(fields, submenuName, inputData, dbConnection);
        } else if (Objects.equals(submenuName, "Deposits")) {
            DepositsInput.handleCreateDepositInput(fields, submenuName, inputData, dbConnection);
        } else if (Objects.equals(submenuName, "Withdraws")) {
            WithdrawsInput.handleCreateWithdrawInput(fields, submenuName, inputData, dbConnection);
        } else if (Objects.equals(submenuName, "Bank")) {
            BankInput.handleCreateBankInput(fields, submenuName, inputData, dbConnection);
        } else if (Objects.equals(submenuName, "Income Source")) {
            IncomeSourceInput.handleCreateIncomeSourceInput(
                    fields, submenuName, inputData, dbConnection
            );
        } else if (Objects.equals(submenuName, "Expenses")) {
            ExpensesInput.handleCreateExpenseInput(
                    fields, submenuName, inputData, dbConnection
            );
        } else if (Objects.equals(submenuName, "Spending Record")) {
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
        if (Objects.equals(submenuName, "User")) {
            handleDisplayOfObjects(submenuName);
            UserInput.handleDeleteUserInput(dbConnection);
        } else if (Objects.equals(submenuName, "Savings Accounts")) {
            handleDisplayOfObjects(submenuName);
            SavingsAccountInput.handleDeleteAccountInput(dbConnection);
        } else if (Objects.equals(submenuName, "Bank")) {
            handleDisplayOfObjects(submenuName);
            BankInput.handleDeleteBankInput(dbConnection);
        } else {
            System.out.println("There is no delete option for " + submenuName);
            PrintInfoClass.printDividerLine();
        }
    }

    public static void handleDisplayOfObjects(String submenuName) {
        if (Objects.equals(submenuName, "User")) {
            PrintInfoClass.printUserObjectsInState(UserState.usersHashMap);
        } else if (Objects.equals(submenuName, "Savings Accounts")) {
            PrintInfoClass.printSavingsAccountObjectsInState(
                    SavingsAccountState.savingsAccountHashMap
            );
        } else if (Objects.equals(submenuName, "Bank")) {
            PrintInfoClass.printBankObjectsInState(BankState.banksHashMap);
        } else if (Objects.equals(submenuName, "Deposits")) {
            PrintInfoClass.printDepositObjectsInState(DepositState.depositHashMap);
        } else if (Objects.equals(submenuName, "Withdraws")) {
            PrintInfoClass.printWithdrawObjectsInState(WithdrawState.withdrawHashMap);
        } else if (Objects.equals(submenuName, "Income Source")) {
            PrintInfoClass.printIncomeObjectsInState(IncomeSourceState.incomeHashMap);
        } else if (Objects.equals(submenuName, "Expenses")) {
            PrintInfoClass.printExpenseObjectsInState(ExpenseState.expensesHashMap);
        } else if (Objects.equals(submenuName, "Spending Record")) {
            PrintInfoClass.printSpendingRecordObjectsInState(
                    SpendingRecordState.spendindRecordHashMap);
        } else {
            System.out.println("There is no display option for " + submenuName);
        }
    }

    public static boolean handleMainMenuInput(String menuOptionInput) throws SQLException {
        clearAllHashmaps();
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
            }
            return true;
        } else if (Objects.equals(menuOptionInput, "8")) {
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
            }
            return true;
        } else if (Objects.equals(menuOptionInput, "9")) {
            UserService.populateUserHashmap(dbConnection);
            BankService.populateBankHashmap(dbConnection);
            IncomeSourceService.populateIncomeHashmap(dbConnection);
            System.out.println("Welcome to Monthly Reports");
            PrintInfoClass.printDividerLine();
            Scanner reportsOptionScanner = new Scanner(System.in);
            PrintInfoClass.printUserObjectsInState(UserState.usersHashMap);
            System.out.println("Please enter your user ID:");
            PrintInfoClass.printDividerLine();
            String userID = reportsOptionScanner.nextLine();
            continueSubMenuLoop = confirmUser(userID);
            while(continueSubMenuLoop) {
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
            PrintInfoClass.printDividerLine();
            DepositService.populateDepositHashmap(userID, monthAndYear, dbConnection);
            PrintInfoClass.printDepositObjectsInState(DepositState.depositHashMap);
            return true;
        } else if (Objects.equals(reportsMenuOptionInput, "2")) {
            SavingsAccountService.populateAccountsHashmap(userID, dbConnection);
            String [] monthAndYear = handleMonthAndYearInput();
            PrintInfoClass.printDividerLine();
            System.out.println("Withdraws for " + monthAndYear[0] + "/" + monthAndYear[1]);
            PrintInfoClass.printDividerLine();
            WithdrawService.populateWithdrawHashmap(userID, monthAndYear, dbConnection);
            PrintInfoClass.printWithdrawObjectsInState(WithdrawState.withdrawHashMap);
            return true;
        } else if (Objects.equals(reportsMenuOptionInput, "3")) {
            ExpenseService.populateExpenseHashmap(userID, dbConnection);
            String [] monthAndYear = handleMonthAndYearInput();
            PrintInfoClass.printDividerLine();
            System.out.println("Expenses for " + monthAndYear[0] + "/" + monthAndYear[1]);
            PrintInfoClass.printDividerLine();
            SpendingRecordService.populateSpendingRecordHashmap(userID, monthAndYear, dbConnection);
            PrintInfoClass.printSpendingRecordObjectsInState(
                    SpendingRecordState.spendindRecordHashMap);
            return true;
        } else if (Objects.equals(reportsMenuOptionInput, "4")) {
            ExpenseService.populateExpenseHashmap(userID, dbConnection);
            String [] monthAndYear = handleMonthAndYearInput();
            PrintInfoClass.printDividerLine();
            System.out.println("Analysis of " + monthAndYear[0] + "/" + monthAndYear[1]);
            PrintInfoClass.printDividerLine();
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
        if (Objects.equals(subMenuOptionInput, "1")) {
            handleCreateInput(fields, submenuName);
            return true;
        } else if (Objects.equals(subMenuOptionInput, "2")) {
            handleUpdateInput(fields, submenuName);
            return true;
        } else if (Objects.equals(subMenuOptionInput, "3")) {
            handleDeleteInput(submenuName);
            return true;
        } else if (Objects.equals(subMenuOptionInput, "4")) {
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
        if (Objects.equals(submenuName, "User")) {
            handleDisplayOfObjects(submenuName);
            UserInput.handleUpdateUserInput(fields, submenuName,inputData, dbConnection);
        } else if (Objects.equals(submenuName, "Savings Accounts")) {
            handleDisplayOfObjects(submenuName);
            SavingsAccountInput.handleUpdateAccountInput(fields, submenuName, inputData, dbConnection);
        } else if (Objects.equals(submenuName, "Bank")) {
            handleDisplayOfObjects(submenuName);
            BankInput.handleUpdateBankInput(fields, submenuName, inputData, dbConnection);
        } else {
            System.out.println("There is no update option for " + submenuName);
            PrintInfoClass.printDividerLine();
        }
    }
}
