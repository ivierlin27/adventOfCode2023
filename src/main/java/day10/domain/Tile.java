package main.java.day10.domain;

public class Tile {
    public static final String NS_VERTICAL = "|";
    public static final String EW_HORIZONTAL = "-";
    public static final String NE_90_BEND = "L";
    public static final String NW_90_BEND = "J";
    public static final String SW_90_BEND = "7";
    public static final String SE_90_BEND = "F";
    public static final String GROUND = ".";
    public static final String STARTING = "S"; //unknown shape

    private final String value;
    private final int column;
    private final int row;

    public Tile(String value, int column, int row) {
        this.value = value;
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isOpenToWest() {
        return value.equals(EW_HORIZONTAL) ||
                value.equals(NW_90_BEND) ||
                value.equals(SW_90_BEND);
    }

    public boolean isOpenToEast() {
        return value.equals(EW_HORIZONTAL) ||
                value.equals(NE_90_BEND) ||
                value.equals(SE_90_BEND);
    }

    public boolean isOpenToNorth() {
        return value.equals(NS_VERTICAL) ||
                value.equals(NE_90_BEND) ||
                value.equals(NW_90_BEND);
    }

    public boolean isOpenToSouth() {
        return value.equals(NS_VERTICAL) ||
                value.equals(SE_90_BEND) ||
                value.equals(SW_90_BEND);
    }
}
