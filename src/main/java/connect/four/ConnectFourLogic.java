package connect.four;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ConnectFourLogic {
    static int connectYellow = Arrays.asList(Disc.YELLOW, Disc.YELLOW, Disc.YELLOW, Disc.YELLOW).hashCode();
    static int connectRed = Arrays.asList(Disc.RED, Disc.RED, Disc.RED, Disc.RED).hashCode();
    public GridCell[][] grid;

    public ConnectFourLogic() {
        grid = new GridCell[6][7];
    }

    public void playDisc(PlayInput playInput) {
        playDisc(playInput.disc, playInput.column);
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
    }


    public boolean checkHorizontal() {
        for (int i = 0; i < grid.length; i++) {
            var row = new LinkedList<>(Arrays.asList(
                            grid[i]).stream()
                    .map(x -> {
                        if(x != null){
                            return x.disc;
                        } else {
                            return null;
                        }
                    }).collect(Collectors.toList()));

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
        int width = grid[0].length;
        int height = grid.length;
        for (int k = 0; k <= width + height - 2; k++) {
            List<Disc> row = new LinkedList<>();
            for (int j = 0; j <= k; j++) {
                int i = k - j;
                if (i < height && j < width) {
                    row.add(Optional.ofNullable(grid[i][j])
                            .map(x -> x.disc)
                            .orElse(null));
                }
            }

            if (check4Matches(row)) {
                return true;
            }
        }

        return false;

    }

    public PlayInput convertInput(String input) {
        var split = input.split("_");
        return new PlayInput(ROW.valueOf(split[0]).getValue(), Disc.get(split[1]));
    }


}

enum Disc {
    RED("Red"), YELLOW("Yellow");
    private final String name;
    private static final Map<String,Disc> discMap;

    private Disc(String s) {
        name = s;
    }
    public String getName() {
        return this.name;
    }

    static {
        Map<String,Disc> map = new ConcurrentHashMap<String, Disc>();
        for (Disc instance : Disc.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        discMap = Collections.unmodifiableMap(map);
    }

    public static Disc get (String name) {
        return discMap.get(name.toLowerCase());
    }


}

class PlayInput {
    Disc disc;
    int column;

    public PlayInput(int column, Disc disc) {
        this.disc = disc;
        this.column = column;
    }
}

enum ROW {
    A(0), B(1), C(2), D(3), E(4), F(5), G(6);

    private final int value;

    private ROW(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
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
