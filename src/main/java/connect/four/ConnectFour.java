package connect.four;

import java.util.*;

public class ConnectFour {

    public GridCell[][] grid;

    public ConnectFour() {
        grid = new GridCell[6][7];
    }

    public void playDisc(Disc disc, int column) {
        outer:
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[i].length; j >= 0; j--) {
                if (j == column) {
                    if (grid[i][j] == null) {
                        grid[i][j] = new GridCell(disc);
                        break outer;
                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
    }


    public boolean checkHorizontal(GridCell[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            var result = 0;
            List<GridCell> row = new LinkedList<>();
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] != null){

                }
                /*if ((j == 0 || j == grid[i].length - 1) && grid[i][j] != null) {
                    result++;
                    if (result == 4) {
                        return true;
                    }
                }
                if (grid[i][j] != null && grid[i][j + 1] != null
                        && grid[i][j].disc == grid[i][j + 1].disc) {
                    result++;
                    if (result == 4) {
                        return true;
                    }
                } else {
                    result = 0;
                }*/
            }
        }
        return false;
    }

    public boolean checkVertical() {
        for (int i = 0; i < grid[0].length; i++) {
            var result = 0;
            for (int j = grid.length - 1; j > 0; j--) {
                System.out.println(grid[j][i]);
                if ((j == 0 || j == grid.length - 1) && grid[j][i] != null) {
                    result++;
                    if (result == 4) {
                        return true;
                    }
                }

                if (grid[j][i] != null && grid[j - 1][i] != null
                        && grid[j][i].disc == grid[j - 1][i].disc) {
                    result++;
                    if (result == 4) {
                        return true;
                    }
                } else {
                    result = 0;
                }
            }
        }
        return false;
    }

    public boolean checkDiagonal(GridCell[][] grid) {
        int WIDTH = grid[0].length;
        int HEIGHT = grid.length;
        var sideGrid = new GridCell[WIDTH * HEIGHT][WIDTH * HEIGHT];
        for (int k = 0; k <= WIDTH + HEIGHT - 2; k++) {
            System.out.println("k" + k);
            var row = new GridCell[7];
            for (int j = 0; j <= k; j++) {
                int i = k - j;
                if (i < HEIGHT && j < WIDTH) {
                    row[j] = grid[i][j];
                    System.out.print(Optional.ofNullable(grid[i][j])
                            .map(x -> x.disc.toString())
                            .orElse("-") + " ");
                }
            }
            System.out.println();
            System.out.println(Arrays.toString(row));
            sideGrid[k] = row;
        }
        System.out.println(Arrays.deepToString(sideGrid));

        return checkHorizontal(sideGrid);
    }
}

enum Disc {
    RED, YELLOW
}

class GridCell {
    Disc disc;

    public GridCell(Disc disc) {
        this.disc = disc;
    }

    @Override
    public String toString() {
        return "" + disc;
    }
}
