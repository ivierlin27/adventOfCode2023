package main.java.day14;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 107951 @ 8:44pm 12/14 (correct!)
// Phase 2: 24860 @ 5:47am 12/15 (too low)
// Phase 2: 95769 @ 4:37pm 12/15 (too high)
// Phase 2: 95968 @ 4:44pm 12/15 (wrong!)
// Phase 2: 95889 @ 5:31pm 12/15 (wrong!)
// Phase 2: 95748 @ 7:38pm 12/15 (wrong!)
// Phase 2: 95736 @ 8:07pm 12/15 (correct!)
public class Day14 {
    private static final String INPUT_FILE = "src/main/resources/day14/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day14/example_input.txt";
    public static long phase1() {
        ReflectorDish reflectorDish = new ReflectorDish();
        processFile(reflectorDish::loadBoard, INPUT_FILE);
        reflectorDish.tiltNorth();
        return reflectorDish.calculateLoad();
    }

    public static long phase2() {
        ReflectorDish reflectorDish = new ReflectorDish();
        processFile(reflectorDish::loadBoard, INPUT_FILE);

        reflectorDish.cycle(1000000000);
        return reflectorDish.calculateLoad();
    }
}
