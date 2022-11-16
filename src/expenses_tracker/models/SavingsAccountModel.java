package expenses_tracker.models;

import expenses_tracker.data.UserState;
import expenses_tracker.data.BankState;

import java.math.BigDecimal;

public class SavingsAccountModel {

    int id;

    int bankId;
    BigDecimal accountBalance;

    int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SavingsAccountModel{id=" + id + "," + "bankId="
                + BankState.banksHashMap.get(bankId) + "," +
                "accountBalance=" + accountBalance + "," +
                "\n     userId=" + UserState.usersHashMap.get(userId) +
                "\n  }";
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public SavingsAccountModel(int id, int userId, int bankId, BigDecimal balance) {
        this.id = id;
        this.userId = userId;
        this.bankId = bankId;
        this.accountBalance = balance;
    }

    public static String[] getModelFields () {
        return new String[]{"id", "userId", "bankId" };
    }





}
