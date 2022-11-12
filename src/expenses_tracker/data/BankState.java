package expenses_tracker.data;

import expenses_tracker.models.BankModel;

import java.util.ArrayList;
import java.util.HashMap;

public class BankState {
    public static ArrayList<BankModel> banks = new ArrayList<BankModel>();

    public static HashMap<Integer, BankModel> banksHashMap = new HashMap();
}
