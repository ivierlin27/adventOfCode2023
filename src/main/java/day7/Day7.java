package main.java.day7;

import main.java.day7.domain.Hand;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 246409899 @ 3:24pm 12/9 (correct!)
// Phase 2: 244848487 @ 4:14pm 12/9
public class Day7 {
    private static final String INPUT_FILE = "src/main/resources/day7/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day7/example_input.txt";
    public static long phase1() {
        Phase1 phase1 = new Phase1();
        processFile(phase1::parseInput, INPUT_FILE);
        return calculateWinnings(phase1.getHands());
    }

    public static long phase2() {
        Phase2 phase2 = new Phase2();
        processFile(phase2::parseInput, INPUT_FILE);
        return calculateWinnings(phase2.getHands());
    }

    private static long calculateWinnings(List<Hand> hands) {
        hands.sort(Collections.reverseOrder());
        return IntStream.range(0, hands.size())
                .mapToObj(i -> hands.get(i).getBid() * (i + 1))
                .reduce(0, Integer::sum);
    }
}
