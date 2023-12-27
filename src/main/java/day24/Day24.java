package main.java.day24;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 12169 @ 2:28pm 12/26 (too low)
// Phase 1: 20847 @ 5:25pm 12/26 (correct!)
public class Day24 {
    private static final String INPUT_FILE = "src/main/resources/day24/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day24/example_input.txt";
    public static long phase1() {
        Hailstorm hailstorm = new Hailstorm();
        processFile(hailstorm::parseInput, INPUT_FILE);
        return hailstorm.detectIntersections(200000000000000L, 400000000000000L);
    }
}
