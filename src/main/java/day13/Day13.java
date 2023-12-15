package main.java.day13;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 37113 @ 8:58pm 12/13 (correct!)
// Phase 2: 30449 @ 6:56pm 12/14 (correct!)
public class Day13 {
    private static final String INPUT_FILE = "src/main/resources/day13/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day13/example_input.txt";
    public static long phase1() {
        Valley valley = new Valley();
        processFile(valley::processLine, INPUT_FILE);
        return valley.summarizePatterns();
    }

    public static long phase2() {
        Valley valley = new Valley();
        processFile(valley::processLine, INPUT_FILE);
        return valley.smudgePatterns();
    }


}
