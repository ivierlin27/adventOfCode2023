package main.java.day2.domain;

import java.util.List;

public class Game {
    int number;

    List<Round> rounds;

    public Game(int number, List<Round> rounds) {
        this.number = number;
        this.rounds = rounds;
    }

    public int getNumber() {
        return this.number;
    }

    public List<Round> getRounds() {
        return rounds;
    }
}
