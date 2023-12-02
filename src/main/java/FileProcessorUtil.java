package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class FileProcessorUtil {

    public static int processFile(Function<String, Integer> processor, String inputFile) {
        int total = 0;
        try (FileReader fileReader =  new FileReader(inputFile)) {
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();

            while (line != null) {
                int val = processor.apply(line);
                total += val;
                // uncomment below for debugging
                // System.out.println(line + " -> " + val + " subTot=" + total);
                // read the next line
                line = reader.readLine();
            }
        } catch (IOException ioException) {
            // whatever
        }
        return total;
    }
}
