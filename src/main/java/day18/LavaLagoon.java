package main.java.day18;

import main.java.day18.domain.Vector;
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

    private final List<Triple<String, Long, String>> lagoon = new ArrayList<>();

    public long processInstruction(Input input) {
        String[] parts = input.getCurrentLine().split(" ");
        lagoon.add(Triple.of(parts[0], Long.parseLong(parts[1]), parts[2].substring(2, 8)));

        return 0;
    }

    public long lavaCapacity(Function<List<Triple<String, Long, String>>, List<Vector>> digPlan) {
        return longPointsFromPickTheorem(shoelaceFormula(cornerVectoritions(digPlan)), perimeter(digPlan));
    }

    public List<Vector> digPlan(List<Triple<String, Long, String>> triples) {
        return triples.stream()
                .map(triple ->  multiplyVector(shiftDirection(triple.getLeft()), triple.getMiddle()))
                .toList();
    }

    public List<Vector> colorsDigPlan(List<Triple<String, Long, String>> triples) {
        return triples.stream()
                .map(triple -> multiplyVector(
                        shiftDirection(extractLast(triple.getRight())), extractHexToLong(triple.getRight())))
                .toList();
    }

    private String extractLast(String value) {
        return value.substring(value.length() - 1);
    }

    private long extractHexToLong(String value) {
        String withoutLast = value.substring(0, value.length() - 1);
        return Long.parseLong(withoutLast, 16);
    }

    private long perimeter(Function<List<Triple<String, Long, String>>, List<Vector>> digPlan) {
        return digPlan.apply(lagoon).stream()
                .map(Vector -> abs(Vector.col()) + abs(Vector.row()))
                .reduce(0L, Long::sum);
    }

    private Vector multiplyVector(Vector vector, long multiplier) {
        return new Vector((vector.col() * multiplier), (vector.row() * multiplier));
    }

    private Vector addVector(Vector vector, Vector otherVector) {
        return new Vector(vector.col() + otherVector.col(), vector.row() + otherVector.row());
    }

    private Vector shiftDirection(String direction) {
        return switch (direction) {
            case "0", RIGHT -> new Vector(1, 0);
            case "1", DOWN -> new Vector(0, 1);
            case "2", LEFT -> new Vector(-1, 0);
            case "3", UP -> new Vector(0, -1);
            default -> null;
        };
    }

    private List<Vector> cornerVectoritions(Function<List<Triple<String, Long, String>>, List<Vector>> digPlan) {
        List<Vector> result = new ArrayList<>();
        List<Vector> values = digPlan.apply(lagoon);
        Vector currentVector = new Vector(0, 0);
        result.add(currentVector);
        for (int i = 0; i < values.size() - 1; i++) {
            currentVector = addVector(currentVector, values.get(i));
            result.add(currentVector);
        }
        return result;
    }

    private long longPointsFromPickTheorem(long area, long perimeter) {
        return area + perimeter / 2L + 1L;
    }

    private long shoelaceFormula(List<Vector> corners) {
        int n = corners.size();
        long area = 0;
        for (int i = 0; i < n - 1; i++) {
            area += corners.get(i).col() * corners.get(i + 1).row() - corners.get(i + 1).col() * corners.get(i).row();
        }
        return area / 2;
    }
}
