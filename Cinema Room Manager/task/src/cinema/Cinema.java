package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();

        double profit = calculateProfit(rows, seatsInRow);

        System.out.println("Total income:");
        System.out.println("$" + (int) profit);

        //drawCinemaRoom(rows, seatsInRow);
    }

    private static double calculateProfit(int rows, int seatsInRow) {

        final int NUMBER_OF_SEATS = rows * seatsInRow;
        final int PRICE_FOR_TICKET_PREMIUM = 10;
        final int PRICE_FOR_TICKET = 8;

        if (NUMBER_OF_SEATS <= 60) {
            return NUMBER_OF_SEATS * PRICE_FOR_TICKET_PREMIUM;
        } else {
            int premiumRows = rows / 2;
            return premiumRows * PRICE_FOR_TICKET_PREMIUM * seatsInRow + (rows-premiumRows) * PRICE_FOR_TICKET * seatsInRow;
        }
    }

    private static char[][] drawCinemaRoom(int rows, int seatsInRow) {

        char[][] cinemaRoom = new char[rows + 1][seatsInRow + 1];

        cinemaRoom[0][0] = ' ';

        // first row
        for (int j = 1; j <= seatsInRow; j++) {
            cinemaRoom[0][j] = Character.forDigit(j, 10);
        }

        // first column
        for (int i = 1; i <= rows; i++) {
            cinemaRoom[i][0] = Character.forDigit(i, 10);
        }

        // real seats
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seatsInRow; j++) {
                cinemaRoom[i][j] = 'S';
            }

        }

        drawCinemaRoom(cinemaRoom);

        return cinemaRoom;
    }

    private static void drawCinemaRoom(char[][] cinemaRoom) {

        System.out.println("Cinema:");

        for (int i = 0; i < cinemaRoom.length; i++) {
            for (int j = 0; j < cinemaRoom[i].length; j++) {
                System.out.print(cinemaRoom[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}