package main.java.day15;

import main.java.day15.domain.Lens;
import main.java.domain.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Magnifier {

    public static long initializeSequence(Input input) {
        String[] parts = input.getCurrentLine().split(",");

        return Arrays.stream(parts).map(Magnifier::calculateHash).reduce(0, Integer::sum);
    }

    public static long focusThePower(Input input) {
        String[] parts = input.getCurrentLine().split(",");
        List<Lens> lenses = Arrays.stream(parts).map(Magnifier::buildLens).toList();
        Map<Integer, List<Lens>>  boxes = new LinkedHashMap<>();
        lenses.forEach(lens -> applyLens(lens, boxes));
        return boxes.entrySet().stream().map(Magnifier::calculateFocusPower).reduce(0, Integer::sum);
    }

    private static int calculateFocusPower(Map.Entry<Integer, List<Lens>> box) {
        int focusPower = 0;
        int boxNumber = box.getKey();
        for (int i = 0; i < box.getValue().size(); i++) {
            focusPower += (1 + boxNumber) * (i + 1) * box.getValue().get(i).getFocalLength();
        }
        return focusPower;
    }

    private static void applyLens(Lens lens, Map<Integer, List<Lens>> boxes) {
        if (lens.getOperator() == '-') {
            List<Lens> existingLenses = boxes.get(lens.getBox());
            if (existingLenses != null) {
                for (int i = 0; i < existingLenses.size(); i++) {
                    if (existingLenses.get(i).getLabel().equals(lens.getLabel())) {
                        existingLenses.remove(i);
                    }
                }
            }
        } else if (lens.getOperator() == '=') {
            List<Lens> existingLenses = boxes.get(lens.getBox());
            if (existingLenses != null) {
                boolean found = false;
                for (int i = 0; i < existingLenses.size(); i++) {
                    if (existingLenses.get(i).getLabel().equals(lens.getLabel())) {
                        existingLenses.remove(i);
                        existingLenses.add(i, lens);
                        found = true;
                    }
                }
                if (!found) {
                    existingLenses.add(lens);
                }
            } else {
                boxes.put(lens.getBox(), new ArrayList<>() {{ add(lens); }});
            }
        }
    }

    private static Lens buildLens(String s) {
        String[] parts = s.split("[=-]+");
        char operation = s.indexOf('=') != -1 ? '=' : '-';
        Integer focalLength = parts.length > 1 ? Integer.parseInt(parts[1]) : null;
        return new Lens(s, parts[0], calculateHash(parts[0]),operation, focalLength);
    }

    private static int calculateHash(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash + s.charAt(i)) * 17 % 256;
        }
        return hash;
    }
}
