package main.java.day21.domain;

import main.java.domain.Pos;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Direction {
    EAST, SOUTH, WEST, NORTH;

    public static Pos move(Pos currentPos, Direction direction) {
        return switch (direction) {
            case EAST -> new Pos(currentPos.col() + 1, currentPos.row());
            case SOUTH -> new Pos(currentPos.col(), currentPos.row() + 1);
            case WEST -> new Pos(currentPos.col() - 1, currentPos.row());
            case NORTH -> new Pos(currentPos.col(), currentPos.row() - 1);
        };
    }

    public static Set<Pos> getPosAtEachCardinalNeighbor(Pos pos) {
        return Arrays.stream(Direction.values()).map(direction -> move(pos, direction)).collect(Collectors.toSet());
    }

    public static Direction getDirectionBetweenPoints(Pos p1, Pos p2) {
        if (move(p1, NORTH).equals(p2)) {
            return NORTH;
        }
        if (move(p1, EAST).equals(p2)) {
            return EAST;
        }
        if (move(p1, SOUTH).equals(p2)) {
            return SOUTH;
        }
        if (move(p1, WEST).equals(p2)) {
            return WEST;
        }
        throw new IllegalStateException("points are not adjacent");
    }
}
