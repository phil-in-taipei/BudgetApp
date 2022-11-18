package expenses_tracker.models;

import expenses_tracker.data.UserState;

public class IncomeSourceModel {

    int id;

    int userId;

    String incomeSourceName;


    public static String[] getModelFields () {

        return new String[]{"id", "User ID", "Income Source Name" };
    }

    public IncomeSourceModel(int id, int userId, String incomeSourceName) {
        this.id = id;
        this.userId = userId;
        this.incomeSourceName = incomeSourceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setIncomeSourceName(String incomeSourceName) {
        this.incomeSourceName = incomeSourceName;
    }

    public int getUserId() {
        return userId;
    }

    public String getIncomeSourceName() {
        return incomeSourceName;
    }

    @Override
    public String toString() {
        return "IncomeSourceModel{" +
                "id=" + id +
                ", user=" + UserState.usersHashMap.get(userId) +
                ", incomeSourceName='" + incomeSourceName + '\'' +
                '}';
    }
}
