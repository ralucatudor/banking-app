package services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Singleton class for writing to a CSV file.
 */
public class CsvWriterService {
    private static CsvWriterService instance = null;

    private CsvWriterService() {}

    public static CsvWriterService getInstance() {
        if (instance == null) {
            instance = new CsvWriterService();
        }
        return instance;
    }

    public String getCommaSeparatedString(List<String> data) {
        return data.stream()
                .collect(Collectors.joining(","));
    }

    public void write(String path, List<String> data) throws IOException {
        FileWriter fileWriter = new FileWriter(path, true);

        String toWrite = getCommaSeparatedString(data);
        fileWriter.write(toWrite + "\n");

        fileWriter.flush();
        fileWriter.close();
    }
}
