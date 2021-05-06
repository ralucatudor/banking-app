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

    public void emptyFile(String path) throws IOException {
        new FileWriter(path, false).close();
    }

    /**
     * Writes to a csv file.
     *
     * @param path csv file path.
     * @param data data to be written on one line of the csv file.
     * @param append boolean indicating whether or not to append
     *               the data already written in the file.
     */
    public void write(String path, List<String> data, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(path, append);

        String toWrite = getCommaSeparatedString(data);
        fileWriter.write(toWrite + "\n");

        fileWriter.flush();
        fileWriter.close();
    }
}
