package main.java.day23;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 2442 @ 9:12pm 12/24 (correct!)
public class Day23 {
    private static final String INPUT_FILE = "src/main/resources/day23/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day23/example_input.txt";
    public static long phase1() {
        PathFinder pathFinder = new PathFinder();
        processFile(pathFinder::loadMap, INPUT_FILE);
        return pathFinder.findLongestPath();
    }
}
