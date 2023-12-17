package main.java.day16.domain;

import java.util.Objects;

public class Pos {
    private int col;
    private int row;

    public Pos(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pos pos = (Pos) o;
        return col == pos.col && row == pos.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }
}
