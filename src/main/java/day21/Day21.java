package main.java.day21;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 3841 @ 8:09pm 12/22 (correct!)
// Phase 2: 5914961 @ 10:31am 12/23 (too low)
// Phase 2: 636385135182641 @ 11:21am 12/23 (too low)
// Phase 2: 636391426712747 @ 11:24am 12/23 (correct!)
public class Day21 {
    private static final String INPUT_FILE = "src/main/resources/day21/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day21/example_input.txt";

    public static long phase1() {
        Garden garden = new Garden();
        processFile(garden::loadMap, INPUT_FILE);
        return garden.countPossibilities(64);
    }

    public static long phase2() {
        Garden garden = new Garden();
        processFile(garden::loadMap, INPUT_FILE);
        return garden.countPossibilitiesInfiniteMap(26501365, 65);
    }
}
