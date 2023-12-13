package main.java.day12;

import main.java.domain.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.java.FileProcessorUtil.processFile;
import static main.java.day12.SpringEvaluator.UNKNOWN;

// Phase 1: 7195 @ 7:44pm 12/12 (correct!)
// Phase 2: 1339458939 @ 8:06pm 12/12 (too low)
// Phase 2: 33992866292225 @ 8:16pm 12/12 (correct!)
public class Day12 {
    private static final String INPUT_FILE = "src/main/resources/day12/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day12/example_input.txt";

    public static long phase1() {
        return processFile(Day12::loadInputLine, INPUT_FILE);
    }

    public static long phase2() {
        return processFile(Day12::loadInputLinePhase2, INPUT_FILE);
    }

    private static long loadInputLine(Input input) {
        String[] parts = input.getCurrentLine().split(" ");
        String puzzle = parts[0];
        List<Integer> keys = Arrays.stream(parts[1].split(",")).map(Integer::parseInt).toList();

        SpringEvaluator springEvaluator = new SpringEvaluator();
        return springEvaluator.calculateVariations(puzzle, keys);
    }

    private static long loadInputLinePhase2(Input input) {
        String[] parts = input.getCurrentLine().split(" ");
        String puzzle = unfoldPuzzle(parts[0]);
        List<Integer> keys = unfoldKeys(Arrays.stream(parts[1].split(",")).map(Integer::parseInt).toList());

        SpringEvaluator springEvaluator = new SpringEvaluator();
        return springEvaluator.calculateVariations(puzzle, keys);
    }

    private static String unfoldPuzzle(String puzzle) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(puzzle);
            stringBuilder.append(UNKNOWN);
        }
        stringBuilder.append(puzzle);
        return stringBuilder.toString();
    }

    private static List<Integer> unfoldKeys(List<Integer> keys) {
        List<Integer> unfoldedKeys = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            unfoldedKeys.addAll(keys);
        }
        return unfoldedKeys;
    }
}
