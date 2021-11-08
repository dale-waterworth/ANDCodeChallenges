package challenging.chains;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ChallengingChainsTest {

    private final ChallengingChains challengingChains = new ChallengingChains();

    @Test
    public void testNoChains() {
        // _
        Cell[][] grid = {{new Cell("_")}};
        var count = challengingChains.getCountOfChains(grid);

        assertEquals(0, count);
    }

    @Test
    public void testSingleChain() {
        // #
        Cell[][] grid = {{new Cell("#")}};
        var count = challengingChains.getCountOfChains(grid);

        assertEquals(1, count);
    }

    @Test
    public void testXAxis2SingleChain() {
        // #_#
        Cell[][] grid = {{new Cell("#"), new Cell("_"), new Cell("#")}};
        var count = challengingChains.getCountOfChains(grid);

        assertEquals(2, count);
    }

    @Test
    public void testXAxisConsecutiveChain() {
        // ###
        Cell[][] grid = {{new Cell("#"), new Cell("#"), new Cell("#")}};
        var count = challengingChains.getCountOfChains(grid);

        assertEquals(1, count);
    }

    @Test
    public void testXAxis2DoubleChain() {
        // ##__##
        Cell[][] grid = {{new Cell("#"), new Cell("#"), new Cell("_"), new Cell("_"), new Cell("#"), new Cell("#")}};
        var count = challengingChains.getCountOfChains(grid);

        assertEquals(2, count);
    }

    // introduce meta data as now the surrounding cells need to be factored in
    @Test
    public void testMetaYChain() {
        Cell grid[][] = {
                {new Cell("#")},
                {new Cell("#")},
        };

        challengingChains.buildGridMeta(grid);

        assertTrue(grid[0][0].isPartOfChain);
        assertTrue(grid[0][0].belowLink);
        assertTrue(grid[1][0].isPartOfChain);
        assertTrue(grid[1][0].aboveLink);
    }

    @Test
    public void testMetaXChain() {
        Cell grid[][] = {
                {new Cell("#"), new Cell("#")},
        };

        challengingChains.buildGridMeta(grid);

        assertTrue(grid[0][0].isPartOfChain);
        assertTrue(grid[0][0].rightLink);
        assertTrue(grid[0][1].isPartOfChain);
        assertTrue(grid[0][1].leftLink);
    }

    @Test
    public void testMetaNoChains() {
        /*
            ___
            _#_
            ___
         */
        Cell[][] grid = {
                {new Cell("_"), new Cell("_"), new Cell("_")},
                {new Cell("_"), new Cell("#"), new Cell("_")},
                {new Cell("_"), new Cell("_"), new Cell("_")},
        };

        challengingChains.buildGridMeta(grid);

        Arrays.stream(grid).flatMap(Stream::of).forEach(cell -> {
            assertFalse(cell.isPartOfChain);
        });
    }

    @Test
    public void testMeta2Chains() {
        /*
            #__
            _##
            __#
            _##
         */
        Cell grid[][] = {
                {new Cell("#"), new Cell("_"), new Cell("_")},
                {new Cell("_"), new Cell("#"), new Cell("#")},
                {new Cell("_"), new Cell("_"), new Cell("#")},
                {new Cell("_"), new Cell("#"), new Cell("#")},
        };

        challengingChains.buildGridMeta(grid);

        assertFalse(grid[0][0].isPartOfChain);
        assertFalse(grid[0][1].isPartOfChain);
        assertFalse(grid[0][2].isPartOfChain);

        assertFalse(grid[1][0].isPartOfChain);
        assertTrue(grid[1][1].isPartOfChain);
        assertTrue(grid[1][1].rightLink);
        assertTrue(grid[1][2].isPartOfChain);
        assertTrue(grid[1][2].leftLink);

        assertFalse(grid[2][0].isPartOfChain);
        assertFalse(grid[2][1].isPartOfChain);
        assertTrue(grid[2][2].isPartOfChain);
        assertTrue(grid[2][2].aboveLink);
        assertTrue(grid[2][2].belowLink);


        assertFalse(grid[3][0].isPartOfChain);
        assertTrue(grid[3][1].isPartOfChain);
        assertTrue(grid[3][1].rightLink);
        assertTrue(grid[3][2].isPartOfChain);
        assertTrue(grid[3][2].aboveLink);
        assertTrue(grid[3][2].leftLink);
    }


    @Test
    public void testYAxis() {
        // ##__##
        Cell grid[][] = {
                {new Cell("#")},
                {new Cell("#")}
        };
        var count = challengingChains.getCountOfChains(grid);

        assertEquals(1, count);
    }

    @Test
    public void testRightRightDown() {
        // ##__##
        Cell grid[][] = {
                {new Cell("#"), new Cell("#")},
                {new Cell("_"), new Cell("#")},
        };
        var count = challengingChains.getCountOfChains(grid);

        assertEquals(1, count);
    }

    @Test
    public void testRightRightDownRightRight() {
        // ##__##
        Cell grid[][] = {
                {new Cell("#"), new Cell("#"), new Cell("_"), new Cell("_")},
                {new Cell("_"), new Cell("#"), new Cell("#"), new Cell("#")},
        };
        var count = challengingChains.getCountOfChains(grid);

        assertEquals(1, count);
    }

    @Test
    public void testRightRightDownRightRightDownRightWithSingleCorner() {
        Cell grid[][] = {
                {new Cell("#"), new Cell("#"), new Cell("_"), new Cell("#")},
                {new Cell("_"), new Cell("#"), new Cell("#"), new Cell("_")},
                {new Cell("_"), new Cell("_"), new Cell("#"), new Cell("#")},
        };
        var count = challengingChains.getCountOfChains(grid);

        assertEquals(2, count);
    }

    @Test
    public void testReverseL() {
        Cell grid[][] = {
                {new Cell("#"), new Cell("#"), new Cell("#"), new Cell("#")},
                {new Cell("#"), new Cell("_"), new Cell("_"), new Cell("_")},
                {new Cell("#"), new Cell("_"), new Cell("_"), new Cell("_")},
        };
        var count = challengingChains.getCountOfChains(grid);

        assertEquals(1, count);
    }

    @Test
    public void testDownDownLeftLeft() {
        Cell grid[][] = {
                {new Cell("_"), new Cell("_"), new Cell("_"), new Cell("#")},
                {new Cell("_"), new Cell("_"), new Cell("_"), new Cell("#")},
                {new Cell("#"), new Cell("#"), new Cell("#"), new Cell("#")},
        };
        var count = challengingChains.getCountOfChains(grid);

        assertEquals(1, count);
    }

    @Test
    public void testDownDownLeftLeftUp() {
        Cell grid[][] = {
                {new Cell("_"), new Cell("_"), new Cell("_"), new Cell("#")},
                {new Cell("#"), new Cell("_"), new Cell("_"), new Cell("#")},
                {new Cell("#"), new Cell("#"), new Cell("#"), new Cell("#")},
        };
        var count = challengingChains.getCountOfChains(grid);

        assertEquals(1, count);
    }

    @Test
    public void getProblemFile() {
        var lines = getFileAsLines();

        var grid = lines.stream()
                .map(line -> Arrays.stream(line.split("")).map(Cell::new).toArray(Cell[]::new))
                .toArray(Cell[][]::new);

        var count = challengingChains.getCountOfChains(grid);

        assertEquals(34, count);

        System.out.println("count: " + count); // 34
    }

    public List<String> getFileAsLines() {
        try {
            var path = Paths.get(getClass().getClassLoader()
                    .getResource("chains.txt").toURI());
            return Files.lines(path).collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
