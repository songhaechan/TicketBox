package ticketbox.main;

import ticketbox.cinema.AnimationScreen;
import ticketbox.cinema.FamilyScreen;
import ticketbox.cinema.PremiumScreen;
import ticketbox.cinema.Screen;

import java.util.Scanner;

public class TicketBox {
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
        System.out.println("선택(1~3)외 종료");
        System.out.print("상영관 선택:");
        select = scanner.nextInt();
        switch (select){
            case 1:
                return familyScreen;
            case 2:
                return animationScreen;
            case 3:
                return premiumScreen;
            default:
                return null;
        }
    }
}
