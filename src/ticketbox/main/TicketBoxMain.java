package ticketbox.main;

import ticketbox.cinema.Screen;

import java.util.Scanner;

public class TicketBoxMain {
    public static void main(String[] args) {
        TicketBox ticketBox = new TicketBox();
        Scanner scan = new Scanner(System.in);
        Screen screen = null;
        boolean mainMenu = true;
        ticketBox.initScreen();

        while(true){
            if(mainMenu){
                screen = ticketBox.selectScreen();
                if(screen == null){
                    System.out.println("안녕히가세요.");
                    scan.close();
                    System.exit(0);
                }
                mainMenu = false;
            }
            screen.showScreenMenu();
            System.out.print("메뉴를 선택하세요>>");
            try{
                int select = scan.nextInt();
                switch (select){
                    case 1:
                        screen.showMovieInfo();
                        break;
                    case 2:
                        screen.showSeatMap();
                        break;
                    case 3:
                        screen.reserveTicket();
                        break;
                    case 4:
                        screen.changeTicket();
                        break;
                    case 5:
                        screen.payment();
                        break;
                    case 6:
                        screen.printTicket();
                        break;
                    case 7:
                        mainMenu = true;
                        break;
                    default:
                        throw new Exception();
                }
            }catch (Exception e){
                System.out.println("메뉴를 다시 선택해주세요.");
            }
        }
    }
}
