package main.java.day16.domain;

public record Pos(int col, int row) {

    @Override
    public String toString() {
        return "Pos{" +
                col +
                ", " + row +
                '}';
    }
}
