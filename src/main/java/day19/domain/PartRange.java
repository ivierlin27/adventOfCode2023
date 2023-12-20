package main.java.day19.domain;

public record PartRange(long[] min, long[] max) {
    public long combinations() {
        long result = 1;
        for (int i = 0; i < 4; i++) {
            result *= max[i] - min[i];
        }
        return result;
    }
}
