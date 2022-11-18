package expenses_tracker.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SpendingRecordModel {
    int id;

    int expenseId;

    Timestamp time;
    BigDecimal spendingAmount;

    public int getId() {
        return id;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public static String[] getModelFields () {
        return new String[]{"id", "Expense ID", "Spending Amount" };
    }

    public Timestamp getTime() {
        return time;
    }

    public BigDecimal getSpendingAmount() {
        return spendingAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setSpendingAmount(BigDecimal spendingAmount) {
        this.spendingAmount = spendingAmount;
    }

    public SpendingRecordModel(
            int id, int expenseId, BigDecimal
            spendingAmount, Timestamp time) {
        this.id = id;
        this.expenseId = expenseId;
        this.spendingAmount = spendingAmount;
        this.time = time;
    }


}
