package main.java.day15;

import main.java.domain.Input;

import java.util.Arrays;

public class Magnifier {

    public static long initializeSequence(Input input) {
        String[] parts = input.getCurrentLine().split(",");

        return Arrays.stream(parts).map(Magnifier::calculateHash).reduce(0L, Long::sum);
    }

    private static long calculateHash(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash + s.charAt(i)) * 17 % 256;
        }
        return hash;
    }
}
