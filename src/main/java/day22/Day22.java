package main.java.day22;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 181 @ 8:57am 12/24 (too low)
// Phase 1: 481 @ 9:34am 12/24 (too high)
// Phase 1: 405 @ 11:00am 12/24 (wrong)
// Phase 1: 389 @ 2:28pm 12/24 (correct!)
// Phase 2: 1212 @ 3:20pm 12/24 (too low)
// Phase 2: 71830 @ 3:25pm 12/24 (too high)
// Phase 2: 71506 @ 3:29pm 12/24 (too high)
// Phase 2: 70609 @ 6:41pm 12/24 (correct!)
public class Day22 {
    private static final String INPUT_FILE = "src/main/resources/day22/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day22/example_input.txt";

    public static long phase1() {
        Jenga jenga = new Jenga();
        processFile(jenga::loadSnapshot, INPUT_FILE);
        return jenga.calculateDestroyable();
    }

    public static long phase2() {
        Jenga jenga = new Jenga();
        processFile(jenga::loadSnapshot, INPUT_FILE);
        return jenga.calculateAllFalling();
    }
}
