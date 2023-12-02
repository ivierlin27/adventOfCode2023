package main.java.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import main.java.day1.parser.BackwardParser;
import main.java.day1.parser.ForwardParser;

public class Day1 {
    public Day1() {
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day1/input.txt"));
            String line = reader.readLine();

            int total;
            for(total = 0; line != null; line = reader.readLine()) {
                int val = parseIntFromText(line);
                total += val;
                System.out.println(line + " -> " + val + " subTot=" + total);
            }

            System.out.println("Total: " + total);
            reader.close();
        } catch (IOException ioException) {
            // do nothing
        }

    }

    private static int parseIntFromText(String line) {
        Optional<String> firstValue = (new ForwardParser(line)).findValue();
        Optional<String> lastValue = (new BackwardParser(line)).findValue();
        if (firstValue.isPresent() && lastValue.isPresent()) {
            return Integer.parseInt(firstValue.get() + lastValue.get());
        } else {
            return 0;
        }
    }
}
