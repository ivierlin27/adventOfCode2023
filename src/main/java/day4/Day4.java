package main.java.day4;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 23750 at 10:36am (correct!)
// Phase 2: 13261850 at 11:25am (correct!)
public class Day4 {
    private static final String INPUT_FILE = "src/main/resources/day4/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day4/example_input.txt";
    public static int phase1() {
        return processFile(Phase1::calculateWinnings, INPUT_FILE);
    }

    public static int phase2() {
        Phase2 phase2 = new Phase2();
        return processFile(phase2::calculateNumberScratchcards, INPUT_FILE);
    }
}
