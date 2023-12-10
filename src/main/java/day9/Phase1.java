package main.java.day9;

import main.java.domain.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Phase1 {
    protected static long parseInput(Input input) {
        List<Long> initialInput = Arrays.stream(input.getCurrentLine().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        List<List<Long>> diffs = new ArrayList<>();
        diffs.add(initialInput);
        List<Long> diff = calculateDiff(initialInput);
        diffs.add(diff);
        while(!diff.stream().allMatch(value -> value == 0)) {
            diff = calculateDiff(diff);
            diffs.add(diff);
        }

        Collections.reverse(diffs);

        for (int x = 0; x < diffs.size(); x++) {
            diffs.get(x).add(calculateNextVal(diffs, x));
        }

        return diffs.getLast().getLast();
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

    private static List<Long> calculateDiff(List<Long> input) {
        List<Long> diff = new ArrayList<>();
        for (int x = 0; x < input.size() - 1; x++) {
            diff.add(input.get(x + 1) - input.get(x));
        }
        return diff;
    }
}
