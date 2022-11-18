package expenses_tracker.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class WithdrawModel {
    int id;

    int accountId;
    BigDecimal withdrawAmount;

    Timestamp time;


    @Override
    public String toString() {
        return "DepositModel{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", Withdraw Amount=" + withdrawAmount +
                ", time=" + time +
                '}';
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

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public WithdrawModel(
            int id, int accountId,
            BigDecimal withdrawAmount,
            Timestamp time) {
        this.id = id;
        this.accountId = accountId;
        this.withdrawAmount = withdrawAmount;
        this.time = time;
    }

    public static String[] getModelFields () {
        return new String[]{"id", "Account ID", "Withdraw Amount" };
    }
}
