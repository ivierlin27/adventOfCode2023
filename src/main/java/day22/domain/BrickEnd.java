package main.java.day22.domain;

public record BrickEnd(long x, long y, long z) {
    public BrickEnd(String[] parts) {
        this(Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2]));
    }

    @Override
    public String toString() {
        return "{" +
                 x +
                ", " + y +
                ", " + z +
                '}';
    }
}
