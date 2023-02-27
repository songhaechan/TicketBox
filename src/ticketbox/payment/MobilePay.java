package ticketbox.payment;

public class MobilePay implements Pay {
    public static final double MOBILE_COMMISION = 0.2;
    public Receipt charge(String product, double amount, String name, String number){
        return new Receipt(name,product,"MobilePay",number,amount,amount*MOBILE_COMMISION);
    }
}
