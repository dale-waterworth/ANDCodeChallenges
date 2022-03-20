package connect.four;

import java.util.Arrays;

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


    public boolean checkHorizontal() {
        for (int i = 0; i < grid.length; i++) {
            var result = 0;
            for (int j = 0; j < grid[i].length; j++) {
                if ((j == 0 || j == grid[i].length - 1) && grid[i][j] != null) {
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
                }
            }
        }
        return false;
    }

    public boolean checkVertical() {
        for (int i = 0; i < grid[0].length; i++) {
            var result = 0;
            for (int j = 0; j < grid.length; j++) {
                System.out.println(grid[j][i]);
                if ((j == 0 || j == grid.length - 1) && grid[j][i] != null) {
                    result++;
                    if (result == 4) {
                        return true;
                    }
                }
                if (grid[j][i] != null && grid[j + 1][i] != null
                        && grid[j][i].disc == grid[j + 1][i].disc) {
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
