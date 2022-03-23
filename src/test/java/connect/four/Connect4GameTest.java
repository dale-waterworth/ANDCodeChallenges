package connect.four;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Connect4GameTest {
    Connect4Game game;

    @BeforeEach
    public void beforeEach() {
        game = new Connect4Game();
    }

    @Test
    public void testSample1() {
        var moves = Arrays.asList("A_Red",
                "B_Yellow",
                "A_Red",
                "B_Yellow",
                "A_Red",
                "B_Yellow",
                "G_Red",
                "B_Yellow");

        Disc winner = null;

        for (int i = 0; i < moves.size(); i++) {
            var won = game.makeMove(moves.get(i));
            if(won != null){
                winner = won.disc;
            }
        }
        assertEquals(Disc.YELLOW, winner);

    }

    @Test
    public void testSample2() {
        var moves = Arrays.asList("A_Red",
                "G_Yellow",
                "B_Red",
                "F_Yellow",
                "C_Red",
                "E_Yellow",
                "D_Red");

        Disc winner = null;

        for (int i = 0; i < moves.size(); i++) {
            var won = game.makeMove(moves.get(i));
            if(won != null){
                winner = won.disc;
            }
        }
        assertEquals(Disc.RED, winner);
    }

    @Test
    public void testSample3() {
        var moves = Arrays.asList("B_Yellow",
                "C_Red",
                "C_Yellow",
                "D_Red",
                "A_Yellow",
                "D_Red",
                "D_Yellow",
                "E_Red",
                "E_Yellow",
                "F_Red",
                "E_Yellow");

        Disc winner = null;

        for (int i = 0; i < moves.size(); i++) {
            var won = game.makeMove(moves.get(i));
            if(won != null){
                winner = won.disc;
                break;
            }
        }
        assertEquals(Disc.RED, winner);

    }
}
