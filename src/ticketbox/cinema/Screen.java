package ticketbox.cinema;

import ticketbox.payment.*;

import javax.smartcardio.Card;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Screen {
    protected int ticketPrice;
    protected int rowMax;
    protected int colMax;
    protected String movieName;
    protected String movieStory;
    protected MovieTicket[][] seatArray;
    public abstract void showMovieInfo();
    Screen(String name, String story, int price, int row, int col){
        this.movieName = name;
        this.movieStory = story;
        this.ticketPrice = price;
        this.rowMax = row;
        this.colMax = col;
        this.seatArray = new MovieTicket[rowMax][colMax];
        for(int i=0; i<this.rowMax; i++) {
            for(int j=0; j<this.colMax; j++) {
                seatArray[i][j] = new MovieTicket();
                seatArray[i][j].setStatus(MovieTicket.SEAT_EMPTY_MARK);
            }
        }
    }
    public void showScreenMenu(){
        System.out.println("--------------------");
        System.out.println("-   영화 메뉴 - "+movieName+"    -");
        System.out.println("--------------------");
        System.out.println("1. 상영 영화 정보");
        System.out.println("2. 좌석 예약 현황");
        System.out.println("3. 좌석 예약 하기");
        System.out.println("4. 좌석 변경 하기");
        System.out.println("5. 좌석 결제 하기");
        System.out.println("6. 티켓 출력 하기");
        System.out.println("7. 메인 메뉴 이동");
    }
    public void showSeatMap(){
        System.out.println("        [ 좌석 예약 현황 ]");
        System.out.print("        ");
        for (int i = 0; i < colMax; i++) {
            System.out.print("[" + (i + 1) + "]");
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < rowMax; i++) {
            if(i!=9){
                System.out.print("[" + (i + 1) + "]     ");
            }else{
                System.out.print("[" + (i + 1) + "]    ");
            }

            for (int j = 0; j < colMax; j++) {
                System.out.print("[" + seatArray[i][j].getStatus() + "]");
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public void reserveTicket(){
        while (true) {
            Scanner scan = new Scanner(System.in);
            final int currentReservedId = 100;
            int row;
            int col;
            System.out.println("[ 좌석 예약 ]");
            System.out.print("좌석 행 번호 입력(1-"+this.rowMax+") :");
            row = scan.nextInt();
            if(row>rowMax || row<1){
                System.out.println("존재하지않는 행입니다.");
                continue;
            }
            System.out.print("좌석 열 번호 입력(1-"+this.colMax+") :");
            col = scan.nextInt();
            if(col>colMax || col<1){
                System.out.println("존재하지않는 열입니다.");
                continue;
            }
            if(seatArray[row-1][col-1].getReservedId() != 0){
                System.out.println("이미 (예약/구매)된 좌석입니다.");
                System.out.println("다시 입력해주세요.");
                continue;
            }
            seatArray[row-1][col-1].setSeat(row,col);
            seatArray[row-1][col-1].setReservedId(currentReservedId+(int)(Math.random()*(colMax*rowMax)+1));
            System.out.println("행["+row+"] 열["+col+"] "+seatArray[row-1][col-1].getReservedId()+"예약 번호로 예약되었습니다.");
            seatArray[row-1][col-1].setStatus(MovieTicket.SEAT_RESERVED_MARK);
            break;
        }
    }
    private MovieTicket checkReservedId(int id){
        for(int i=0; i<rowMax; i++) {
            for(int j=0; j<colMax; j++) {
                if(seatArray[i][j].getReservedId()==id){
                    return seatArray[i][j];
                }
            }
        }
        return null;
    }
    public void changeTicket(){
        while(true){
            Scanner scan = new Scanner(System.in);
            final int currentReservedId = 100;
            int row;
            int col;
            int num;
            System.out.println("[ 좌석 변경 ]");
            System.out.print("좌석 예약 번호 입력 :");
            num = scan.nextInt();
            MovieTicket tmp = checkReservedId(num);
            if(tmp == null) {
                System.out.println("예약 번호를 확인해주세요.");
                continue;
            }
            tmp.setStatus(MovieTicket.SEAT_EMPTY_MARK);
            System.out.print("좌석 행 번호 입력(1-"+this.rowMax+") :");
            row = scan.nextInt();
            if(row>rowMax || row<1){
                System.out.println("존재하지않는 행입니다.");
                continue;
            }
            System.out.print("좌석 열 번호 입력(1-"+this.colMax+") :");
            col = scan.nextInt();
            if(col>colMax || col<1){
                System.out.println("존재하지않는 열입니다.");
                continue;
            }
                seatArray[row-1][col-1].setSeat(row,col);
                seatArray[row-1][col-1].setStatus(MovieTicket.SEAT_RESERVED_MARK);
                System.out.println("예약번호"+num+"행["+row+"] 열["+col+"] 좌석으로 변경되었습니다.");
                break;
        }
    }

    private final HashMap<Integer, Receipt> receiptMap = new HashMap<Integer, Receipt>();
    public HashMap<Integer, Receipt> getMap(){
        return this.receiptMap;
    }
    public void payment(){
        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("[ 좌석 예약 결제 ]");
            System.out.print("예약 번호 입력:");
            int num = scan.nextInt();
            MovieTicket tmp = checkReservedId(num);
            if(tmp == null){
                System.out.println("예약 번호를 확인해주세요.");
            }
            System.out.println("--------------------");
            System.out.println("-   결제 방식 선택    -");
            System.out.println("--------------------");
            System.out.println("1. 은행 이체");
            System.out.println("2. 카드 결제");
            System.out.println("3. 모바일 결제");
            System.out.print("결제 방식 선택 :");
            int pay = scan.nextInt();
            String name;
            String number;
            double price;
            Receipt r;
            switch (pay) {
                case Pay.BANK_TRANSFER_PAYMENT:
                    System.out.println("[ 은행 이체 ]");
                    System.out.print("이름 입력: ");
                    name = scan.next();
                    System.out.print("은행 번호 입력(12자리수): ");
                    number = scan.next();
                    if(number.length()!=12){
                        System.out.println("은행 번호를 확인해주세요.");
                        return;
                    }
                    price = (ticketPrice
                            +ticketPrice*BankTransfer.BANK_TRANSFER_COMMISION);
                    System.out.println("은행 결제가 완료되었습니다. :"+price+"원");
                    BankTransfer bt = new BankTransfer();
                    r = bt.charge(movieName,price,name,number);
                    receiptMap.put(tmp.getReservedId(),r);
                    tmp.setStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK);
                    return;

                case Pay.CREDIT_CARD_PAYMENT:
                    System.out.println("[ 카드 결제 ]");
                    System.out.print("이름 입력: ");
                    name = scan.next();
                    System.out.print("카드 번호 입력(12자리수): ");
                    number = scan.next();
                    if(number.length()!=12){
                        System.out.println("카드 번호를 확인해주세요.");
                        return;
                    }
                    price = (ticketPrice
                            +ticketPrice* CardPay.CARD_COMMISION);
                    System.out.println("카드 결제가 완료되었습니다. :"+price+"원");
                    CardPay cp = new CardPay();
                    r = cp.charge(movieName,price,name,number);
                    receiptMap.put(tmp.getReservedId(),r);
                    tmp.setStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK);
                    return;

                case Pay.MOBILE_PHONE_PAYMENT:
                    System.out.println("[ 모바일 결제 ]");
                    System.out.print("이름 입력: ");
                    name = scan.next();
                    System.out.print("핸드폰 번호 입력(11자리수): ");
                    number = scan.next();
                    if(number.length()!=11){
                        System.out.println("휴대폰 번호를 확인해주세요.");
                        return;
                    }
                    price = (ticketPrice
                            +ticketPrice* MobilePay.MOBILE_COMMISION);
                    System.out.println("모바일 결제가 완료되었습니다. :"+price+"원");
                    MobilePay mp = new MobilePay();
                    r = mp.charge(movieName,price,name,number);
                    receiptMap.put(tmp.getReservedId(),r);
                    tmp.setStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK);
                    return;

                default:
                    System.out.println("결제 방식을 다시 입력해주세요.");
            }

        }
    }

    public void printTicket(){
        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("[ 좌석 티켓 출력 ]");
            System.out.print("예약 번호 입력 :");
            int num = scan.nextInt();
            if(checkReservedId(num) != null){
                Receipt r = receiptMap.get(num);
                System.out.println("--------------------");
                System.out.println("-      Receipt     -");
                System.out.println("--------------------");
                System.out.println(r.toString());
            }else{
                System.out.println("예약 번호를 확인해주세요.");
                return;
            }
        }
    }
}
