package main.java.day9;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 1684566095 @ 8:39pm 12/9
public class Day9 {
    private static final String INPUT_FILE = "src/main/resources/day9/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day9/example_input.txt";

    public static long phase1() {
        return processFile(Phase1::parseInput, INPUT_FILE);
    }
}
