package expenses_tracker.controller;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class InterfaceController {

    static boolean continueMainLoop = true;
    public static void main(String[] args) {

        PrintInfoClass.printIntro("Budget Tracker Main Menu");

        while(continueMainLoop) {
            PrintInfoClass.printContinueUsingAppPrompt();

            Scanner eventLoopScanner = new Scanner(System.in);
            String letsUseApp = eventLoopScanner.next();

            if (letsUseApp.equalsIgnoreCase("y")) {
                continueMainLoop = true;
                InterfaceController controller = new InterfaceController();
                PrintInfoClass.printMainMenuOptionPrompt();

                String menuOptionInput = controller.getMenuOption();
                handleMainMenuInput(menuOptionInput);
                PrintInfoClass.printDividerLine();

            }  else {
            continueMainLoop = false;
            PrintInfoClass.printExit("Budget Tracker Main Menu");
            }
        }
    }

    public String getMenuOption() {
        Scanner eventOptionScanner = new Scanner(System.in);
        String loopOption = eventOptionScanner.nextLine();
        PrintInfoClass.printDividerLine();
        System.out.println("You entered this menu option: " + loopOption);
        PrintInfoClass.printDividerLine();
        return loopOption;
    }

    public static void handleMainMenuInput(String menuOptionInput) {
        if (Objects.equals(menuOptionInput, "1")) {
            System.out.println("User Info Menu");
            PrintInfoClass.printSubMenuOptionPrompt("User");
            PrintInfoClass.printDividerLine();
        } else if (Objects.equals(menuOptionInput, "2")) {
            System.out.println("Savings Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Savings");
            PrintInfoClass.printDividerLine();
        } else if (Objects.equals(menuOptionInput, "3")) {
            System.out.println("Expenses Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Expenses");
            PrintInfoClass.printDividerLine();
        } else if (Objects.equals(menuOptionInput, "4")) {
            System.out.println("Financial Goal Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Financial Goals");
            PrintInfoClass.printDividerLine();
        }else {
            System.out.println("Reports Menu");
        }
    }

    public static void handleSubMenuInput(String menuOptionInput, String submenuName) {
        if (Objects.equals(menuOptionInput, "1")) {
            System.out.println("Create " +submenuName + " Menu");
            PrintInfoClass.printDividerLine();
        } else if (Objects.equals(menuOptionInput, "2")) {
            System.out.println("Update " +submenuName + " Menu");
            PrintInfoClass.printDividerLine();
        } else if (Objects.equals(menuOptionInput, "3")) {
            System.out.println("Delete " +submenuName + " Menu");
            PrintInfoClass.printDividerLine();
        } else {
            System.out.println("Display " +submenuName + " Menu");
            PrintInfoClass.printDividerLine();
        }
    }
}
