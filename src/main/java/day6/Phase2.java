package main.java.day6;

import main.java.domain.Input;

public class Phase2 {
    public static long race(Input input) {
        if (!input.isLastLine()) {
            long time = Long.parseLong(input.getCurrentLine().split(":")[1].trim().replace(" ", ""));
            long distance = Long.parseLong(input.getNextLine().split(":")[1].trim().replace(" ", ""));

            return calculateWins(time, distance);
        }
        return 0;
    }

    protected static long calculateWins(long time, long distance) {
        long winCount = 0;
        for (long x = 1; x < time - 1; x++) {
            long dist = calculateDistance(time, x);
            if (dist > distance) {
                winCount++;
            }
        }
        return winCount;
    }

    private static long calculateDistance(long time, long hold) {
        return hold * (time - hold);
    }
}
