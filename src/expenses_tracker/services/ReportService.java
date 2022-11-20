package expenses_tracker.services;

import expenses_tracker.controller.PrintInfoClass;
import expenses_tracker.data.DepositState;
import expenses_tracker.data.IncomeSourceState;
import expenses_tracker.models.DepositModel;
import expenses_tracker.models.ExpenseModel;
import expenses_tracker.models.IncomeSourceModel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ReportService {

    public static void printExpenseObjectsInState(
            HashMap<Integer, ExpenseModel> expenseModelHashMap) {
        Set entries = expenseModelHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry userObj = (Map.Entry)iterator.next();
            System.out.println(userObj.getValue());
        }
        PrintInfoClass.printDividerLine();
    }

    public static void calculateTotalDeposits() {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        //PrintInfoClass.printDividerLine();
        //System.out.println("Deposits");
        //PrintInfoClass.printDividerLine();
        //System.out.println("TIME                      AMOUNT     SOURCE");
        PrintInfoClass.printDividerLine();
        Set entries = DepositState.depositHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry obj = (Map.Entry)iterator.next();
            DepositModel deposit = (DepositModel) obj.getValue();
            totalAmount = totalAmount.add(deposit.getDepositAmount());
            IncomeSourceModel source = IncomeSourceState.incomeHashMap.get(deposit.getIncomeSourceId());
            //System.out.println(deposit.getTime() + " "
            //        +  "    " + deposit.getDepositAmount()
            //        +  "    " + source.getIncomeSourceName());
        }
        //PrintInfoClass.printDividerLine();
        System.out.println(
                PrintInfoClass.getBlackText() +  PrintInfoClass.getWhiteBackground()
                + "Total Deposit Amount: " + totalAmount + PrintInfoClass.getStandardFormat()
        );
    }

    public static void calculateDepositsByIncomeSource(
            IncomeSourceModel incomeSource) {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        StringBuilder reportString = new StringBuilder(
                PrintInfoClass.getBlackText() +  PrintInfoClass.getWhiteBackground()
                + "Deposits from " + incomeSource.getIncomeSourceName()
                + PrintInfoClass.getStandardFormat()
                + "\nTIME                      AMOUNT");
       // PrintInfoClass.printDividerLine();
        Set entries = DepositState.depositHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry obj = (Map.Entry)iterator.next();
            DepositModel deposit = (DepositModel) obj.getValue();
            if (deposit.getIncomeSourceId() == incomeSource.getId()) {
                totalAmount = totalAmount.add(deposit.getDepositAmount());
                reportString.append("\n").append(
                        deposit.getTime()).append(" ").append("    ").append(deposit.getDepositAmount()
                );
            }
        }
        if (totalAmount.intValue() != 0) {
            PrintInfoClass.printDividerLine();
            System.out.println(reportString);
            PrintInfoClass.printDividerLine();
            System.out.println("Total Amount: " + totalAmount);
        }

    }

    public static void generateDepositsByIncomeSource() {
        Set entries = IncomeSourceState.incomeHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry obj = (Map.Entry)iterator.next();
            IncomeSourceModel source = (IncomeSourceModel) obj.getValue();
            calculateDepositsByIncomeSource(source);
        }
    }
}
