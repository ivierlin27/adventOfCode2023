package main.java.day22.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class Brick {
    private final Long id;
    private BrickEnd end1;
    private BrickEnd end2;
    private final Set<Brick> supportedBy = new HashSet<>();
    private final Set<Brick> supports = new HashSet<>();

    public Brick(Long id, BrickEnd end1, BrickEnd end2) {
        this.id = id;
        this.end1 = end1;
        this.end2 = end2;
    }
    
    public void moveToFinalPosition(List<Brick> block) {
        boolean unsettled = true;
        while(unsettled) {
            if (canFallMore(block)) {
                fall();
            } else {
                unsettled = false;
            }
        }
    }

    public void identifySupportedBy(List<Brick> block) {
       block.stream()
               .filter(this::isDirectlyBelow)
               .filter(brick -> intersectsX(brick) && intersectsY(brick))
               .forEach(supportedBy::add);
    }

    private boolean isHorizontal() {
        return end1.z() == end2.z();
    }

    public void identifySupports(List<Brick> block) {
        block.stream()
                .filter(this::isDirectlyAbove)
                .filter(brick -> intersectsX(brick) && intersectsY(brick))
                .forEach(supports::add);
    }

    private boolean isDirectlyBelow(Brick brick) {
        return Math.min(end1.z() - 1, end2.z() - 1) == Math.max(brick.end1.z(), brick.end2.z());
    }

    private boolean isDirectlyAbove(Brick brick) {
        return Math.max(end1.z() + 1, end2.z() + 1) == Math.min(brick.end1.z(), brick.end2.z());
    }

    private void fall() {
        this.end1 = new BrickEnd(end1.x(), end1.y(), end1.z() - 1);
        this.end2 = new BrickEnd(end2.x(), end2.y(), end2.z() - 1);
    }

    private boolean canFallMore(List<Brick> block) {
        if (end1.z() == 1) {
            return false;
        }
        return block.stream().filter(brick -> Math.max(brick.end2.z(), brick.end1.z()) < Math.min(end1.z(), end2.z()))
                .noneMatch(brick -> {
                    if (Math.min(end1.z() - 1, end2.z() - 1) == Math.max(brick.end2.z(), brick.end1.z())) {
                        return intersectsX(brick) && intersectsY(brick);
                    }
                    return false;
                });
    }

    private boolean intersectsX(Brick brick) {
        if (isHorizontal() && brick.isHorizontal()) {
            for (long i = end1.x(); i <= end2.x(); i++) {
                for (long j = brick.end1.x(); j <= brick.end2.x(); j++) {
                    if (i == j) {
                        return true;
                    }
                }
            }
        } else if (isHorizontal() && !brick.isHorizontal()) {
            BrickEnd brickEnd = brick.end1.z() == end1.z() ? brick.end1 : brick.end2;
            for (long i = end1.x(); i <= end2.x(); i++) {
                if (i == brickEnd.x()) {
                    return true;
                }
            }
        } else if (!isHorizontal() && brick.isHorizontal()) {
            BrickEnd localBrickEnd = end1.z() == brick.end1.z() ? end1 : end2;
            for (long j = brick.end1.x(); j <= brick.end2.x(); j++) {
                if (localBrickEnd.x() == j) {
                    return true;
                }
            }
        } else {
            BrickEnd brickEnd = brick.end1.z() == end1.z() ? brick.end1 : brick.end2;
            BrickEnd localBrickEnd = end1.z() == brickEnd.z() ? end1 : end2;
            return brickEnd.x() == localBrickEnd.x();
        }
        return false;
    }

    private boolean intersectsY(Brick brick) {
        if (isHorizontal() && brick.isHorizontal()) {
            for (long i = end1.y(); i <= end2.y(); i++) {
                for (long j = brick.end1.y(); j <= brick.end2.y(); j++) {
                    if (i == j) {
                        return true;
                    }
                }
            }
        } else if (isHorizontal() && !brick.isHorizontal()) {
            BrickEnd brickEnd = brick.end1.z() == end1.z() ? brick.end1 : brick.end2;
            for (long i = end1.y(); i <= end2.y(); i++) {
                if (i == brickEnd.y()) {
                    return true;
                }
            }
        } else if (!isHorizontal() && brick.isHorizontal()) {
            BrickEnd localBrickEnd = end1.z() == brick.end1.z() ? end1 : end2;
            for (long j = brick.end1.y(); j <= brick.end2.y(); j++) {
                if (localBrickEnd.y() == j) {
                    return true;
                }
            }
        } else {
            BrickEnd brickEnd = brick.end1.z() == end1.z() ? brick.end1 : brick.end2;
            BrickEnd localBrickEnd = end1.z() == brickEnd.z() ? end1 : end2;
            return brickEnd.y() == localBrickEnd.y();
        }
        return false;
    }

    public BrickEnd getEnd1() {
        return end1;
    }

    public Set<Brick> supports() {
        return supports;
    }

    public Set<Brick> supportedBy() {
        return supportedBy;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Brick) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.end1, that.end1) &&
                Objects.equals(this.end2, that.end2);
    }

    @Override
    public String toString() {
        return "Brick{" +
                id +
                ": " + end1 +
                " -> " + end2 +
                " supports: " + supports.stream().map(brick -> new Brick(brick.id, brick.end1, brick.end2)).toList() +
                " supported by: " + supportedBy.stream().map(brick -> new Brick(brick.id, brick.end1, brick.end2)).toList() +
                '}';
    }
}
