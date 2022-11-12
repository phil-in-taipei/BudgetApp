package expenses_tracker.data;
import expenses_tracker.models.UserModel;

import java.util.ArrayList;

public class UserState {

    public static ArrayList<UserModel> users = new ArrayList<UserModel>();;

    public static void main(String[] args) {
        UserModel newUser =  new UserModel(1, "Joe", "Blow", "email.com");
        users.add(newUser);
    }

}
