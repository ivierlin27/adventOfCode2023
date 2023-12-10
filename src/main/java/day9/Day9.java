package main.java.day9;

import main.java.domain.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 1684566095 @ 8:39pm 12/9 (correct!)
// Phase 2: 1136 @ 9:10pm 12/9 (correct!)
public class Day9 {
    private static final String INPUT_FILE = "src/main/resources/day9/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day9/example_input.txt";

    public static long phase1() {
        return processFile(Phase1::parseInput, INPUT_FILE);
    }

    public static long phase2() {
        return processFile(Phase2::parseInput, INPUT_FILE);
    }

    public static List<List<Long>> parseInput(Input input) {
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

        return diffs;
    }

    private static List<Long> calculateDiff(List<Long> input) {
        List<Long> diff = new ArrayList<>();
        for (int x = 0; x < input.size() - 1; x++) {
            diff.add(input.get(x + 1) - input.get(x));
        }
        return diff;
    }
}
