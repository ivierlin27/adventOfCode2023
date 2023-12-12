package main.java.day11;

import main.java.day11.domain.Space;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 9536038 @ 4:58pm 12/11 (correct!)
public class Day11 {
    private static final String INPUT_FILE = "src/main/resources/day11/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day11/example_input.txt";

    public static long phase1() {
        Space space = new Space();
        return processFile(space::loadInputLine, INPUT_FILE);
    }
}
