package expenses_tracker.services;
import expenses_tracker.data.UserState;
import expenses_tracker.models.BankModel;
import expenses_tracker.data.BankState;

public class BankService {
    public static BankModel createNewBank(String[] inputData) {
        for (String input : inputData) {
            System.out.println("This is the input data: " + input);
        }
        BankModel newBank = new BankModel(
                Integer.parseInt(inputData[0]),
                inputData[1]
        );
        BankState.banks.add(newBank);
        BankState.banksHashMap.put(newBank.getId(), newBank);
        return  newBank;
    }
}
