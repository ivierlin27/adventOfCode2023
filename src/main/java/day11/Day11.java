package main.java.day11;

import main.java.day11.domain.Space;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 9536038 @ 4:58pm 12/11 (correct!)
// Phase 2: 447745088302 @ 6:52 12/11 (too high)
// Phase 2: 447744640566 @ 7:02 12/11 (correct!)
public class Day11 {
    private static final String INPUT_FILE = "src/main/resources/day11/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day11/example_input.txt";

    public static long phase1() {
        Space space = new Space();
        processFile(space::loadInputLine, INPUT_FILE);

        return space.calculateShortestPaths(1);
    }

    public static long phase2() {
        Space space = new Space();
        processFile(space::loadInputLine, INPUT_FILE);

        return space.calculateShortestPaths(1000000);
    }
}
