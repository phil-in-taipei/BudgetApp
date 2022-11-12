package expenses_tracker.services;
import expenses_tracker.models.UserModel;
import expenses_tracker.data.UserState;


public class UserService {


    public static UserModel createNewUser(String[] inputData) {
        for (String input : inputData) {
            System.out.println("This is the input data: " + input);
        }
        UserModel newUser = new UserModel(
                Integer.parseInt(inputData[0]),
                inputData[1], inputData[2],
                inputData[3]
        );
        UserState.users.add(newUser);
        UserState.usersHashMap.put(newUser.getId(), newUser);
        return  newUser;
    }

    public static UserModel updateExistingUser(String[] inputData, int userId) { //
        //for (String input : inputData) {
        //    System.out.println("This is the input data: " + input);
        //}
        UserModel updatedUser = UserState.usersHashMap.get(userId);
        updatedUser.setGivenName(inputData[1]);
        updatedUser.setSurname(inputData[2]);
        updatedUser.setEmail(inputData[3]);
        UserState.usersHashMap.replace(userId, updatedUser);
        //UserState.users.set(UserState.users.indexOf(userForUpdate), userForUpdate);
        return updatedUser;
    }
}
