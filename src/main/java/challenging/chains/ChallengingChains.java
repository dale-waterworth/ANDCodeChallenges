package challenging.chains;

import java.util.Arrays;

public class ChallengingChains {
    private int chains = 0;

    public ChallengingChains() {
    }

    public void buildGridMeta(Cell[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            var rowLength = grid[y].length;
            for (int x = 0; x < rowLength; x++) {

                var cell = grid[y][x];
                cell.x = x;
                cell.y = y;

                cell.aboveLink = y - 1 >= 0 && grid[y - 1][x].isChain;
                cell.rightLink = x + 1 < rowLength && grid[y][x + 1].isChain;
                cell.belowLink = y + 1 < grid.length && grid[y + 1][x].isChain;
                cell.leftLink = x - 1 >= 0 && grid[y][x - 1].isChain;

                cell.ignore = !cell.isChain;

                cell.linkCount = cell.isChain
                        ? (int) Arrays.asList(cell.aboveLink, cell.rightLink, cell.belowLink, cell.leftLink)
                        .stream().filter(p -> p == true).count()
                        : 0;
                cell.isPartOfChain = cell.linkCount > 0;
            }
        }
    }

    public int getCountOfChains(Cell[][] grid) {
        buildGridMeta(grid);

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                traverser(grid, y, x, true);
            }
        }
        return chains;
    }

    private void traverser(Cell[][] grid, int y, int x, boolean caller) {
        var cell = grid[y][x];
        if (cell.isChain && !cell.ignore) {
            cell.ignore = true;
            if (cell.isPartOfChain) {
                if (cell.rightLink) {
                    traverser(grid, y,  x + 1, false);
                }
                if (cell.belowLink) {
                    traverser(grid, y + 1,  x, false);
                }
                if (cell.leftLink) {
                    traverser(grid, y,  x - 1, false);
                }
                if (cell.aboveLink) {
                    traverser(grid, y-1,  x, false);
                }
            }

            if(caller){
                chains++;
            }
        }
    }
}

