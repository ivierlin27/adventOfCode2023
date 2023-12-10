package main.java.day10;

import main.java.day10.domain.Step;

public class Phase1 {
    public static long countStepsInPipe(PipeMaze pipeMaze) {
        int steps = 0;

        Step step = pipeMaze.takeStep(pipeMaze.getStart(), "Not a direction");
        steps++;
        while (pipeMaze.currentPositionNotStart(step.getCurrentTile())) {
            step = pipeMaze.takeStep(step.getCurrentTile(), step.getFromDirection());
            steps++;
        }

        return steps;
    }
}
