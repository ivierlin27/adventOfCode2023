package main.java.day24.domain;

public record HailStone(long x, long y, long z, long vx, long vy, long vz) {
    public double getM() {
        long x2 = x + vx;
        long y2 = y + vy;
        return (double) (y - y2) / (x - x2);
    }

    public double getC() {
        return -(getM() * x - y);
    }
}
