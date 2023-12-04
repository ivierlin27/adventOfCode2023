package main.java.day3;

import main.java.day3.domain.Line;
import main.java.day3.domain.Number;
import main.java.day3.domain.Symbol;
import main.java.domain.Input;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static main.java.day3.EngineDiagramParser.parseLine;

public class Phase1 {
    private Line nextLine;
    private Line currentLine;
    private Line previousLine;

    protected int parseEngineDiagram(Input input) {
        if (!input.isLastLine()) {
            nextLine = parseLine(input.getNextLine());
        } else {
            nextLine = null;
        }
        currentLine = parseLine(input.getCurrentLine());

        int lineValue = checkLineForEngineParts();
        previousLine = currentLine;
        return lineValue;
    }

    private int checkLineForEngineParts() {
        List<Integer> symbolPositions = Stream.of(
                        previousLine == null ? null : previousLine.getSymbols(),
                        currentLine == null ? null : currentLine.getSymbols(),
                        nextLine == null ? null : nextLine.getSymbols()
                ).filter(Objects::nonNull)
                .flatMap(List::stream)
                .map(Symbol::getPosition)
                .toList();
        assert currentLine != null;
        currentLine.getNumbers().forEach(number -> {
            int beginningPosition = number.getPosition();
            for (int x = beginningPosition - 1; x <= beginningPosition + number.getLength(); x++) {
                if (symbolPositions.contains(x)) {
                    number.setEnginePart(true);
                    number.setEnginePartSymbol(new Symbol(x));
                    break;
                }
            }
        });
        return currentLine.getNumbers().stream()
                .filter(Number::isEnginePart)
                .map(Number::getValue)
                .reduce(0, Integer::sum);
    }
}
