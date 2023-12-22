package main.resources.day20.domain;

public final class Counter {
    private long low;
    private long high;

    public Counter(long low, long high) {
        this.low = low;
        this.high = high;
    }

    public void incrementLow() {
        low++;
    }

    public void incrementHigh() {
        high++;
    }

    public long score() {
        return low * high;
    }
}
