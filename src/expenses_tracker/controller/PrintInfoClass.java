package expenses_tracker.controller;

import expenses_tracker.models.UserModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PrintInfoClass {

    static String BLACK_TEXT = "\u001B[30m";
    static String WHITE_BACKGROUND = "\u001B[47m";
    static String STANDARD_FORMAT = "\u001B[0m";

    public static void printContinueUsingAppPrompt() {
        System.out.println(
                "Do you want to continue?"
                        + "\n'Y') Yes"
                        + "\n'N') No thanks, I'm done"
        );
    }

    public static void printCreatePrompt(String fieldName, String submenuName) {
        System.out.println("Create a new " + submenuName);
        printDividerLine();
        System.out.println(
                "Please enter a " + fieldName
        );
    }

    public static void printDividerLine() {
        System.out.println("-----------------------------------------------------------------------------");
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
        System.out.println(
                "Please input menu option:"
                        + "\n1) User Information"
                        + "\n2) Savings"
                        + "\n3) Banks"
                        + "\n4) Expenses"
                        + "\n5) Financial Goal"
                        + "\n6) Reports"
                        + "\n7) Exit"

        );
    }

    public static void printSubMenuOptionPrompt(String submenuName) {
        printDividerLine();
        System.out.println(
                "Please input menu option:"
                        + "\n1) Create " + submenuName
                        + "\n2) Update " + submenuName
                        + "\n3) Delete " + submenuName
                        + "\n4) Display " + submenuName

        );
    }

    public static void printUserObjectsInState(HashMap<Integer, UserModel> usersHashMap) {
        System.out.println("Each element (key and value) in the HashMap using entries");
        PrintInfoClass.printDividerLine();
        Set entries = usersHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            // get each Entry individually
            Map.Entry studentStatus = (Map.Entry)iterator.next();
            // print out the entry's key and value

            System.out.println("The key (student) is: "+ studentStatus.getKey()
                    + " and the value (status) is: " + studentStatus.getValue());
        }
    }
}
