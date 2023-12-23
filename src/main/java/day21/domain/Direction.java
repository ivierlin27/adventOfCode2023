package main.java.day21.domain;

import main.java.domain.Pos;

public enum Direction {
    EAST, SOUTH, WEST, NORTH;

    public Pos move(Pos currentPos) {
        return switch (this) {
            case EAST -> new Pos(currentPos.col() + 1, currentPos.row());
            case SOUTH -> new Pos(currentPos.col(), currentPos.row() + 1);
            case WEST -> new Pos(currentPos.col() - 1, currentPos.row());
            case NORTH -> new Pos(currentPos.col(), currentPos.row() - 1);
        };
    }
}
