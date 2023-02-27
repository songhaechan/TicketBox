package ticketbox.payment;

public class MovieTicket {
    public static final char SEAT_EMPTY_MARK                = '-';
    public static final char SEAT_RESERVED_MARK             = 'R';
    public static final char SEAT_PAY_COMPLETION_MARK       = '$';

    private int row;
    private int col;
    private char status;
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public char getStatus(){
        return status;
    }
    public void setStatus(char status){
        this.status = status;
    }

    private int reservedId=0;
    public void setSeat(int row, int col){
        this.row = row;
        this.col = col;
    }
    public void setReservedId(int id){
        this.reservedId = id;
    }
    public int getReservedId(){
        return reservedId;
    }
}
