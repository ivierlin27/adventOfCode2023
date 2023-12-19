package main.java.day18;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 1844 @ 7:59pm 12/18 (too low)
// Phase 1: 49578 @ 9:41pm 12/18 (correct!)
public class Day18 {
    private static final String INPUT_FILE = "src/main/resources/day18/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day18/example_input.txt";

    public static long phase1() {
        LavaLagoon lavaLagoon = new LavaLagoon();
        processFile(lavaLagoon::processInstruction, INPUT_FILE);
        return lavaLagoon.lavaCapacity(lavaLagoon::digPlan);
    }
}
