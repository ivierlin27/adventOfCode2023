package main.java.domain;

import java.util.List;
import java.util.Objects;

public record Pos(int col, int row) {

    public boolean inBounds(List<String> layout) {
        return (row >= 0 && col >= 0 && row < layout.size() && col < layout.getFirst().length());
    }

    public char getTileAtPos(List<String> layout) {
        return layout.get(row).charAt(col);
    }

    @Override
    public String toString() {
        return "Pos{" +
                col +
                ", " + row +
                '}';
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
