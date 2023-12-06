package main.java.day4;

import main.java.day4.domain.Scratchcard;
import main.java.domain.Input;

import java.util.HashMap;
import java.util.Map;

import static main.java.day4.ScratchcardUtil.calculateNumberOfWinnings;
import static main.java.day4.ScratchcardUtil.parseScratchcard;

public class Phase2 {
    Map<Integer, Integer> cardNumberCountMap = new HashMap<>();
    protected long calculateNumberScratchcards(Input input) {
        Scratchcard scratchcard = parseScratchcard(input.getCurrentLine());
        int winningCount = calculateNumberOfWinnings(scratchcard);

        updateWonScratchcards(scratchcard.getCardNumber(), winningCount);

        int numCurrentCard = cardNumberCountMap.get(scratchcard.getCardNumber());
        if (numCurrentCard > 1) {
            for (int x = 0; x < numCurrentCard - 1; x++) {
                updateWonScratchcards(scratchcard.getCardNumber(), winningCount);
            }
        }

        return numCurrentCard;
    }

    private void updateWonScratchcards(int currentCardNumber, int winningCount) {
        for (int x = 0; x <= winningCount; x++) {
            int copyCardNumber = currentCardNumber + x;
            cardNumberCountMap.put(copyCardNumber,
                    cardNumberCountMap.containsKey(copyCardNumber)
                            ? cardNumberCountMap.get(copyCardNumber) + 1
                            : 1
            );
        }
    }
}
