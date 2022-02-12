package cinema;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here

        int n = 7;
        int m = 8;

        drawCinemaRoom(n, m);
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