package expenses_tracker.models;

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

    public SavingsAccountModel(int id, int userId, int bankId) {
        this.id = id;
        this.userId = userId;
        this.bankId = bankId;
        this.accountBalance = BigDecimal.valueOf(00.00);
    }

    public static String[] getModelFields () {
        return new String[]{"id", "userId", "bankId" };
    }





}
