package expenses_tracker.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class DepositModel {

    int id;

    int accountId;
    BigDecimal depositAmount; // 2022-11-17 14:48:05

    Timestamp time;



    @Override
    public String toString() {
        return "DepositModel{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", depositAmount=" + depositAmount +
                ", time=" + time +
                '}';
    }

    public DepositModel(
            int id, int accountId,
            BigDecimal depositAmount,
            Timestamp time) {
        this.id = id;
        this.accountId = accountId;
        this.depositAmount = depositAmount;
        this.time = time;
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

    public static String[] getModelFields () {
        return new String[]{"id", "accountId", "depositAmount" };
    }
}
