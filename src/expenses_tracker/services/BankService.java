package expenses_tracker.services;
import expenses_tracker.models.BankModel;
import expenses_tracker.data.BankState;

public class BankService {
    public static BankModel createNewBank(String[] inputData) {
        BankModel newBank = new BankModel(
                Integer.parseInt(inputData[0]),
                inputData[1]
        );
        //BankState.banks.add(newBank);
        BankState.banksHashMap.put(newBank.getId(), newBank);
        return  newBank;
    }

    public static BankModel updateExistingBank(String[] inputData, int bankId) {
        BankModel updatedBank = BankState.banksHashMap.get(bankId);
        updatedBank.setBankName(inputData[1]);
        BankState.banksHashMap.replace(bankId, updatedBank);
        return updatedBank;
    }
}
