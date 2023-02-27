package ticketbox.payment;

public class Receipt {
    String client;
    String productName;
    String payMethod;
    String payNumber;
    double subTotalAmount;
    double totalAmount;
    Receipt(String client, String productName, String payMethod, String payNumber, double subTotalAmount,double totalAmount){
        this.client = client;
        this.productName = productName;
        this.payMethod = payMethod;
        this.payNumber = payNumber;
        this.subTotalAmount = subTotalAmount;
        this.totalAmount =   totalAmount;
    }
}
