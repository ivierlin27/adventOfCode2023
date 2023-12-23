package main.java.day21;

import main.java.day21.domain.Direction;
import main.java.day9.Day9;
import main.java.day9.Phase1;
import main.java.domain.Input;
import main.java.domain.Pos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

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

    public long countPossibilities(long stepGoal) {
        return countPossibilities(stepGoal, this::calculatePossibleMoves);
    }

    private long countPossibilities(long stepGoal, BiConsumer<Pos, Set<Pos>> calculateMoves) {
        long steps = 0L;
        Set<Pos> current = new HashSet<>();
        current.add(startingPos);
        while (steps < stepGoal) {
            Set<Pos> next = new HashSet<>();
            for (Pos pos : current) {
                calculateMoves.accept(pos, next);
            }
            steps++;
            current = next;
        }
        return current.size();
    }

    public long countPossibilitiesInfiniteMap(long stepGoal, long offset) {
        long val = (stepGoal - offset) / layout.size();

        StringBuilder stringBuilder = new StringBuilder();
        List<List<Long>> diffs = new ArrayList<>();
        for (long i = 0; i <= val; i++) {
            if (i < 4) {
                if (i > 0) {
                    stringBuilder.append(" ");
                }
                long possibilitiesGoal = offset + (i * layout.size());
                stringBuilder.append(countPossibilities(possibilitiesGoal, this::calculatePossibleMovesInfinite));
            } else {
                if (diffs.isEmpty()) {
                    diffs = Day9.parseInput(new Input(0, stringBuilder.toString(), null));

                }
                Phase1.fillForwards(diffs);
            }
        }



        return diffs.getLast().getLast();
    }

    private void calculatePossibleMoves(Pos pos, Collection<Pos> path) {
        for (Direction direction : Direction.values()) {
            Pos p = direction.move(pos);
            if (p.inBounds(layout) && validWalkingTile(p)) {
                path.add(p);
            } else {
                if (!p.inBounds(layout)) {
                    System.out.println("out of bounds at " + pos);
                }
            }
        }
    }

    private void calculatePossibleMovesInfinite(Pos pos, Collection<Pos> path) {
        for (Direction direction : Direction.values()) {
            Pos p = direction.move(pos);
            int row = p.row() % layout.size();
            if (row < 0) {
                row += layout.size();
            }
            int col = p.col() % layout.getFirst().length();
            if (col < 0) {
                col += layout.getFirst().length();
            }
            if (validWalkingTile(new Pos(col, row))) {
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
