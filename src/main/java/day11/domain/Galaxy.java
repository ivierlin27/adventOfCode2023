package main.java.day11.domain;

import java.util.Objects;

public class Galaxy {
    int column;
    int row;

    public Galaxy(int column, int row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public String toString() {
        return "(" + column +
                ", " + row +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Galaxy galaxy = (Galaxy) o;
        return column == galaxy.column && row == galaxy.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
