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
        return  newUser;
    }

    public static UserModel updateExistingUser(String[] inputData, UserModel userForUpdate) {
        for (String input : inputData) {
            System.out.println("This is the input data: " + input);
        }
        UserModel updatedUser = new UserModel(
                UserState.users.indexOf(userForUpdate), //Integer.parseInt(inputData[0])
                inputData[1], inputData[2],
                inputData[3]
        );
        UserState.users.set(UserState.users.indexOf(userForUpdate), userForUpdate);
        return updatedUser;
    }
}
