package connect.four;

public class Connect4Game {

    private ConnectFourLogic logicEngine;

    public Connect4Game() {
        logicEngine = new ConnectFourLogic();
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

}

class Winner {
    Disc disc;

    public Winner(Disc disc) {
        this.disc = disc;
    }
}
