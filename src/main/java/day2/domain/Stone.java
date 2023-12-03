package main.java.day2.domain;

public class Stone {
    StoneColor color;
    int count;

    public Stone(StoneColor color, int count) {
        this.color = color;
        this.count = count;
    }

    public StoneColor getColor() {
        return color;
    }

    public int getCount() {
        return count;
    }
}
