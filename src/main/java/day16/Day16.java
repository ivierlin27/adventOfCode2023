package main.java.day16;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 7498 @ 7:38pm 12/16 (correct!)
public class Day16 {
    private static final String INPUT_FILE = "src/main/resources/day16/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day16/example_input.txt";

    public static long phase1() {
        MirrorMaze mirrorMaze = new MirrorMaze();
        processFile(mirrorMaze::loadLayout, INPUT_FILE);
        return mirrorMaze.start();
    }
}
