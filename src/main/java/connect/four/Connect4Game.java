package connect.four;

import java.util.Optional;

public class Connect4Game {

    private ConnectFourLogic logicEngine;

    public Connect4Game() {
        logicEngine = new ConnectFourLogic();
    }

    public void reset(){
        logicEngine = new ConnectFourLogic();
    }

    public void printBoard(){
        for (int i = 0; i < logicEngine.grid.length; i++) {
            for (int j = 0; j < logicEngine.grid[i].length; j++) {
                var cell = Optional.ofNullable(logicEngine.grid[i][j])
                        .map(x -> padString(x.disc.toString()))
                        .orElse(padString("-"));
                System.out.print(cell);
            }
            System.out.println("");
        }
        for (int i = 0; i < logicEngine.grid[0].length; i++) {
            System.out.print(padString(String.valueOf(i + 1)));
        }
        System.out.println("");
    }

    private String padString(String s){
        var len = 10;
        String out = String.format("%"+len+"s%s%"+len+"s", "",s,"");
        float mid = (out.length()/2);
        float start = mid - (len/2);
        float end = start + len;
        return out.substring((int)start, (int)end);
    }

    public Winner makeMove(String input) {
        var parsedInput = logicEngine.convertInput(input);
        logicEngine.playDisc(parsedInput);

        return checkForWinner(parsedInput);
    }

    public Winner checkForWinner(PlayInput parsedInput) {
        if (logicEngine.checkHorizontal()
                || logicEngine.checkVertical()
                || logicEngine.checkDiagonal()
        ) {
            return new Winner(parsedInput.disc);
        }

        return null;
    }

    public void makeMove(PlayInput input) {
        logicEngine.playDisc(input);
    }
}

class Winner {
    Disc disc;

    public Winner(Disc disc) {
        this.disc = disc;
    }
}
