package main.java.day21;

import main.java.day21.domain.Direction;
import main.java.domain.Input;
import main.java.domain.Pos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Garden {

    public static final String START = "S";
    public static final char GARDEN_PLOT = '.';
    List<String> layout = new ArrayList<>();
    Pos startingPos;
    int rowNum = 0;

    public long loadMap(Input input) {
        layout.add(input.getCurrentLine());
        if (input.getCurrentLine().contains(START)) {
            startingPos = new Pos(input.getCurrentLine().indexOf(START), rowNum);
        }
        rowNum++;
        return 0;
    }

    public long countPossibilities() {
        long steps = 0L;
        Set<Pos> current = new HashSet<>();
        current.add(startingPos);
        while (steps < 64) {
            Set<Pos> next = new HashSet<>();
            for (Pos pos : current) {
                calculatePossibleMoves(pos, next);
            }
            steps++;
            current = next;
        }
        return current.size();
    }

    private void calculatePossibleMoves(Pos pos, Collection<Pos> path) {
        for (Direction direction : Direction.values()) {
            Pos p = direction.move(pos);
            if (p.inBounds(layout) && validWalkingTile(p)) {
                path.add(p);
            }
        }
    }

    private boolean validWalkingTile(Pos pos) {
        return getTileAtPos(pos) == GARDEN_PLOT || pos.equals(startingPos);
    }

    private char getTileAtPos(Pos pos) {
        return layout.get(pos.row()).charAt(pos.col());
    }
}
