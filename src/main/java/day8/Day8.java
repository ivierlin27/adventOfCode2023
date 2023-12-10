package main.java.day8;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 18673 @ 5:15pm 12/9
public class Day8 {
    private static final String INPUT_FILE = "src/main/resources/day8/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day8/example_input.txt";
    public static long phase1() {
        Phase1 phase1 = new Phase1();
        processFile(phase1::parseInput, INPUT_FILE);
        return phase1.followPath();
    }
}
