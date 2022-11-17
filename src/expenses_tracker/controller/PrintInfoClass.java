package expenses_tracker.controller;

import expenses_tracker.models.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PrintInfoClass {

    static String BLACK_TEXT = "\u001B[30m";
    static String WHITE_BACKGROUND = "\u001B[47m";
    static String STANDARD_FORMAT = "\u001B[0m";

    public static void printBankObjectsInState(HashMap<Integer, BankModel> banksHashMap) {
        //System.out.println("Each element (key and value) in the HashMap using entries");
        PrintInfoClass.printDividerLine();
        Set entries = banksHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry bankObj = (Map.Entry)iterator.next();
            System.out.println("The key (bank id) is: "+ bankObj.getKey()
                    + " and the value (bank obj) is: " + bankObj.getValue());
        }
        PrintInfoClass.printDividerLine();
    }

    public static void printCreatePrompt(String fieldName, String submenuName) {
        //System.out.println("Create a new " + submenuName);
        //printDividerLine();
        System.out.println(
                "Please enter a " + fieldName
        );
        printDividerLine();
    }

    public static void printDepositObjectsInState(
            HashMap<Integer, DepositModel> depositModelHashMap) {
        PrintInfoClass.printDividerLine();
        Set entries = depositModelHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry userObj = (Map.Entry)iterator.next();
            System.out.println(userObj.getValue());
        }
        PrintInfoClass.printDividerLine();
    }

    public static void printDividerLine() {
        System.out.println("-----------------------------------------------------------------------------");
    }

    public static void printIncomeObjectsInState(
            HashMap<Integer, IncomeSourceModel> integerIncomeSourceModelHashMap) {
        PrintInfoClass.printDividerLine();
        Set entries = integerIncomeSourceModelHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry userObj = (Map.Entry)iterator.next();
            System.out.println(userObj.getValue());
        }
        PrintInfoClass.printDividerLine();
    }

    public static void printIntro(String exerciseName) {
        printDividerLine();
        System.out.println(
                BLACK_TEXT + WHITE_BACKGROUND +
                        " -- Welcome to the "
                        + exerciseName.toUpperCase()
                        + " Menu -- "
                        + STANDARD_FORMAT
        );
    }

    public static void printExit(String exerciseName) {
        printDividerLine();
        System.out.println(
                BLACK_TEXT + WHITE_BACKGROUND +
                        " -- Now Exiting the "
                        + exerciseName.toUpperCase()
                        + "  Menu -- "
                        + STANDARD_FORMAT
        );
    }

    public static void printMainMenuOptionPrompt() {
        printDividerLine();
        System.out.println("Please input menu option:");
        printDividerLine();
        System.out.println(
                        "1) User Information"
                        + "\n2) Savings Accounts"
                        + "\n3) Banks"
                        + "\n4) Withdraws"
                        + "\n5) Deposits"
                        + "\n6) Expenses"
                        + "\n7) Income Sources"
                        + "\n8) Reports"
                        + "\n9) Exit"
        );
        printDividerLine();
    }

    public static void printSubMenuOptionPrompt(String submenuName) {
        printDividerLine();
        System.out.println("Please input menu option:");
        printDividerLine();
        System.out.println(
                        "1) Create " + submenuName
                        + "\n2) Update " + submenuName
                        + "\n3) Delete " + submenuName
                        + "\n4) Display " + submenuName
                        + "\n5) Exit " + submenuName + " Menu"
        );
        printDividerLine();
    }

    public static void printUserObjectsInState(HashMap<Integer, UserModel> usersHashMap) {
        //System.out.println("Each element (key and value) in the HashMap using entries");
        PrintInfoClass.printDividerLine();
        Set entries = usersHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry userObj = (Map.Entry)iterator.next();
            System.out.println("The key (id) is: "+ userObj.getKey()
                    + " and the value is: " + userObj.getValue());
        }
        PrintInfoClass.printDividerLine();
    }

    public static void printSavingsAccountObjectsInState(
            HashMap<Integer, SavingsAccountModel> savingsAccountModelHashMap) {
        PrintInfoClass.printDividerLine();
        Set entries = savingsAccountModelHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry userObj = (Map.Entry)iterator.next();
            System.out.println(userObj.getValue());
        }
        PrintInfoClass.printDividerLine();
    }

    public static void printWithdrawObjectsInState(
            HashMap<Integer, WithdrawModel> withdrawModelHashMap) {
        PrintInfoClass.printDividerLine();
        Set entries = withdrawModelHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry userObj = (Map.Entry)iterator.next();
            System.out.println(userObj.getValue());
        }
        PrintInfoClass.printDividerLine();
    }
}
