package connect.four;

import java.util.Random;
import java.util.Scanner;

public class InteractiveConnect4 {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new InteractiveConnect4();
    }

    public InteractiveConnect4() {
        while (true) {
            System.out.println("Press 1 for single player or 2 for 2 player");
            try {
                if (scanner.nextInt() == 1) {
                    System.out.println("Single Player");
                    onePlayer();
                } else {
                    System.out.println("2 Player");
                    twoPlayer();
                }
            } catch (Exception e) {
                System.out.println("1 or 2");
                scanner.next();
            }
        }
    }

    private void onePlayer() {
        var game = new Connect4Game();
        int column;
        while (true) {
            game.printBoard();
            displayInfo(Disc.RED);

            try {
                column = scanner.nextInt();

                if (column == 9) {
                    break;
                } else if (column == 0) {
                    game.reset();
                    continue;
                }

                --column;
                playRound(column, Disc.RED, game);
                playRound(getComputerTurn(), Disc.YELLOW, game);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private int getComputerTurn() {
        return new Random().nextInt(6) + 1;
    }

    public void playRound(int column, Disc nextDisc, Connect4Game game) {
        var input = new PlayInput(column, nextDisc);
        game.makeMove(input);

        var winner = game.checkForWinner(input);

        if (winner != null) {
            for (int i = 0; i < 100; i++) {
                System.out.print("WINNER IS: " + winner.disc);
                if (i % 5 == 0) {
                    System.out.println("");
                }
            }
            System.out.println("");
            game.printBoard();
            System.out.println("\n\n\n\n");
            game.reset();
        }
    }

    public void twoPlayer() {
        var game = new Connect4Game();

        int column;
        Disc nextDisc = Disc.RED;
        while (true) {
            game.printBoard();
            displayInfo(nextDisc);

            try {
                column = scanner.nextInt();

                if (column == 9) {
                    break;
                } else if (column == 0) {
                    game.reset();
                    continue;
                }

                --column;

                playRound(column, nextDisc, game);

                if (nextDisc == Disc.RED) {
                    nextDisc = Disc.YELLOW;
                } else {
                    nextDisc = Disc.RED;
                }

            } catch (Exception e) {
                System.out.println("OPTIONS ARE: 0 - 7");
                scanner.next();
            }

        }
    }

    private void displayInfo(Disc nextDisc) {
        System.out.println("Select column 1 - 7");
        System.out.println("Select 0 for new game");
        System.out.println("Select 9 to exit");
        System.out.println(nextDisc.toString() + " take you turn...");
    }

}
