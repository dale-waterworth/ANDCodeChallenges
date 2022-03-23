package connect.four;

import java.util.*;
import java.util.stream.Collectors;

public class ConnectFourLogic {
    static int connectYellow = Arrays.asList(Disc.YELLOW, Disc.YELLOW, Disc.YELLOW, Disc.YELLOW).hashCode();
    static int connectRed = Arrays.asList(Disc.RED, Disc.RED, Disc.RED, Disc.RED).hashCode();
    public GridCell[][] grid;

    public ConnectFourLogic() {
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
            var row = new LinkedList<>(Arrays.asList(
                            grid[i]).stream()
                    .filter(Objects::nonNull)
                    .map(x -> x.disc).collect(Collectors.toList()));

            if (check4Matches(row)) {
                return true;
            }

        }
        return false;
    }

    public boolean check4Matches(List<Disc> row) {
        var i = 0;
        while (i + 4 <= row.size()) {
            var listOf4 = row.subList(i, 4 + i);
            var groupOfFour = listOf4.hashCode();
            if (groupOfFour == connectRed || groupOfFour == connectYellow) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean checkVertical() {
        for (int i = 0; i < grid[0].length; i++) {
            List<Disc> row = new LinkedList<>();
            for (int j = grid.length - 1; j > 0; j--) {
                row.add(Optional.ofNullable(grid[j][i])
                        .map(x -> x.disc)
                        .orElse(null));
            }

            if (check4Matches(row)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonal() {
        int WIDTH = grid[0].length;
        int HEIGHT = grid.length;
        var sideGrid = new GridCell[WIDTH * HEIGHT][WIDTH * HEIGHT];
        for (int k = 0; k <= WIDTH + HEIGHT - 2; k++) {
            System.out.println("k" + k);
            List<Disc> row = new LinkedList<>();
            for (int j = 0; j <= k; j++) {
                int i = k - j;
                if (i < HEIGHT && j < WIDTH) {
                    row.add(Optional.ofNullable(grid[i][j])
                            .map(x -> x.disc)
                            .orElse(null));
                    System.out.print(Optional.ofNullable(grid[i][j])
                            .map(x -> x.disc.toString())
                            .orElse("-") + " ");
                }
            }

            if (check4Matches(row)) {
                return true;
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
