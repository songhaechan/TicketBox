package ticketbox.payment;

import java.util.Objects;

public class Receipt {
    String client;
    String productName;
    String payMethod;
    String payNumber;
    double subTotalAmount;
    double totalAmount;
    public double getAmount(){
        return totalAmount;
    }
    Receipt(String client, String productName, String payMethod, String payNumber, double subTotalAmount,double totalAmount){
        this.client = client;
        this.productName = productName;
        this.payMethod = payMethod;
        this.payNumber = payNumber;
        this.subTotalAmount = subTotalAmount;
        this.totalAmount =   totalAmount;
    }
    @Override
    public String toString() {
        return "[Client:" + client +']'+'\n'+
                "[ProductName :" + productName+']' + '\n' +
                "[PayMethod :" + payMethod +']' +'\n' +
                "[PayNumber :" + payNumber + ']'+'\n' +
                "[SubTotalAmount :" + subTotalAmount+ ']'+'\n' +
                "[TotalAmount :" + totalAmount + ']';
    }

    public String toBackupString(){
        return ""+client+','+productName+','+payMethod
                +payNumber+','+subTotalAmount+','+totalAmount;
    }

}
