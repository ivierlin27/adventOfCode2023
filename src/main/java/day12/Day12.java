package main.java.day12;

import main.java.domain.Input;

import java.util.Arrays;
import java.util.List;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 7195 @ 7:44pm 12/12
public class Day12 {
    private static final String INPUT_FILE = "src/main/resources/day12/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day12/example_input.txt";

    public static long phase1() {
        return processFile(Day12::loadInputLine, INPUT_FILE);
    }

    public static long loadInputLine(Input input) {
        String[] parts = input.getCurrentLine().split(" ");
        String puzzle = parts[0];
        List<Integer> keys = Arrays.stream(parts[1].split(",")).map(Integer::parseInt).toList();

        Phase1 phase1 = new Phase1();
        return phase1.calculateVariations(puzzle, keys);
    }
}
