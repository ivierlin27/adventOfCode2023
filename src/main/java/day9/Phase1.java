package main.java.day9;

import main.java.domain.Input;

import java.util.List;

public class Phase1 {
    protected static long parseInput(Input input) {
        List<List<Long>> diffs = Day9.parseInput(input);

        fillForwards(diffs);

        return diffs.getLast().getLast();
    }

    public static void fillForwards(List<List<Long>> diffs) {
        for (int x = 0; x < diffs.size(); x++) {
            diffs.get(x).add(calculateNextVal(diffs, x));
        }
    }

    private static long calculateNextVal(List<List<Long>> diffs, int x) {
        List<Long> tmp = diffs.get(x);
        long lastVal = tmp.getLast();
        if (lastVal == 0) {
            return 0;
        } else {
            return lastVal + diffs.get(x - 1).getLast();
        }
    }
}
