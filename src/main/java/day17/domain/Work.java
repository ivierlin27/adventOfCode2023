package main.java.day17.domain;

public record Work(State state, int heatLoss) implements Comparable<Work> {

    @Override
    public int compareTo(Work o) {
        return heatLoss - o.heatLoss;
    }
}
