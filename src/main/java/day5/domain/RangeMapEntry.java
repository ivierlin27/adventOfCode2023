package main.java.day5.domain;

public class RangeMapEntry {
    long destination;
    long source;
    long rangeLength;

    public RangeMapEntry(String[] parts) {
        this.destination = Long.parseLong(parts[0]);
        this.source = Long.parseLong(parts[1]);
        this.rangeLength = Long.parseLong(parts[2]);
    }

    public long getDestination() {
        return destination;
    }

    public long getSource() {
        return source;
    }

    public long getRangeLength() {
        return rangeLength;
    }
}
