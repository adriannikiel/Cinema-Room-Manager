package cinema;

import java.util.Scanner;

public class Cinema {

    private final static int PRICE_FOR_TICKET_PREMIUM = 10;
    private final static int PRICE_FOR_TICKET = 8;

    private static Scanner scanner = new Scanner(System.in);

    private char[][] cinemaRoom;
    private int rows;
    private int seatsInRow;
    private int[] clientSeat = new int[2];

    public Cinema() {
    }

    public static void main(String[] args) {
        // Write your code here

        Cinema cinema = new Cinema();
        initCinema(cinema);

        while (true) {
            int userChoice = chooseOption();

            if (userChoice == 0) {
                break;
            } else if (userChoice == 1) {
                drawCinemaRoom(cinema.cinemaRoom);
            } else if (userChoice == 2) {
                setClientSeat(cinema);
                calculatePrice(cinema.cinemaRoom, cinema.clientSeat[0]);
            }

        }
    }

    private static int chooseOption() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("0. Exit");

        return scanner.nextInt();
    }

    public static void initCinema(Cinema cinema) {
        System.out.println("Enter the number of rows:");
        cinema.rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        cinema.seatsInRow = scanner.nextInt();

        cinema.cinemaRoom = setEmptyCinemaRoom(cinema.rows, cinema.seatsInRow);
    }

    public static void setClientSeat(Cinema cinema) {
        System.out.println();
        System.out.println("Enter a row number:");
        cinema.clientSeat[0] = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        cinema.clientSeat[1] = scanner.nextInt();

        cinema.cinemaRoom[cinema.clientSeat[0]][cinema.clientSeat[1]] = 'B';
    }

    private static double calculatePrice(char[][] cinemaRoom, int row) {

        final int NUMBER_OF_SEATS = (cinemaRoom.length - 1) * (cinemaRoom[0].length - 1);

        double price;

        if (NUMBER_OF_SEATS <= 60) {
            price = Cinema.PRICE_FOR_TICKET_PREMIUM;
        } else {
            int premiumRows = (cinemaRoom.length - 1) / 2;

            if (row <= premiumRows) {
                price = PRICE_FOR_TICKET_PREMIUM;
            } else {
                price = PRICE_FOR_TICKET;
            }

        }

        System.out.print("Ticket price: ");
        System.out.println("$" + (int) price);

        return price;
    }

    private static double calculateProfit(int rows, int seatsInRow) {

        final int NUMBER_OF_SEATS = rows * seatsInRow;

        double profit;

        if (NUMBER_OF_SEATS <= 60) {
            profit = NUMBER_OF_SEATS * Cinema.PRICE_FOR_TICKET_PREMIUM;
        } else {
            int premiumRows = rows / 2;
            profit = premiumRows * Cinema.PRICE_FOR_TICKET_PREMIUM * seatsInRow + (rows - premiumRows) * Cinema.PRICE_FOR_TICKET * seatsInRow;
        }

        System.out.println("Total income:");
        System.out.println("$" + (int) profit);

        return profit;
    }

    private static char[][] setEmptyCinemaRoom(int rows, int seatsInRow) {

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

        return cinemaRoom;
    }

    public static void drawCinemaRoom(char[][] cinemaRoom) {

        System.out.println("\nCinema:");

        for (int i = 0; i < cinemaRoom.length; i++) {
            for (int j = 0; j < cinemaRoom[i].length; j++) {
                System.out.print(cinemaRoom[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

    }
}