package main.java.day17.domain;

import main.java.day16.domain.Pos;

import java.util.Objects;

public record State(Pos location, char direction, int singleDirection) {
    public static final char LEFT = '<';
    public static final char RIGHT = '>';
    public static final char UP = '^';
    public static final char DOWN = 'v';

    public State next(char nextDirection) {
        return new State(getNextPos(this.location, nextDirection),
                nextDirection,
                this.direction == nextDirection ? singleDirection + 1 : 1);
    }

    private Pos getNextPos(Pos pos, char direction) {
        return switch (direction) {
            case UP -> new Pos(pos.getCol(), pos.getRow() - 1);
            case DOWN -> new Pos(pos.getCol(), pos.getRow() + 1);
            case LEFT -> new Pos(pos.getCol() - 1, pos.getRow());
            default -> new Pos(pos.getCol() + 1, pos.getRow());
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return direction == state.direction && Objects.equals(location, state.location);
    }

}
