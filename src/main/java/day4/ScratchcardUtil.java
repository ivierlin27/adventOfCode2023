package main.java.day4;

import main.java.day4.domain.Scratchcard;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class ScratchcardUtil {
    protected static Scratchcard parseScratchcard(String line) {
        String[] lineParts = line.split(":");
        String[] lineBodyParts = lineParts[1].split("\\|");
        return new Scratchcard(
                parseGameNumber(lineParts[0]),
                parseNumbers(lineBodyParts[0]),
                parseNumbers(lineBodyParts[1]));
    }

    protected static int calculateNumberOfWinnings(Scratchcard scratchcard) {
        return Math.toIntExact(scratchcard.getMyNumbers().stream()
                .filter(myNumber -> scratchcard.getWinningNumbers().contains(myNumber))
                .count());
    }

    private static int parseGameNumber(String cardHeader) {
        return Integer.parseInt(cardHeader.split("\\s+")[1]);
    }

    private static List<Integer> parseNumbers(String numbers) {
        return Arrays.stream(numbers.split(" ")).filter(StringUtils::isNoneEmpty).map(Integer::parseInt).toList();
    }
}
