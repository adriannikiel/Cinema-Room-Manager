package cinema;

import java.util.Scanner;

public class Cinema {

    private final static int PRICE_FOR_PREMIUM_TICKET = 10;
    private final static int PRICE_FOR_NORMAL_TICKET = 8;

    private static final Scanner scanner = new Scanner(System.in);

    private char[][] cinemaRoom;
    private int rows;
    private int seatsInRow;

    public Cinema() {
    }

    public static void main(String[] args) {

        Cinema cinema = new Cinema();

        initCinema(cinema);

        while (true) {
            int userChoice = chooseOption();

            if (userChoice == 0) {
                break;
            } else if (userChoice == 1) {
                drawCinemaRoom(cinema);
            } else if (userChoice == 2) {
                buyTicket(cinema);
            } else if (userChoice == 3) {
                printStatistics(cinema);
            }
        }
    }

    private static int chooseOption() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
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

    public static void buyTicket(Cinema cinema) {

        int row = 0;
        int seatInRow = 0;

        while (true) {
            System.out.println();

            System.out.println("Enter a row number:");
            row = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            seatInRow = scanner.nextInt();

            if (row < 1 || row > 9 || seatInRow < 1 || seatInRow > 9) {
                System.out.println("Wrong input!");
                continue;
            }

            if (cinema.cinemaRoom[row][seatInRow] == 'B') {
                System.out.println("That ticket has already been purchased!");
                continue;
            }

            cinema.cinemaRoom[row][seatInRow] = 'B';
            showPrice(cinema, row);

            return;
        }

    }

    private static void showPrice(Cinema cinema, int row) {

        final int NUMBER_OF_SEATS = cinema.rows * cinema.seatsInRow;
        double price;

        if (NUMBER_OF_SEATS <= 60) {
            price = Cinema.PRICE_FOR_PREMIUM_TICKET;
        } else {
            int premiumRows = cinema.rows / 2;

            if (row <= premiumRows) {
                price = PRICE_FOR_PREMIUM_TICKET;
            } else {
                price = PRICE_FOR_NORMAL_TICKET;
            }
        }

        System.out.print("\nTicket price: ");
        System.out.println("$" + (int) price);
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

    public static void drawCinemaRoom(Cinema cinema) {

        System.out.println("\nCinema:");

        for (int i = 0; i <= cinema.rows; i++) {
            for (int j = 0; j <= cinema.seatsInRow; j++) {
                System.out.print(cinema.cinemaRoom[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

    }

    public static void printStatistics(Cinema cinema) {

        int soldTickets = calculateSoldTickets(cinema);
        double currentIncome = calculateCurrentIncome(cinema);
        double totalIncome = calculateProfit(cinema.rows, cinema.seatsInRow);
        double percentage = soldTickets * 100.0 / (cinema.rows * cinema.seatsInRow);

        System.out.print("\nNumber of purchased tickets: " + soldTickets);
        System.out.printf("\nPercentage: %.2f%% ", percentage);
        System.out.print("\nCurrent income: $" + (int) currentIncome);
        System.out.println("\nTotal income: $" + (int) totalIncome);

    }

    private static double calculateCurrentIncome(Cinema cinema) {

        final int NUMBER_OF_SEATS = cinema.rows * cinema.seatsInRow;
        double income = 0;

        if (NUMBER_OF_SEATS <= 60) {
            income = Cinema.PRICE_FOR_PREMIUM_TICKET * calculateSoldTickets(cinema);
        } else {
            int premiumRows = cinema.rows / 2;

            double ticketPrice;

            for (int i = 1; i <= cinema.rows; i++) {

                if (i <= premiumRows) {
                    ticketPrice = PRICE_FOR_PREMIUM_TICKET;
                } else {
                    ticketPrice = PRICE_FOR_NORMAL_TICKET;
                }

                for (int j = 1; j <= cinema.seatsInRow; j++) {
                    if (cinema.cinemaRoom[i][j] == 'B')
                        income += ticketPrice;
                }
            }
        }

        return income;
    }

    private static int calculateSoldTickets(Cinema cinema) {

        int counter = 0;

        for (int i = 1; i < cinema.rows + 1; i++) {
            for (int j = 1; j < cinema.seatsInRow + 1; j++) {
                if (cinema.cinemaRoom[i][j] == 'B') {
                    counter++;
                }
            }
        }

        return counter;
    }

    private static double calculateProfit(int rows, int seatsInRow) {

        final int NUMBER_OF_SEATS = rows * seatsInRow;

        double profit;

        if (NUMBER_OF_SEATS <= 60) {
            profit = NUMBER_OF_SEATS * Cinema.PRICE_FOR_PREMIUM_TICKET;
        } else {
            int premiumRows = rows / 2;
            profit = seatsInRow * (premiumRows * Cinema.PRICE_FOR_PREMIUM_TICKET + (rows - premiumRows) * Cinema.PRICE_FOR_NORMAL_TICKET);
        }

        return profit;
    }
}