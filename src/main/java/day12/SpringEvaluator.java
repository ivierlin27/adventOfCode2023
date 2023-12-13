package main.java.day12;

import main.java.day12.domain.Params;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringEvaluator {
    public static final Character OPERATIONAL = '.';
    public static final Character DAMAGED = '#';
    public static final Character UNKNOWN = '?';

    private static final Map<Params, Long> cache = new HashMap<>();

    public long calculateVariations(String puzzle, List<Integer> keys) {
        Params params = new Params(puzzle, keys);
        if (cache.containsKey(params)) {
            return cache.get(params);
        }

        if (puzzle.isBlank()) {
            return keys.isEmpty() ? 1 : 0;
        }

        char firstChar = puzzle.charAt(0);
        long cnt = 0;
        if (firstChar == OPERATIONAL) {
            cnt = calculateVariations(puzzle.substring(1), keys);
        } else if (firstChar == UNKNOWN) {
            cnt = calculateVariationsOnBothPaths(puzzle, keys);
        } else {
            if (!keys.isEmpty()) {
                int numDamaged = keys.getFirst();
                if (numDamaged <= puzzle.length() && numDamagedContainsAllDamagedOrUnknown(puzzle, numDamaged)) {
                    List<Integer> newKeys = keys.subList(1, keys.size());
                    if (numDamaged == puzzle.length()) {
                        cnt = newKeys.isEmpty() ? 1 : 0;
                    } else if (puzzle.charAt(numDamaged) == OPERATIONAL) {
                        cnt = calculateVariations(puzzle.substring(numDamaged + 1), newKeys);
                    } else if (puzzle.charAt(numDamaged) == UNKNOWN) {
                        cnt = calculateVariations(OPERATIONAL + puzzle.substring(numDamaged + 1), newKeys);
                    }
                }
            }
        }
        cache.put(params, cnt);
        return cnt;
    }

    private static boolean numDamagedContainsAllDamagedOrUnknown(String puzzle, int numDamaged) {
        return puzzle.chars().limit(numDamaged).allMatch(character -> character == DAMAGED || character == UNKNOWN);
    }

    private long calculateVariationsOnBothPaths(String puzzle, List<Integer> keys) {
        return calculateVariations(OPERATIONAL + puzzle.substring(1), keys) +
                calculateVariations(DAMAGED + puzzle.substring(1), keys);
    }
}
