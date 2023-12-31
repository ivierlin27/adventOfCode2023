package main.java.day8;

import main.java.day8.domain.Node;
import main.java.day8.domain.NodeMap;

public class Phase1 {

    public static long followPath(NodeMap nodeMap) {
        String currentLocation = "AAA";
        int x = 0;
        int steps = 0;
        while (!currentLocation.equals("ZZZ")) {
            steps++;
            char direction = nodeMap.getDirections().charAt(x);
            Node node = nodeMap.getNodes().get(currentLocation);
            if (direction == 'L') {
                currentLocation = node.getLeft();
            } else if (direction == 'R') {
                currentLocation = node.getRight();
            }
            if (x + 1 > nodeMap.getDirections().length() - 1) {
                x = 0;
            } else {
                x++;
            }
        }
        return steps;
    }
}
