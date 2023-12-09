package main.java.day7;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 246409899 @ 3:24pm 12/9 (correct!)
public class Day7 {
    private static final String INPUT_FILE = "src/main/resources/day7/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day7/example_input.txt";
    public static long phase1() {
        Phase1 phase1 = new Phase1();
        processFile(phase1::parseInput, INPUT_FILE);
        return phase1.calculateWinnings();
    }
}
