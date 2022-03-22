package connect.four;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static connect.four.ConnectFour.connectYellow;
import static org.junit.jupiter.api.Assertions.*;

class ConnectFourTest {

    ConnectFour game;

    @BeforeEach
    public void beforeEach() {
        game = new ConnectFour();
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
    public void testCompareArray() {
         List<Disc> typicalRow = new LinkedList<>(
                Arrays.asList(null,Disc.RED,Disc.RED, Disc.YELLOW,Disc.YELLOW,Disc.YELLOW,Disc.YELLOW)
        );

        assertTrue(typicalRow.containsAll(connectYellow));
    }

    @Test
    public void testConnect4HorizontalRed() {
        game.playDisc(Disc.RED, 0);
        game.playDisc(Disc.RED, 1);
        game.playDisc(Disc.RED, 2);
        game.playDisc(Disc.RED, 3);

        assertEquals(true, game.checkHorizontal(game.grid));
    }
    @Test
    public void testConnect4HorizontalYellow() {
        game.playDisc(Disc.YELLOW, 0);
        game.playDisc(Disc.YELLOW, 1);
        game.playDisc(Disc.YELLOW, 2);
        game.playDisc(Disc.YELLOW, 3);

        assertEquals(true, game.checkHorizontal(game.grid));
    }

    @Test
    public void testConnect4HorizontalAtEnd() {
        game.playDisc(Disc.RED, 3);
        game.playDisc(Disc.RED, 4);
        game.playDisc(Disc.RED, 5);
        game.playDisc(Disc.RED, 6);

        assertEquals(true, game.checkHorizontal(game.grid));
    }

    @Test
    public void testConnect4HorizontalWithOthers() {
        game.playDisc(Disc.YELLOW, 0);
        game.playDisc(Disc.RED, 1);
        game.playDisc(Disc.RED, 2);
        game.playDisc(Disc.RED, 3);
        game.playDisc(Disc.YELLOW, 4);
        game.playDisc(Disc.RED, 5);


        assertEquals(false, game.checkHorizontal(game.grid));
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
    public void testDiagonal() {
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

        assertEquals(true, game.checkDiagonal(game.grid));
    }


}
