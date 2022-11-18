package expenses_tracker.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DepositModel {

    int id;

    int accountId;

    int incomeSourceId;

    BigDecimal depositAmount; // 2022-11-17 14:48:05

    Timestamp time;



    @Override
    public String toString() {
        return "DepositModel{" +
                "ID=" + id +
                ", Income Source Id=" + incomeSourceId +
                ", Deposit Amount=" + depositAmount +
                ", Time=" + time +
                ", Account Id=" + accountId +
                '}';
    }

    public DepositModel(
            int id,
            BigDecimal depositAmount,
            Timestamp time, int incomeSourceId, int accountId) {
        this.id = id;
        this.accountId = accountId;
        this.depositAmount = depositAmount;
        this.time = time;
        this.incomeSourceId = incomeSourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getIncomeSourceId() {
        return incomeSourceId;
    }

    public void setIncomeSourceId(int incomeSourceId) {
        this.incomeSourceId = incomeSourceId;
    }

    public static String[] getModelFields () {
        return new String[]{"id", "Deposit Amount",  "Income Source ID",  "Account ID"};
    }
}
