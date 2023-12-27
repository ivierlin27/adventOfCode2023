package main.java.day25;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 601344 @ 2:32pm 12/27 (correct!)
public class Day25 {
    private static final String INPUT_FILE = "src/main/resources/day25/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day25/example_input.txt";

    public static long phase1() {
        Apparatus apparatus = new Apparatus();
        return processFile(apparatus::processInput, INPUT_FILE);
    }
}
