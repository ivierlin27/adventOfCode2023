package main.java.day2;

import main.java.day2.domain.Game;
import main.java.day2.domain.Stone;
import main.java.day2.domain.StoneColor;

import java.util.HashMap;
import java.util.Map;

public class PowerCalculator {
    public static int calculateGamePower(Game game) {
        Map<StoneColor, Integer> map = new HashMap<>();
        game.getRounds().stream()
                .flatMap(round -> round.getStones().stream())
                .forEach(stone -> updateMapWithStone(stone, map));
        return calculatePower(map);
    }

    private static void updateMapWithStone(Stone stone, Map<StoneColor, Integer> map) {
        if (map.containsKey(stone.getColor())) {
            int currentMax = map.get(stone.getColor());
            if (stone.getCount() > currentMax) {
                map.put(stone.getColor(), stone.getCount());
            }
        } else {
            map.put(stone.getColor(), stone.getCount());
        }
    }

    private static int calculatePower(Map<StoneColor, Integer> map) {
        return map.values().stream()
                .reduce(1, (subtotal, element) -> subtotal * element);
    }
}
