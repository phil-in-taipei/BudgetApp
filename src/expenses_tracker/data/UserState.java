package expenses_tracker.data;
import expenses_tracker.models.UserModel;

import java.util.ArrayList;
import java.util.HashMap;

public class UserState {

    public static ArrayList<UserModel> users = new ArrayList<UserModel>();
    public static HashMap<Integer, UserModel> usersHashMap = new HashMap();

    public static void main(String[] args) {
        UserModel newUser =  new UserModel(1, "Joe", "Blow", "email.com");
        users.add(newUser);

        usersHashMap.put(newUser.getId(), newUser);

    }

}
