package main.java;

import main.java.domain.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class FileProcessorUtil {

    public static int processFile(Function<Input, Integer> processor, String inputFile) {
        int total = 0;
        try (FileReader fileReader =  new FileReader(inputFile)) {
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();

            while (line != null) {
                String currentLine = line;
                // read the next line before processing
                line = reader.readLine();
                Input input = new Input(currentLine, line);

                total += processor.apply(input);
                System.out.println(" -> total: " + total);
            }
        } catch (IOException ioException) {
            // whatever
        }
        return total;
    }
}
