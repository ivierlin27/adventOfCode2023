package main.java.day10;

import main.java.day10.domain.Step;
import main.java.day10.domain.Tile;

import java.util.ArrayList;
import java.util.List;

import static main.java.day10.domain.Tile.NS_VERTICAL;
import static main.java.day10.domain.Tile.SE_90_BEND;
import static main.java.day10.domain.Tile.SW_90_BEND;

public class Phase2 {
    public static long countEnclosedTiles(PipeMaze pipeMaze) {
        List<Tile> path = new ArrayList<>();
        path.add(pipeMaze.getStart());

        Step step = pipeMaze.takeStep(pipeMaze.getStart(), "Not a direction");
        path.add(step.getCurrentTile());
        while (pipeMaze.currentPositionNotStart(step.getCurrentTile())) {
            step = pipeMaze.takeStep(step.getCurrentTile(), step.getFromDirection());
            path.add(step.getCurrentTile());
        }

        int enclosedTileCount = 0;
        boolean inside = false;
        for (int row = 0; row < pipeMaze.getGridRowSize(); row++) {
            for (int column = 0; column < pipeMaze.getGridColumnSize(); column++) {
                if (tileInPathIsConnector(column, row, path)) {
                    inside = !inside;
                } else if (tileNotInPath(column, row, path) && inside) {
                    enclosedTileCount++;
                }
            }
        }

        return enclosedTileCount;
    }

    private static boolean tileNotInPath(int column, int row, List<Tile> path) {
        return path.stream().noneMatch(tile -> tile.getRow() == row && tile.getColumn() == column);
    }

    private static boolean tileInPathIsConnector(int column, int row, List<Tile> path) {
        return path.stream()
                .filter(tile -> tile.getValue().equals(NS_VERTICAL) || tile.getValue().equals(SW_90_BEND) || tile.getValue().equals(SE_90_BEND))
                .anyMatch(tile -> tile.getRow() == row && tile.getColumn() == column);
    }
}
