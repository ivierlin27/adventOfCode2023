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
