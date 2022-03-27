package connect.four;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConnectFourLogicTest {

    ConnectFourLogic game;

    @BeforeEach
    public void beforeEach() {
        game = new ConnectFourLogic();
    }

    @Test
    public void testGrid() {
        Assertions.assertEquals(6, game.grid.length);
        Assertions.assertEquals(7, game.grid[0].length);
    }

    @Test
    public void testPlacingSingleDisc() {
        game.playDisc(Disc.RED, 0);

        assertEquals(Disc.RED, game.grid[game.grid.length - 1][0].disc);
    }

    @Test
    public void testPlacingTwoDiscsVertical() {
        game.playDisc(Disc.RED, 0);
        game.playDisc(Disc.YELLOW, 0);

        assertEquals(Disc.RED, game.grid[game.grid.length - 1][0].disc);
        assertEquals(Disc.YELLOW, game.grid[game.grid.length - 2][0].disc);
    }

    @Test
    public void testPlacingTwoDiscsHorizontal() {
        game.playDisc(Disc.RED, 0);
        game.playDisc(Disc.YELLOW, 1);

        assertEquals(Disc.RED, game.grid[game.grid.length - 1][0].disc);
        assertEquals(Disc.YELLOW, game.grid[game.grid.length - 1][1].disc);
    }

    @Test
    public void check4MatchesAtStart() {
        List<Disc> typicalRow = new LinkedList<>(
                Arrays.asList(Disc.RED, Disc.RED, Disc.RED, Disc.RED, Disc.YELLOW, Disc.YELLOW, Disc.YELLOW)
        );

        assertTrue(game.check4Matches(typicalRow));
    }

    @Test
    public void check4MatchesAtEnd() {
        List<Disc> typicalRow = Arrays.asList(Disc.RED, Disc.RED, Disc.RED, Disc.YELLOW, Disc.YELLOW, Disc.YELLOW, Disc.YELLOW);

        assertTrue(game.check4Matches(typicalRow));
    }

    @Test
    public void checkNoneMatches() {
        List<Disc> typicalRow = Arrays.asList(null, Disc.RED, Disc.RED, Disc.YELLOW, Disc.YELLOW, Disc.RED, Disc.YELLOW);


        assertFalse(game.check4Matches(typicalRow));
    }

    @Test
    public void testConnect4HorizontalRed() {
        game.playDisc(Disc.RED, 0);
        game.playDisc(Disc.RED, 1);
        game.playDisc(Disc.RED, 2);
        game.playDisc(Disc.RED, 3);

        assertEquals(true, game.checkHorizontal());
    }

    @Test
    public void testConnect4HorizontalYellow() {
        game.playDisc(Disc.YELLOW, 0);
        game.playDisc(Disc.YELLOW, 1);
        game.playDisc(Disc.YELLOW, 2);
        game.playDisc(Disc.YELLOW, 3);

        assertEquals(true, game.checkHorizontal());
    }

    @Test
    public void testConnect4HorizontalAtEnd() {
        game.playDisc(Disc.RED, 3);
        game.playDisc(Disc.RED, 4);
        game.playDisc(Disc.RED, 5);
        game.playDisc(Disc.RED, 6);

        assertEquals(true, game.checkHorizontal());
    }

    @Test
    public void testConnect4HorizontalWithOthers() {
        game.playDisc(Disc.YELLOW, 0);
        game.playDisc(Disc.RED, 1);
        game.playDisc(Disc.RED, 2);
        game.playDisc(Disc.RED, 3);
        game.playDisc(Disc.YELLOW, 4);
        game.playDisc(Disc.RED, 5);


        assertEquals(false, game.checkHorizontal());
    }

    @Test
    public void testConnect4Vertical() {
        game.playDisc(Disc.RED, 0);
        game.playDisc(Disc.RED, 0);
        game.playDisc(Disc.RED, 0);
        game.playDisc(Disc.RED, 0);

        assertEquals(true, game.checkVertical());
    }

    @Test
    public void testConnect4VerticalWithOthers() {
        game.playDisc(Disc.RED, 0);
        game.playDisc(Disc.RED, 0);
        game.playDisc(Disc.RED, 0);
        game.playDisc(Disc.YELLOW, 0);
        game.playDisc(Disc.RED, 0);

        assertEquals(false, game.checkVertical());
    }

    @Test
    public void testConnect4VerticalWithOthers2() {
        game.playDisc(Disc.RED, 2);
        game.playDisc(Disc.RED, 2);
        game.playDisc(Disc.RED, 2);
        game.playDisc(Disc.RED, 2);

        assertEquals(true, game.checkVertical());
    }

    @Test
    public void testDiagonalBottomLeftToTopRight() {
        /**
         * yyyr
         * yyry
         * yryy
         * ryyy
         */
        game.playDisc(Disc.RED, 2);
        game.playDisc(Disc.YELLOW, 2);
        game.playDisc(Disc.YELLOW, 2);
        game.playDisc(Disc.YELLOW, 2);

        game.playDisc(Disc.YELLOW, 3);
        game.playDisc(Disc.RED, 3);
        game.playDisc(Disc.YELLOW, 3);
        game.playDisc(Disc.YELLOW, 3);

        game.playDisc(Disc.YELLOW, 4);
        game.playDisc(Disc.YELLOW, 4);
        game.playDisc(Disc.RED, 4);
        game.playDisc(Disc.YELLOW, 4);

        game.playDisc(Disc.YELLOW, 5);
        game.playDisc(Disc.YELLOW, 5);
        game.playDisc(Disc.YELLOW, 5);
        game.playDisc(Disc.RED, 5);

        assertEquals(true, game.checkDiagonal());
    }

    @Test
    public void testDiagonalBottomRightToTopLeft() {
        /**
         * ryyy
         * yryy
         * yyry
         * yyyr
         */
        game.playDisc(Disc.YELLOW, 2);
        game.playDisc(Disc.YELLOW, 2);
        game.playDisc(Disc.YELLOW, 2);
        game.playDisc(Disc.RED, 2);

        game.playDisc(Disc.YELLOW, 3);
        game.playDisc(Disc.YELLOW, 3);
        game.playDisc(Disc.RED, 3);
        game.playDisc(Disc.YELLOW, 3);

        game.playDisc(Disc.YELLOW, 4);
        game.playDisc(Disc.RED, 4);
        game.playDisc(Disc.YELLOW, 4);
        game.playDisc(Disc.YELLOW, 4);

        game.playDisc(Disc.RED, 5);
        game.playDisc(Disc.YELLOW, 5);
        game.playDisc(Disc.YELLOW, 5);
        game.playDisc(Disc.YELLOW, 5);

        assertEquals(true, game.checkDiagonal());
    }


    @Test
    public void testSampleInput(){
        PlayInput playInput = game.convertInput("A_Red");
        Assertions.assertEquals(0, playInput.column);
        Assertions.assertEquals(Disc.RED, playInput.disc);

        PlayInput playInput2 = game.convertInput("G_Yellow");
        Assertions.assertEquals(6, playInput2.column);
        Assertions.assertEquals(Disc.YELLOW, playInput2.disc);
    }

}
