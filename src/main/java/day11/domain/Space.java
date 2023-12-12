package main.java.day11.domain;

import main.java.domain.Input;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Space {
    public static final String GALAXY = "#";
    public static final String EMPTY_SPACE = ".";
    private final Map<Integer, String> grid = new LinkedHashMap<>();
    private final List<Galaxy> galaxies = new ArrayList<>();
    private final Map<Integer, String> expandedGrid = new LinkedHashMap<>();
    private final List<Galaxy> expandedGalaxies = new ArrayList<>();
    private int lineNumber = 0;

    public long loadInputLine(Input input) {
        grid.put(lineNumber, input.getCurrentLine());
        lineNumber++;
        if (input.isLastLine()) {
            locateGalaxies(grid, galaxies);
            expandSpace();
            locateGalaxies(expandedGrid, expandedGalaxies);
            return calculateShortestPaths(expandedGalaxies);
        }
        return 0;
    }

    private long calculateShortestPaths(List<Galaxy> expandedGalaxies) {
        long total = 0;
        for (int x = 0; x < expandedGalaxies.size(); x++) {
            for (int y = x + 1; y < expandedGalaxies.size(); y++) {
                total += calculatePath(expandedGalaxies.get(x), expandedGalaxies.get(y));
            }
        }
        return total;
    }

    private long calculatePath(Galaxy galaxy, Galaxy otherGalaxy) {
        return Math.abs(galaxy.column - otherGalaxy.column) + Math.abs(galaxy.row - otherGalaxy.row);
    }

    private void expandSpace() {
        List<Integer> rowsToExpand = calculateRowsToExpand();
        List<Integer> columnsToExpand = calculateColumnsToExpand();

        int newLineNumber = 0;
        for (Map.Entry<Integer, String> entry : grid.entrySet()) {
            String row = expandColumnsOfCurrentRow(entry.getValue(), columnsToExpand);
            expandedGrid.put(newLineNumber, row);
            newLineNumber++;
            if (rowsToExpand.contains(entry.getKey())) {
                expandedGrid.put(newLineNumber, row);
                newLineNumber++;
            }
        }
    }

    private String expandColumnsOfCurrentRow(String row, List<Integer> columnsToExpand) {
        for (Integer column : columnsToExpand.reversed()) {
            row = StringUtils.overlay(row, EMPTY_SPACE, column, column);
        }
        return row;
    }

    private List<Integer> calculateRowsToExpand() {
        return grid.entrySet().stream()
                .filter(entry -> !entry.getValue().contains(GALAXY))
                .map(Map.Entry::getKey)
                .toList();
    }

    private List<Integer> calculateColumnsToExpand() {
        List<Integer> columnsWithGalaxies = galaxies.stream().map(galaxy -> galaxy.column).toList();
        List<Integer> columnsToExpand = new ArrayList<>();
        for (int i = 0; i < grid.get(0).length(); i++) {
            if (!columnsWithGalaxies.contains(i)) {
                columnsToExpand.add(i);
            }
        }
        return columnsToExpand;
    }

    private void locateGalaxies(Map<Integer, String> gridToProcess, List<Galaxy> galaxiesContainer) {
        gridToProcess.forEach((key, value) -> {
            for (int i = 0; i < gridToProcess.get(0).length(); i++) {
                char character = value.charAt(i);
                if (character == '#') {
                    galaxiesContainer.add(new Galaxy(i, key));
                }
            }
        });
    }
}
