package main.java.day4;

import main.java.domain.Input;
import main.java.day4.domain.Scratchcard;

import static main.java.day4.ScratchcardParser.parseScratchcard;

public class Phase1 {
    protected static int calculateWinnings(Input input) {
        Scratchcard scratchcard = parseScratchcard(input.getCurrentLine());
        return processScratchcard(scratchcard);
    }

    private static int processScratchcard(Scratchcard scratchcard) {
        return calculateDoublings(Math.toIntExact(scratchcard.getMyNumbers().stream()
                .filter(myNumber -> scratchcard.getWinningNumbers().contains(myNumber))
                .count()));
    }

    private static int calculateDoublings(int winningCount) {
        return (int) Math.pow(2, winningCount - 1);
    }
}
