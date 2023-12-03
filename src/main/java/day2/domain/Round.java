package main.java.day2.domain;

import java.util.List;

public class Round {
    List<Stone> stones;

    public Round(List<Stone> stones) {
        this.stones = stones;
    }

    public List<Stone> getStones() {
        return stones;
    }
}
