package main.java.day10.domain;

public class Step {
    Tile currentTile;
    Tile previousTile;
    String fromDirection;

    public Step(Tile currentTile, Tile previousTile, String fromDirection) {
        this.currentTile = currentTile;
        this.previousTile = previousTile;
        this.fromDirection = fromDirection;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public String getFromDirection() {
        return fromDirection;
    }
}
