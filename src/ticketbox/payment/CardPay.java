package ticketbox.payment;

public class CardPay implements Pay{
    public static final double CARD_COMMISION = 0.15;
    public Receipt charge(String product, double amount, String name, String number){
        return new Receipt(name,product,"CardPay",number,amount,amount*CARD_COMMISION);
    }
}
