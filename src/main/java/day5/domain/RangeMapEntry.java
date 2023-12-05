package main.java.day5.domain;

public class RangeMapEntry {
    long source;
    long destination;
    long rangeLength;

    public RangeMapEntry(String[] parts) {
        this.source = Long.parseLong(parts[1]);
        this.destination = Long.parseLong(parts[0]);
        this.rangeLength = Long.parseLong(parts[2]);
    }

    public long getSource() {
        return source;
    }

    public long getDestination() {
        return destination;
    }

    public long getRangeLength() {
        return rangeLength;
    }
}
