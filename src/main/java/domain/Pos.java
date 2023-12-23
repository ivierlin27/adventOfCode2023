package main.java.domain;

import java.util.List;

public record Pos(int col, int row) {

    public boolean inBounds(List<String> layout) {
        return (row >= 0 && col >= 0 && row < layout.size() && col < layout.getFirst().length());
    }
    @Override
    public String toString() {
        return "Pos{" +
                col +
                ", " + row +
                '}';
    }
}
