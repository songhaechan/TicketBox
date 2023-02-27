package ticketbox.cinema;

import ticketbox.payment.MovieTicket;

public class FamilyScreen extends Screen {
    public FamilyScreen(String name, String story, int price, int row, int col) {
        super(name, story, price, row, col);
    }
    @Override
    public void showMovieInfo() {
        System.out.println("--------------------");
        System.out.println("-       A소개       -");
        System.out.println("--------------------");
        System.out.println("영화관 : 가족 영화 1관");
        System.out.println("줄거리 :"+movieStory);
        System.out.println("가격 :"+ticketPrice);
    }
}


