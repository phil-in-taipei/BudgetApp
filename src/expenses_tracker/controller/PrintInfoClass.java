package expenses_tracker.controller;

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
                        + "\n3) Expenses"
                        + "\n4) Financial Goal"
                        + "\n5) Reports"

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
}
