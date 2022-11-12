package expenses_tracker.controller;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import expenses_tracker.data.BankState;
import expenses_tracker.models.UserModel;
import expenses_tracker.data.UserState;
import expenses_tracker.models.BankModel;
import expenses_tracker.services.BankService;
import expenses_tracker.services.UserService;



public class InterfaceController {

    static UserState userState;
    static boolean continueMainLoop = true;
    public static void main(String[] args) {

        userState = new UserState();


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

    public static String getMenuOption() {
        Scanner eventOptionScanner = new Scanner(System.in);
        String loopOption = eventOptionScanner.nextLine();
        PrintInfoClass.printDividerLine();
        System.out.println("You entered this menu option: " + loopOption);
        PrintInfoClass.printDividerLine();
        return loopOption;
    }

    public static void handleCreateInput(String[] fields, String submenuName) {
        String[] inputData = new String[fields.length];
        Scanner eventOptionScanner = new Scanner(System.in);
        for (int i = 0; i < fields.length; i++) {
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        System.out.println("The is the obj to be created: " + submenuName);
        if (submenuName == "User") {
            UserModel newUser = UserService.createNewUser(inputData);
            System.out.println("New user created: " + newUser.toString());
            System.out.println("In user state: " + UserState.users.get(UserState.users.size() - 1).toString());
        } else if (submenuName == "Bank") {
            BankModel newBank = BankService.createNewBank(inputData);
            System.out.println("New bank created: " + newBank.toString());
            System.out.println("In bank state: " + BankState.banks.get(BankState.banks.size() - 1).toString());
        } else {
            System.out.println("No more options");
        }
    }

    public static void handleDisplayOfObjects(String submenuName) {
        System.out.println("These are the objs to be displayed: " + submenuName);
        if (submenuName == "User") {
            System.out.println("Elements of Users ArrayList are:");
            int index = 0;
            for (UserModel user : UserState.users) {
                System.out.println("Index(" + index + ") User: " + user.toString());
                index++;
            }
        } else if (submenuName == "Bank") {
            System.out.println("Elements of Banks ArrayList are:");
            for (BankModel bank : BankState.banks) {
                System.out.println("Bank: " + bank.toString());
            }
        } else {
            System.out.println("No option");
        }
    }

    public static void handleMainMenuInput(String menuOptionInput) {
        if (Objects.equals(menuOptionInput, "1")) {
            System.out.println("User Info Menu");
            String [] fields = UserModel.getModelFields();
            PrintInfoClass.printSubMenuOptionPrompt("User");
            String subMenuOption = getMenuOption();
            handleSubMenuInput(subMenuOption, "User", fields);
        } else if (Objects.equals(menuOptionInput, "2")) {
            System.out.println("Savings Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Savings");
            PrintInfoClass.printDividerLine();
        } else if (Objects.equals(menuOptionInput, "3")) {
            System.out.println("Banks Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Banks");
            String [] fields = BankModel.getModelFields();
            String subMenuOption = getMenuOption();
            //handleMainMenuInput(menuOptionInput);
            handleSubMenuInput(subMenuOption, "Bank", fields);
            PrintInfoClass.printDividerLine();
        } else if (Objects.equals(menuOptionInput, "4")) {
            System.out.println("Expenses Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Expenses");
            PrintInfoClass.printDividerLine();
        } else if (Objects.equals(menuOptionInput, "5")) {
            System.out.println("Financial Goal Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Financial Goals");
            PrintInfoClass.printDividerLine();
        }else {
            System.out.println("Reports Menu");
        }
    }

    public static void handleSubMenuInput(
            String subMenuOptionInput, String submenuName,
            String[] fields
        ) {
        System.out.println("These are the fields:");
        for (String field : fields) {
            System.out.print(" " + field);
        }
        System.out.println("\n");
        if (Objects.equals(subMenuOptionInput, "1")) {
            System.out.println("Create " +submenuName + " Menu");
            handleCreateInput(fields, submenuName);
        } else if (Objects.equals(subMenuOptionInput, "2")) {
            System.out.println("Update " +submenuName + " Menu");
            handleUpdateInput(fields, submenuName);
        } else if (Objects.equals(subMenuOptionInput, "3")) {
            System.out.println("Delete " +submenuName + " Menu");
        } else {
            System.out.println("Display " +submenuName + " Menu");
            handleDisplayOfObjects(submenuName);
        }
    }

    public static void handleUpdateInput(String[] fields, String submenuName) {
        String[] inputData = new String[fields.length];
        Scanner eventOptionScanner = new Scanner(System.in);
        handleDisplayOfObjects(submenuName);
        String userIdInput = eventOptionScanner.nextLine();
        System.out.println("Which user would you like to update (enter id)?");
        for (int i = 1; i < fields.length; i++) {
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        System.out.println("The is the obj to be updated: " + submenuName);
        if (submenuName == "User") {
            int updatedIndex = Integer.parseInt(userIdInput);

            UserModel updatedUser = UserService.updateExistingUser(inputData, UserState.users.get(updatedIndex));
            System.out.println("New user created: " + updatedUser.toString());
            System.out.println("In user state: " + UserState.users.get(updatedIndex));

        } else if (submenuName == "Bank") {
            BankModel newBank = BankService.createNewBank(inputData);
            System.out.println("New bank created: " + newBank.toString());
            System.out.println("In bank state: " + BankState.banks.get(BankState.banks.size() - 1).toString());
        } else {
            System.out.println("No more options");
        }
    }
}
