package expenses_tracker.controller;

import java.util.Objects;
import java.util.Scanner;

import expenses_tracker.data.BankState;
import expenses_tracker.models.UserModel;
import expenses_tracker.data.UserState;
import expenses_tracker.models.BankModel;
import expenses_tracker.services.BankService;
import expenses_tracker.services.UserService;



public class InterfaceController {
    static boolean continueMainLoop = true;
    public static void main(String[] args) {

        while(continueMainLoop) {
            PrintInfoClass.printIntro("Budget Tracker Main Menu");

            continueMainLoop = true;
            InterfaceController controller = new InterfaceController();
            PrintInfoClass.printMainMenuOptionPrompt();

            String menuOptionInput = controller.getMenuOption();
            continueMainLoop = handleMainMenuInput(menuOptionInput);
            PrintInfoClass.printDividerLine();

        }
        PrintInfoClass.printExit("Budget Tracker Main Menu");
    }

    public static String getMenuOption() {
        Scanner eventOptionScanner = new Scanner(System.in);
        String loopOption = eventOptionScanner.nextLine();
        PrintInfoClass.printDividerLine();
        System.out.println("You entered this menu option: " + loopOption);
        PrintInfoClass.printDividerLine();
        return loopOption;
    }

    public static void handleCreateBankInput(String[] fields, String submenuName, String[] inputData) {
        Scanner eventOptionScanner = new Scanner(System.in);
        for (int i = 0; i < fields.length; i++) {
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        BankModel newBank = BankService.createNewBank(inputData);
        System.out.println("New bank created: " + newBank.toString());
        System.out.println("In bank state: " + BankState.banksHashMap.get(newBank.getId()));
    }

    public static void handleCreateInput(String[] fields, String submenuName) {
        String[] inputData = new String[fields.length];
        System.out.println("The is the obj to be created: " + submenuName);
        if (submenuName == "User") {
            handleCreateUserInput(fields, submenuName, inputData);
        } else if (submenuName == "Bank") {
            handleCreateBankInput(fields, submenuName, inputData);
        } else {
            System.out.println("No more options");
        }
    }

    public static void handleCreateUserInput(String[] fields, String submenuName, String[] inputData) {
        Scanner eventOptionScanner = new Scanner(System.in);
        for (int i = 0; i < fields.length; i++) {
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        UserModel newUser = UserService.createNewUser(inputData);
        System.out.println("New user created: " + newUser.toString());
        System.out.println("In user state: " + UserState.usersHashMap.get(newUser.getId()));
    }

    public static void handleDisplayOfObjects(String submenuName) {
        System.out.println("These are the objs to be displayed: " + submenuName);
        if (submenuName == "User") {
            PrintInfoClass.printUserObjectsInState(UserState.usersHashMap);
            //}
        } else if (submenuName == "Bank") {
            System.out.println("Elements of Banks ArrayList are:");
            for (BankModel bank : BankState.banks) {
                System.out.println("Bank: " + bank.toString());
            }
        } else {
            System.out.println("No option");
        }
    }

    public static boolean handleMainMenuInput(String menuOptionInput) {
        if (Objects.equals(menuOptionInput, "1")) {
            System.out.println("User Info Menu");
            String [] fields = UserModel.getModelFields();
            PrintInfoClass.printSubMenuOptionPrompt("User");
            String subMenuOption = getMenuOption();
            handleSubMenuInput(subMenuOption, "User", fields);
            return true;
        } else if (Objects.equals(menuOptionInput, "2")) {
            System.out.println("Savings Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Savings");
            PrintInfoClass.printDividerLine();
            return true;
        } else if (Objects.equals(menuOptionInput, "3")) {
            System.out.println("Banks Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Banks");
            String [] fields = BankModel.getModelFields();
            String subMenuOption = getMenuOption();
            handleSubMenuInput(subMenuOption, "Bank", fields);
            PrintInfoClass.printDividerLine();
            return true;
        } else if (Objects.equals(menuOptionInput, "4")) {
            System.out.println("Expenses Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Expenses");
            PrintInfoClass.printDividerLine();
            return true;
        } else if (Objects.equals(menuOptionInput, "5")) {
            System.out.println("Financial Goal Menu");
            PrintInfoClass.printSubMenuOptionPrompt("Financial Goals");
            PrintInfoClass.printDividerLine();
            return true;
        }else {
            System.out.println("Exit Menu");
            return false;
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
        handleDisplayOfObjects(submenuName);
        System.out.println("The is the obj to be updated: " + submenuName);
        if (submenuName == "User") {
            handleUpdateUserInput(fields, submenuName,inputData);
        } else if (submenuName == "Bank") {
            BankModel newBank = BankService.createNewBank(inputData);
            System.out.println("New bank created: " + newBank.toString());
            System.out.println("In bank state: " + BankState.banks.get(BankState.banks.size() - 1).toString());
        } else {
            System.out.println("No more options");
        }
    }

    public static void handleUpdateUserInput(String[] fields, String submenuName, String[] inputData) {
        Scanner eventOptionScanner = new Scanner(System.in);
        System.out.println("Which user would you like to update (enter user id)?");
        String userIdInput = eventOptionScanner.nextLine();
        int updatedIndex = Integer.parseInt(userIdInput);
        System.out.println("Which user would you like to update (enter id)?");
        for (int i = 1; i < fields.length; i++) {
            PrintInfoClass.printCreatePrompt(fields[i], submenuName);
            String fieldInput = eventOptionScanner.nextLine();
            inputData[i] = fieldInput;
        }
        UserModel updatedUser = UserService.updateExistingUser(inputData, updatedIndex);
        System.out.println("New user created: " + updatedUser.toString());
        System.out.println("In user state: " + UserState.usersHashMap.get(updatedUser.getId()));
    }
}
