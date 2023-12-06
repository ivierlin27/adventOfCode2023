package main.java.day4;

import main.java.domain.Input;
import main.java.day4.domain.Scratchcard;

import static main.java.day4.ScratchcardUtil.calculateNumberOfWinnings;
import static main.java.day4.ScratchcardUtil.parseScratchcard;

public class Phase1 {
    protected static long calculateWinnings(Input input) {
        Scratchcard scratchcard = parseScratchcard(input.getCurrentLine());
        int winningCount = calculateNumberOfWinnings(scratchcard);
        return calculateDoublings(winningCount);
    }

    private static int calculateDoublings(int winningCount) {
        return (int) Math.pow(2, winningCount - 1);
    }
}
