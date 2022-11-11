package expenses_tracker.models;

public class BankModel {
    int id;
    String bankName;

    public static String[] getModelFields () {
        return new String[]{"id", "bankName"};
    }

    public BankModel(int id, String bankName) {
        this.id = id;
        this.bankName = bankName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
