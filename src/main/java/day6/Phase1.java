package main.java.day6;

import main.java.domain.Input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phase1 {
    public static int race(Input input) {
        if (!input.isLastLine()) {
            List<Integer> times = Arrays.stream(input.getCurrentLine().split(":")[1].trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .toList();

            List<Integer> distances = Arrays.stream(input.getNextLine().split(":")[1].trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .toList();

            Map<Integer, Integer> gameMap = new HashMap<>();
            for (int x = 0; x < times.size(); x++) {
                gameMap.put(times.get(x), distances.get(x));
            }
            return gameMap.entrySet().stream().map(Phase1::calculateWins).reduce(1, Math::multiplyExact);
        }
        return 0;
    }

    protected static int calculateWins(Map.Entry<Integer, Integer> game) {
        int time = game.getKey();
        int distance = game.getValue();

        int winCount = 0;
        for (int x = 1; x < distance - 1; x++) {
            if (calculateDistance(time, x) > distance) {
                winCount++;
            }
        }
        return winCount;
    }

    private static int calculateDistance(int time, int hold) {
        return hold * (time - hold);
    }
}
