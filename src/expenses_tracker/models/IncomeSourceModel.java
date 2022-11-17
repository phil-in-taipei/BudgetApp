package expenses_tracker.models;

public class IncomeSourceModel {

    int id;

    int userId;

    String incomeSourceName;


    public static String[] getModelFields () {

        return new String[]{"id", "userId", "incomeSourceName" };
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
}
