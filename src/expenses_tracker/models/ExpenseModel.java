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
