package expenses_tracker.services;
import expenses_tracker.data.UserState;
import expenses_tracker.models.BankModel;
import expenses_tracker.data.BankState;

import java.sql.*;

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

    public static void deleteBank(int bankId, Connection dbConnection) {
        String sql = "DELETE FROM expense_tracker.bank " +
                "WHERE bank_id = ? ";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setInt(1, bankId);
            BankState.banksHashMap.remove(bankId);
            System.out.println(ps);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
    }

    public static void insertBankIntoDatabase(
            String bankName, Connection dbConnection)  throws SQLException {

        String sql = "INSERT INTO expense_tracker.bank " +
                "(bank_name)" +
                "VALUES (?);";

        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);

            ps.setString(1, bankName);

            System.out.println(ps);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static void updateBankHashmap(Connection dbConnection) throws SQLException {
        statement = dbConnection.createStatement();
        // Result set get the result of the SQL query
        resultSetBanks = statement
                .executeQuery("select * from expense_tracker.bank ORDER BY bank_id DESC LIMIT 1");
        while (resultSetBanks.next()) {
            int id = resultSetBanks.getInt("bank_id");
            String bankName = resultSetBanks.getString("bank_name");
            String[] inputData = { String.valueOf(id), bankName };
            createNewBank(inputData);
        }
    }
    public static void updateExistingBankInDatabase(
            String[] inputData,
            int bankId,
            Connection dbConnection) throws SQLException {
        String sql = "UPDATE expense_tracker.bank SET bank_name = ?"
                + "WHERE bank_id = ? ";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setString(1, inputData[1]);
            ps.setInt(2, bankId);
            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
