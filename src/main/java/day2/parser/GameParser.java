package main.java.day2.parser;

import main.java.day2.domain.Game;
import main.java.day2.domain.Round;
import main.java.day2.domain.Stone;
import main.java.day2.domain.StoneColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameParser {
    public static Game parseGame(String line) {
        String[] strings = line.split("[:;]");
        return new Game(
                extractGameNumber(strings[0]),
                extractRounds(strings)
        );
    }

    private static int extractGameNumber(String string) {
        return Integer.parseInt(string.substring(5));
    }

    private static List<Round> extractRounds(String[] strings) {
        return Arrays.stream(removeGameHeader(strings))
                .map(GameParser::roundFromString)
                .collect(Collectors.toList());
    }

    private static Round roundFromString(String string) {
        return new Round(Arrays.stream(string.split(","))
                .map(GameParser::stoneFromString)
                .collect(Collectors.toList())
        );
    }

    private static Stone stoneFromString(String string) {
        String[] stoneParts = string.trim().split(" ");
        return new Stone(
                StoneColor.valueOf(stoneParts[1].toUpperCase()),
                Integer.parseInt(stoneParts[0])
        );
    }

    private static String[] removeGameHeader(String[] strings) {
        return Arrays.copyOfRange(strings, 1, strings.length);
    }
}
