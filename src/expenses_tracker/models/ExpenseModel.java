package expenses_tracker.models;

public class ExpenseModel {

    int id;

    String expenseName;

    int userId;

    public ExpenseModel(int id, String expenseName, int userId) {
        this.id = id;
        this.expenseName = expenseName;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    @Override
    public String toString() {
        return "ExpenseModel{" +
                "id=" + id +
                ", expenseName='" + expenseName + '\'' +
                ", userId=" + userId +
                '}';
    }

    public static String[] getModelFields () {

        return new String[]{"id", "User ID", "Expense Name" };
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
