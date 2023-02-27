package ticketbox.util;

import ticketbox.payment.Receipt;

import java.util.HashMap;

public class Statistics {
    public static double sum(HashMap<Integer, Receipt>map){
        double sum = 0;
        for(Receipt r : map.values()){
            sum += r.getAmount();
        }
        return sum;
    }
}
