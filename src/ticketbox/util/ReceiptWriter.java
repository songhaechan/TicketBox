package ticketbox.util;

import ticketbox.cinema.FamilyScreen;
import ticketbox.payment.Receipt;

import java.util.HashMap;

public class ReceiptWriter {
    public void printConsole(HashMap<Integer, Receipt>map){
        for(Receipt receipt : map.values()){
            System.out.print(receipt.toBackupString());
            System.out.println();
        }
        System.out.println();
    }
}
