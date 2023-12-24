package main.java.day22;

import main.java.day22.domain.Brick;
import main.java.day22.domain.BrickEnd;
import main.java.domain.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Jenga {
    List<Brick> block = new ArrayList<>();
    long idSequence = 0L;
    public long loadSnapshot(Input input) {
        String[] parts = input.getCurrentLine().split("~");
        BrickEnd side1 = new BrickEnd(parts[0].split(","));
        BrickEnd side2 = new BrickEnd(parts[1].split(","));
        Brick brick = new Brick(idSequence, bottom(side1, side2), top(side1, side2));

        block.add(brick);
        idSequence++;

        if (input.isLastLine()) {
            block.sort(Comparator.comparing(b -> b.getEnd1().z()));
            block.forEach(b -> b.moveToFinalPosition(block));
            block.forEach(b -> {
                b.identifySupportedBy(block);
                b.identifySupports(block);
            });
//            block.forEach(System.out::println);
            return calculateDestroyable();
        }
        return 0;
    }

    private long calculateDestroyable() {
        return bricksThatSupportBricksSupportedAlready().size();
    }

    private Set<Brick> bricksThatSupportBricksSupportedAlready() {
        return block.stream()
                .filter(brick -> brick.supports().isEmpty() || brick.supports().stream()
                        .allMatch(supportBrick -> supportBrick.supportedBy().size() > 1))
                .collect(Collectors.toSet());
    }

    private BrickEnd bottom(BrickEnd side1, BrickEnd side2) {
        if (side1.z() == side2.z()) {
            if (side1.x() == side2.x()) {
                return side1.y() == Math.min(side1.y(), side2.y()) ? side1 : side2;
            }
            return side1.x() == Math.min(side1.x(), side2.x()) ? side1 : side2;
        }
        return side1.z() == Math.min(side1.z(), side2.z()) ? side1 : side2;
    }

    private BrickEnd top(BrickEnd side1, BrickEnd side2) {
        if (side1.z() == side2.z()) {
            if (side1.x() == side2.x()) {
                return side1.y() == Math.max(side1.y(), side2.y()) ? side1 : side2;
            }
            return side1.x() == Math.max(side1.x(), side2.x()) ? side1 : side2;
        }
        return side1.z() == Math.max(side1.z(), side2.z()) ? side1 : side2;
    }
}
