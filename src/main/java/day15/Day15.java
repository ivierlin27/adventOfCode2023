package main.java.day15;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 517965 @ 8:50pm 12/15 (correct!)
public class Day15 {
    private static final String INPUT_FILE = "src/main/resources/day15/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day15/example_input.txt";
    public static long phase1() {
        return processFile(Magnifier::initializeSequence, INPUT_FILE);
    }
}
