package main.java.day21;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 3841 @ 8:09pm 12/22 (correct!)
public class Day21 {
    private static final String INPUT_FILE = "src/main/resources/day21/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day21/example_input.txt";

    public static long phase1() {
        Garden garden = new Garden();
        processFile(garden::loadMap, INPUT_FILE);
        return garden.countPossibilities();
    }
}
