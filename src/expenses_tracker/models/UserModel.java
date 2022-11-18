package expenses_tracker.models;

public class UserModel {
    int id;
    String givenName;
    String surname;
    String email;

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static String[] getModelFields () {
        return new String[]{"id", "Given Name", "Surname", "Email Address"};
    }

    public UserModel(int id, String givenName, String surname, String email) {
        this.id = id;
        this.givenName = givenName;
        this.surname = surname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
