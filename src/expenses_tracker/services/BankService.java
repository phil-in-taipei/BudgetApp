package expenses_tracker.services;
import expenses_tracker.models.BankModel;
import expenses_tracker.data.BankState;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankService {

    static Statement statement = null;
    static ResultSet resultSetBanks = null;
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

    public static void populateBankHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        // Result set get the result of the SQL query
        resultSetBanks = statement
                .executeQuery("select * from expense_tracker.bank");
        while (resultSetBanks.next()) {
            int id = resultSetBanks.getInt("bank_id");
            String bankName = resultSetBanks.getString("bank_name");
            String[] inputData = { String.valueOf(id), bankName };
            createNewBank(inputData);
        }
    }
}
