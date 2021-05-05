package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Singleton class for reading from a CSV file.
 */
public class CsvReaderService {
    private static CsvReaderService instance = null;

    private CsvReaderService() {}

    public static CsvReaderService getInstance() {
        if (instance == null) {
            instance = new CsvReaderService();
        }
        return instance;
    }

    public List<List<String>> read(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        // Initialize the data array.
        List<List<String>> data = new ArrayList<>();

        // Populate {@code data} with info from the csv file.
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<String> items = Arrays.asList(line.split(","));
            data.add(items);
        }

        scanner.close();
        return data;
    }
}
