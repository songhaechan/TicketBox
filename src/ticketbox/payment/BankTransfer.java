package ticketbox.payment;

import ticketbox.cinema.Screen;

import java.util.Scanner;

public class BankTransfer implements Pay {
    public static final double BANK_TRANSFER_COMMISION = 0.1;
    public Receipt charge(String product, double amount, String name, String number){
        return new Receipt(name,product,"BankTransfer",number,amount,amount*BANK_TRANSFER_COMMISION);
    }
}
