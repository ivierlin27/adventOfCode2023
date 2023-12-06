package main.java.day3;

import main.java.day3.domain.Line;
import main.java.day3.domain.Number;
import main.java.day3.domain.Symbol;
import main.java.domain.Input;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static main.java.day3.EngineDiagramParser.parseLine;

public class Phase2 {
    private Line nextLine;
    private Line currentLine;
    private Line previousLine;

    // guess 79579614 at 9:51pm
    // guess 80694070 at 10:18pm
    protected long findGears(Input input) {
        // optimization to re-use parsed next line as current if available
        currentLine = Objects.requireNonNullElseGet(nextLine, () -> parseLine(input.getCurrentLineNumber(), input.getCurrentLine()));
        if (!input.isLastLine()) {
            nextLine = parseLine(input.getCurrentLineNumber() + 1, input.getNextLine());
        } else {
            nextLine = null;
        }
        checkLineForEngineParts(listOfPrevCurNextSymbols(), currentLine);

        if (nextLine != null) {
            checkLineForEngineParts(listOfCurNextSymbols(), nextLine);
        }

        int lineValue = currentLine.getSymbols().stream().map(this::checkForGear).reduce(0, Integer::sum);
        previousLine = currentLine;
        return lineValue;
    }

    private int checkForGear(Symbol symbol) {
        List<Number> numbers = Stream.of(previousLine == null ? null : previousLine.getNumbers(),
                currentLine == null ? null : currentLine.getNumbers(),
                nextLine == null ? null : nextLine.getNumbers()
                        ).filter(Objects::nonNull)
                        .flatMap(List::stream)
                .filter(Number::isEnginePart)
                .filter(enginePart -> {
                    Symbol s = enginePart.getEnginePartSymbol();
                    return s.getPosition() == symbol.getPosition() && s.getLine() == symbol.getLine();
                })
                .toList();
        if (numbers.size() == 2) {
            return numbers.get(0).getValue() * numbers.get(1).getValue();
        }
        return 0;
    }

    private void checkLineForEngineParts(List<Symbol> symbols, Line lineToProcess) {
        lineToProcess.getNumbers().forEach(number -> {
            int beginningPosition = number.getPosition();
            for (int x = beginningPosition - 1; x <= beginningPosition + number.getLength(); x++) {
                Optional<Symbol> foundSymbol = findSymbolByPosition(symbols, x);
                if (foundSymbol.isPresent()) {
                    number.setEnginePart(true);
                    number.setEnginePartSymbol(foundSymbol.get());
                    break;
                }
            }
        });
    }

    private List<Symbol> listOfPrevCurNextSymbols() {
        return Stream.of(
                        previousLine == null ? null : previousLine.getSymbols(),
                        currentLine == null ? null : currentLine.getSymbols(),
                        nextLine == null ? null : nextLine.getSymbols()
                ).filter(Objects::nonNull)
                .flatMap(List::stream)
                .toList();
    }

    private List<Symbol> listOfCurNextSymbols() {
        return Stream.of(
                        currentLine == null ? null : currentLine.getSymbols(),
                        nextLine == null ? null : nextLine.getSymbols()
                ).filter(Objects::nonNull)
                .flatMap(List::stream)
                .toList();
    }

    private Optional<Symbol> findSymbolByPosition(List<Symbol> symbols, int positionToFind) {
        return symbols.stream().filter(symbol -> positionToFind == symbol.getPosition()).findFirst();
    }
}
