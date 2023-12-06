package main.java.day6;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 2612736 @ 9:41pm 12/5 (correct!)
// Phase 2: 29891250 @ 10:25am 12/6 (correct!)
public class Day6 {
    private static final String INPUT_FILE = "src/main/resources/day6/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day6/example_input.txt";
    public static long phase1() {
        return processFile(Phase1::race, INPUT_FILE);
    }

    public static long phase2() {
        return processFile(Phase2::race, INPUT_FILE);
    }

}
