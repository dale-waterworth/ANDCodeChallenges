package connect.four;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testConnect4Horizontal() {
        game.playDisc(Disc.RED, 0);
        game.playDisc(Disc.RED, 1);
        game.playDisc(Disc.RED, 2);
        game.playDisc(Disc.RED, 3);

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
}
