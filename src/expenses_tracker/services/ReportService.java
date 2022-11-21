package expenses_tracker.services;

import expenses_tracker.controller.PrintInfoClass;
import expenses_tracker.data.*;
import expenses_tracker.models.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ReportService {

    public static void analyzeMonthlyFinance(
            BigDecimal totalDeposits, BigDecimal totalWithdraws,
            BigDecimal totalSpending) {
        PrintInfoClass.printDividerLine();
        BigDecimal monthlyEarnings = totalDeposits.subtract(totalWithdraws);
        System.out.println(
                PrintInfoClass.getBlackText() + PrintInfoClass.getWhiteBackground()
                + "This month after withdraws, you made this amount: $"
                + monthlyEarnings
                + PrintInfoClass.getStandardFormat()
        );
        PrintInfoClass.printDividerLine();
        BigDecimal unaccountedSpending = totalWithdraws.subtract(totalSpending);
        System.out.println(
                PrintInfoClass.getBlackText() + PrintInfoClass.getWhiteBackground()
                + "This month, $" + totalDeposits
                + " in withdraws was spent on unaccounted expenses: $"
                + unaccountedSpending
                + PrintInfoClass.getStandardFormat()
        );
    }

    public static BigDecimal calculateTotalDeposits() {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        PrintInfoClass.printDividerLine();
        Set entries = DepositState.depositHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry obj = (Map.Entry)iterator.next();
            DepositModel deposit = (DepositModel) obj.getValue();
            totalAmount = totalAmount.add(deposit.getDepositAmount());
            IncomeSourceModel source = IncomeSourceState.incomeHashMap.get(deposit.getIncomeSourceId());
        }
        System.out.println(
                PrintInfoClass.getBlackText() +  PrintInfoClass.getWhiteBackground()
                + "Total Deposit Amount: " + totalAmount + PrintInfoClass.getStandardFormat()
        );
        return totalAmount;
    }

    public static void calculateAndDisplayTotalDeposits() {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        PrintInfoClass.printDividerLine();
        System.out.println("Deposits");
        PrintInfoClass.printDividerLine();
        System.out.println("TIME                      AMOUNT     SOURCE");
        PrintInfoClass.printDividerLine();
        Set entries = DepositState.depositHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry obj = (Map.Entry)iterator.next();
            DepositModel deposit = (DepositModel) obj.getValue();
            totalAmount = totalAmount.add(deposit.getDepositAmount());
            IncomeSourceModel source = IncomeSourceState.incomeHashMap.get(deposit.getIncomeSourceId());
            System.out.println(deposit.getTime() + " "
                    +  "    " + deposit.getDepositAmount()
                    +  "    " + source.getIncomeSourceName());
        }
        PrintInfoClass.printDividerLine();
        System.out.println(
                PrintInfoClass.getBlackText() +  PrintInfoClass.getWhiteBackground()
                        + "Total Deposit Amount: " + totalAmount + PrintInfoClass.getStandardFormat()
        );
    }

    public static BigDecimal calculateTotalSpending() {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        PrintInfoClass.printDividerLine();
        Set entries = SpendingRecordState.spendindRecordHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry obj = (Map.Entry)iterator.next();
            SpendingRecordModel spendingRecord = (SpendingRecordModel) obj.getValue();
            totalAmount = totalAmount.add(spendingRecord.getSpendingAmount());
            ExpenseModel expense = ExpenseState.expensesHashMap.get(spendingRecord.getExpenseId());

        }
        System.out.println(
                PrintInfoClass.getBlackText() +  PrintInfoClass.getWhiteBackground()
                        + "Total Spending Amount: " + totalAmount + PrintInfoClass.getStandardFormat()
        );
        return totalAmount;
    }

    public static void calculateAndDisplayTotalSpending() {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        PrintInfoClass.printDividerLine();
        System.out.println("Spending Record");
        PrintInfoClass.printDividerLine();
        System.out.println("TIME                      AMOUNT     EXPENSE");
        PrintInfoClass.printDividerLine();
        Set entries = SpendingRecordState.spendindRecordHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry obj = (Map.Entry)iterator.next();
            SpendingRecordModel spendingRecord = (SpendingRecordModel) obj.getValue();
            totalAmount = totalAmount.add(spendingRecord.getSpendingAmount());
            ExpenseModel expense = ExpenseState.expensesHashMap.get(spendingRecord.getExpenseId());
            System.out.println(spendingRecord.getTime() + " "
                    +  "    " + spendingRecord.getSpendingAmount()
                    +  "    " + expense.getExpenseName());
        }
        PrintInfoClass.printDividerLine();
        System.out.println(
                PrintInfoClass.getBlackText() +  PrintInfoClass.getWhiteBackground()
                        + "Total Spending Amount: " + totalAmount + PrintInfoClass.getStandardFormat()
        );
    }

    public static void calculateSpendingRecordByExpense(
            ExpenseModel expense) {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        StringBuilder reportString = new StringBuilder(
                PrintInfoClass.getBlackText() +  PrintInfoClass.getWhiteBackground()
                        + "Spending for " + expense.getExpenseName()
                        + PrintInfoClass.getStandardFormat()
                        + "\nTIME                      AMOUNT");
        // PrintInfoClass.printDividerLine();
        Set entries = SpendingRecordState.spendindRecordHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry obj = (Map.Entry)iterator.next();
            SpendingRecordModel spendingRecord = (SpendingRecordModel) obj.getValue();
            if (spendingRecord.getExpenseId() == expense.getId()) {
                totalAmount = totalAmount.add(spendingRecord.getSpendingAmount());
                reportString.append("\n").append(
                        spendingRecord.getTime()).append(" ").append("    ").append(
                                spendingRecord.getSpendingAmount()
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

    public static void generateSpendingRecordByExpense() {
        PrintInfoClass.printDividerLine();
        System.out.println("Spending Record");
        Set entries = ExpenseState.expensesHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry obj = (Map.Entry)iterator.next();
            ExpenseModel expense = (ExpenseModel) obj.getValue();
            calculateSpendingRecordByExpense(expense);
        }
    }

    public static BigDecimal calculateTotalWithdraws() {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        PrintInfoClass.printDividerLine();
        System.out.println("Withdraws");
        PrintInfoClass.printDividerLine();
        System.out.println("TIME                      AMOUNT");
        PrintInfoClass.printDividerLine();
        Set entries = WithdrawState.withdrawHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry obj = (Map.Entry)iterator.next();
            WithdrawModel withdraw = (WithdrawModel) obj.getValue();
            totalAmount = totalAmount.add(withdraw.getWithdrawAmount());
            System.out.println(withdraw.getTime() + " "
                   +  "    " + withdraw.getWithdrawAmount()
            );
        }
        PrintInfoClass.printDividerLine();
        System.out.println(
                PrintInfoClass.getBlackText() +  PrintInfoClass.getWhiteBackground()
                        + "Total Withdraw Amount: " + totalAmount + PrintInfoClass.getStandardFormat()
        );
        return totalAmount;
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
        PrintInfoClass.printDividerLine();
        System.out.println("Deposits");
        Set entries = IncomeSourceState.incomeHashMap.entrySet();
        Iterator iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry obj = (Map.Entry)iterator.next();
            IncomeSourceModel source = (IncomeSourceModel) obj.getValue();
            calculateDepositsByIncomeSource(source);
        }
    }
}
