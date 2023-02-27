package ticketbox.main;

import ticketbox.cinema.AnimationScreen;
import ticketbox.cinema.FamilyScreen;
import ticketbox.cinema.PremiumScreen;
import ticketbox.cinema.Screen;
import ticketbox.payment.Receipt;
import ticketbox.util.ReceiptWriter;
import ticketbox.util.Statistics;

import java.util.HashMap;
import java.util.Scanner;

public class TicketBox {
    public static final String ADMIN_PASSWORD = "admin";
    private FamilyScreen familyScreen;
    private AnimationScreen animationScreen;
    private PremiumScreen premiumScreen;

    Scanner scanner = new Scanner(System.in);
    public void initScreen(){
        familyScreen = new FamilyScreen("A","A is...",8000,10,10);
        animationScreen = new AnimationScreen("B","B is...",10000,10,10);
        premiumScreen = new PremiumScreen("C","C is...",25000,5,5);
    }

    public Screen selectScreen(){
        int select;
        System.out.println("--------------------");
        System.out.println("-  상영관선택 메인메뉴  -");
        System.out.println("--------------------");
        System.out.println("1. 가족 영화 1관");
        System.out.println("2. 애니메이션 영화 2관");
        System.out.println("3. 프리미엄 영화 3관(커피,샌드위치 제공");
        System.out.println("9. 관리자 메뉴");
        System.out.println("선택(1~3,9)외 종료");
        System.out.print("상영관 선택:");
        select = scanner.nextInt();
        switch (select){
            case 1:
                return familyScreen;
            case 2:
                return animationScreen;
            case 3:
                return premiumScreen;
            case 9:
                managerMode();
                break;
            default:
                return null;
        }
        return null;
    }
    void managerMode(){
        System.out.print("암호 입력:");
        String password = scanner.next();
        if(password.equals(ADMIN_PASSWORD)){
            System.out.println("---------------------------");
            System.out.println("----      관리자 기능    ----");
            System.out.println("---------------------------");
            HashMap<Integer, Receipt> familyMap = familyScreen.getMap();
            HashMap<Integer, Receipt> animationMap = animationScreen.getMap();
            HashMap<Integer, Receipt> premiumMap = premiumScreen.getMap();
            System.out.println("영화관 총 티켓 판매량 : "
                    +(familyMap.size()+animationMap.size()
                    +premiumMap.size()));
            System.out.println("가족 영화관 결제 총액 :"+Statistics.sum(familyMap));
            System.out.println("애니메이션 영화관 결제 총액 :"+Statistics.sum(animationMap));
            System.out.println("프리미엄 영화관 결제 총액 :"+Statistics.sum(premiumMap));
            ReceiptWriter rw = new ReceiptWriter();
            System.out.print("가족 영화관 영수증 전체 출력: ");
            rw.printConsole(familyMap);
            System.out.print("애니메이션 영화관 영수증 전체 출력: ");
            rw.printConsole(animationMap);
            System.out.print("프리미엄 영화관 영수증 전체 출력: ");
            rw.printConsole(premiumMap);
            System.out.println("안녕히가세요");
        }
    }
}
