package connect.four;

import java.util.Scanner;

public class InteractiveConnect4 {
    Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        new InteractiveConnect4();
    }

    public InteractiveConnect4(){
        while (true){
            System.out.println("Press 1 for single player or 2 for 2 player");
            try {
                if(scanner.nextInt() == 1){
                    System.out.println("Single Player");
                } else {
                    System.out.println("2 Player");
                    twoPlayer();
                }
            } catch (Exception e){
                System.out.println("1 or 2");
                scanner.next();
            }
        }
    }

    public void twoPlayer(){
        var game = new Connect4Game();

        int column;
        Disc nextDisc = Disc.RED;
        while (true) {
            game.printBoard();
            System.out.println("Select column 1 - 7");
            System.out.println("Select 0 for new game");
            System.out.println("Select 9 to exit");
            System.out.println(nextDisc.toString() + " take you turn...");

            if(nextDisc == Disc.YELLOW){

            }

            try {
                column = scanner.nextInt();

                if (column == 0) {
                    game.reset();
                    continue;
                }

                if(column == 9){
                    break;
                }

                --column;

                var input = new PlayInput(column, nextDisc);
                game.makeMove(input);

                var winner = game.checkForWinner(input);

                if (winner != null) {
                    for (int i = 0; i < 10000; i++) {
                        System.out.println("WINNER IS: " + winner.disc);
                    }
                    game.reset();
                    continue;
                }

                if (nextDisc == Disc.RED) {
                    nextDisc = Disc.YELLOW;
                } else {
                    nextDisc = Disc.RED;
                }


            } catch (Exception e) {
                System.out.println("0 - 7 dummy!");
                scanner.next();
            }

        }
    }

}
