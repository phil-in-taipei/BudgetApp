package expenses_tracker.data;
import expenses_tracker.models.UserModel;

import java.util.HashMap;

public class UserState {

    public static HashMap<Integer, UserModel> usersHashMap = new HashMap();

    public static void main(String[] args) {
        UserModel newUser =  new UserModel(1, "Joe", "Blow", "email.com");
        usersHashMap.put(newUser.getId(), newUser);
    }

}
