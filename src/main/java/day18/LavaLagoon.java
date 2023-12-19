package main.java.day18;

import main.java.day16.domain.Pos;
import main.java.domain.Input;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.abs;

public class LavaLagoon {
    private static final String RIGHT = "R";
    private static final String DOWN = "D";
    private static final String LEFT = "L";
    private static final String UP = "U";

    private final List<Triple<String, Integer, String>> lagoon = new ArrayList<>();

    public long processInstruction(Input input) {
        String[] parts = input.getCurrentLine().split(" ");
        lagoon.add(Triple.of(parts[0], Integer.parseInt(parts[1]), parts[2].substring(2, 8)));

        return 0;
    }

    public long lavaCapacity(Function<List<Triple<String, Integer, String>>, List<Pos>> digPlan) {
        return integerPointsFromPickTheorem(shoelaceFormula(cornerPositions(digPlan)), perimeter(digPlan));
    }

    public List<Pos> digPlan(List<Triple<String, Integer, String>> triples) {
        return triples.stream()
                .map(triple ->  multiplyPos(shiftDirection(triple.getLeft()), triple.getMiddle()))
                .toList();
    }

    private long perimeter(Function<List<Triple<String, Integer, String>>, List<Pos>> digPlan) {
        return digPlan.apply(lagoon).stream()
                .map(pos -> abs(pos.col()) + abs(pos.row()))
                .reduce(0, Integer::sum);
    }

    private Pos multiplyPos(Pos pos, int multiplier) {
        return new Pos((pos.col() * multiplier), (pos.row() * multiplier));
    }

    private Pos addPos(Pos pos, Pos otherPos) {
        return new Pos(pos.col() + otherPos.col(), pos.row() + otherPos.row());
    }

    private Pos shiftDirection(String direction) {
        return switch (direction) {
            case RIGHT -> new Pos(1, 0);
            case DOWN -> new Pos(0, 1);
            case LEFT -> new Pos(-1, 0);
            case UP -> new Pos(0, -1);
            default -> null;
        };
    }

    private List<Pos> cornerPositions(Function<List<Triple<String, Integer, String>>, List<Pos>> digPlan) {
        List<Pos> result = new ArrayList<>();
        List<Pos> values = digPlan.apply(lagoon);
        Pos currentPos = new Pos(0, 0);
        result.add(currentPos);
        for (int i = 0; i < values.size() - 1; i++) {
            currentPos = addPos(currentPos, values.get(i));
            result.add(currentPos);
        }
        return result;
    }

    private long integerPointsFromPickTheorem(long area, long perimeter) {
        return area + perimeter / 2L + 1L;
    }

    private long shoelaceFormula(List<Pos> corners) {
        int n = corners.size();
        int area = 0;
        for (int i = 0; i < n - 1; i++) {
            area += corners.get(i).col() * corners.get(i + 1).row() - corners.get(i + 1).col() * corners.get(i).row();
        }
        return area / 2;
    }
}
