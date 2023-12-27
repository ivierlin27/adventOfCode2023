package main.java.day24;

import main.java.day24.domain.HailStone;
import main.java.day24.domain.IntersectionPoint;
import main.java.domain.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Hailstorm {
    List<HailStone> hailStones = new ArrayList<>();

    public long parseInput(Input input) {
        String[] parts = input.getCurrentLine().split(" @ ");
        List<Long> cords = Arrays.stream(parts[0].split(", ")).map(s -> Long.parseLong(s.trim())).toList();
        List<Long> velocity = Arrays.stream(parts[1].split(", ")).map(s -> Long.parseLong(s.trim())).toList();
        HailStone hailStone = new HailStone(cords.getFirst(), cords.get(1), cords.get(2), velocity.getFirst(), velocity.get(1), velocity.get(2));
        hailStones.add(hailStone);
        return 0;
    }

    public long detectIntersections(long rangeStart, long rangeEnd) {
        long intersections = 0;
        for (int i = 0; i < hailStones.size() - 1; i++) {
            for (int j = i + 1; j < hailStones.size(); j++) {
                Optional<IntersectionPoint> intersectionPoint = intersectionWithoutZ(hailStones.get(i), hailStones.get(j));
                if (intersectionPoint.isPresent() && intersectionInRange(intersectionPoint.get(), rangeStart, rangeEnd)) {
                    intersections++;
                }
            }
        }
        return intersections;
    }

    public long calculatePosition() {
        HailStone pos = findThrowingLocation();

        return pos.x() + pos.y() + pos.z();
    }

    private HailStone findThrowingLocation() {
        double[] rhs = calculateRhsForXYVxVy();

        long x = Math.round(rhs[0]);
        long y = Math.round(rhs[1]);
        long vx = Math.round(rhs[2]);
        long vy = Math.round(rhs[3]);

        rhs = calculateRhsForZVz(x, vx);

        long z = Math.round(rhs[0]);
        long vz = Math.round(rhs[1]);

        return new HailStone(x, y, z, vx, vy, vz);
    }

    private double[] calculateRhsForXYVxVy() {
        double[][] coefficients = new double[4][4];
        double[] rhs = new double[4];

        for (int i = 0; i < 4; i++) {
            HailStone h1 = hailStones.get(i);
            HailStone h2 = hailStones.get(i + 1);
            coefficients[i][0] = h2.vy() - h1.vy();
            coefficients[i][1] = h1.vx() - h2.vx();
            coefficients[i][2] = h1.y() - h2.y();
            coefficients[i][3] = h2.x() - h1.x();
            rhs[i] = -h1.x() * h1.vy() + h1.y() * h1.vx() + h2.x() * h2.vy() - h2.y() * h2.vx();
        }

        gaussianElimination(coefficients, rhs);
        return rhs;
    }

    private double[] calculateRhsForZVz(long x, long vx) {
        double[][] coefficients = new double[2][2];
        double[] rhs = new double[2];
        for (int i = 0; i < 2; i++) {
            HailStone h1 = hailStones.get(i);
            HailStone h2 = hailStones.get(i + 1);
            coefficients[i][0] = h1.vx() - h2.vx();
            coefficients[i][1] = h2.x() - h1.x();
            rhs[i] = -h1.x() * h1.vz() + h1.z() * h1.vx() + h2.x() * h2.vz() - h2.z() * h2.vx()
                    - ((h2.vz() - h1.vz()) * x) - ((h1.z() - h2.z()) * vx);
        }

        gaussianElimination(coefficients, rhs);
        return rhs;
    }

    private void gaussianElimination(double[][] coefficients, double[] rhs) {
        int numVars = coefficients.length;
        for (int i = 0; i < numVars; i++) {
            double pivot = coefficients[i][i];
            for (int j = 0; j < numVars; j++) {
                coefficients[i][j] = coefficients[i][j] / pivot;
            }
            rhs[i] = rhs[i] / pivot;
            for (int k = 0; k < numVars; k++) {
                if (k != i) {
                    double factor = coefficients[k][i];
                    for (int j = 0; j < numVars; j++) {
                        coefficients[k][j] = coefficients[k][j] - factor * coefficients[i][j];
                    }
                    rhs[k] = rhs[k] - factor * rhs[i];
                }
            }
        }
    }

    private boolean intersectionInRange(IntersectionPoint intersectionPoint, long rangeStart, long rangeEnd) {
        return rangeStart <= intersectionPoint.x() && intersectionPoint.x() < rangeEnd &&
                rangeStart <= intersectionPoint.y() && intersectionPoint.y() < rangeEnd;
    }

    private Optional<IntersectionPoint> intersectionWithoutZ(HailStone h1, HailStone h2) {
        double x = (-h2.getC() + h1.getC()) / (-h1.getM() + 1 * h2.getM());
        double y = (h1.getC() * h2.getM() - h2.getC() * h1.getM()) / (-h1.getM() + h2.getM());

        if (hailstoneHasNotCrossedInThePast(h1, x, y) && hailstoneHasNotCrossedInThePast(h2, x, y)) {
            return Optional.of(new IntersectionPoint(x, y));
        }

        return Optional.empty();
    }

    private boolean hailstoneHasNotCrossedInThePast(HailStone hailStone, double x, double y) {
        return (hailStone.vx() <= 0 || !(x < hailStone.x())) && (hailStone.vx() >= 0 || !(x > hailStone.x()))
                && (hailStone.vy() <= 0 || !(y < hailStone.y())) && (hailStone.vy() >= 0 || !(y > hailStone.y()));
    }
}
