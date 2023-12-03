package main.java.day2;

import main.java.day2.domain.Game;
import main.java.day2.domain.Round;
import main.java.day2.domain.Stone;
import main.java.day2.domain.StoneColor;

import java.util.Map;

public class Validator {

    private static final Map<String, Integer> MAX_VALUES = Map.of(
            StoneColor.RED.name(), 12,
            StoneColor.GREEN.name(), 13,
            StoneColor.BLUE.name(), 14
    );
    public static boolean isValid(Game game) {
        return game.getRounds().stream().allMatch(Validator::validateRound);
    }

    private static boolean validateRound(Round round) {
        return round.getStones().stream().allMatch(Validator::validateStone);
    }

    private static boolean validateStone(Stone stone) {
        return stone.getCount() <= MAX_VALUES.get(stone.getColor().name());
    }
}
