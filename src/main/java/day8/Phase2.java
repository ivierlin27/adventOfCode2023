package main.java.day8;

import main.java.day8.domain.Node;
import main.java.day8.domain.NodeMap;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.List;

public class Phase2 {

    public static long followPaths(NodeMap nodeMap) {
        List<String> currentLocations = nodeMap.getNodes().keySet().stream().filter(key -> key.endsWith("A")).toList();

        return currentLocations.stream().map(location -> followPath(nodeMap, location)).reduce(1L, ArithmeticUtils::lcm);
    }

    public static long followPath(NodeMap nodeMap, String initialLocation) {
        String currentLocation = initialLocation;
        int x = 0;
        int steps = 0;
        while (!currentLocation.endsWith("Z")) {
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
