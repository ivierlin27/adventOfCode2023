package main.java.day9;

import main.java.domain.Input;

import java.util.List;

public class Phase2 {
    public static long parseInput(Input input) {
        List<List<Long>> diffs = Day9.parseInput(input);

        fillBackwards(diffs);

        return diffs.getLast().getFirst();
    }

    public static void fillBackwards(List<List<Long>> diffs) {
        for (int x = 0; x < diffs.size(); x++) {
            diffs.get(x).add(0, calculatePreviousVal(diffs, x));
        }
    }

    private static long calculatePreviousVal(List<List<Long>> diffs, int x) {
        List<Long> tmp = diffs.get(x);
        long firstVal = tmp.getFirst();
        if (tmp.stream().allMatch(val -> val == 0)) {
            return 0;
        } else {
            return firstVal - diffs.get(x - 1).getFirst();
        }
    }
}
