package services;

import java.io.File;
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
        File destinatie = new File(path);
        destinatie.createNewFile(); // if file already exists will do nothing

        FileWriter w = new FileWriter(destinatie,true);
        String toWrite = getCommaSeparatedString(data);
        w.write(toWrite + "\n");
        w.flush();
        w.close();
    }
}
