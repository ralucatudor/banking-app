package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Singleton class for writing to a file.
 */
public class FileWriterService {
    private static FileWriterService instance = null;

    private FileWriterService() {}

    public static FileWriterService getInstance() {
        if (instance == null) {
            instance = new FileWriterService();
        }
        return instance;
    }

    public String getCommaSeparatedString(List<String> data) {
        return data.stream()
                .collect(Collectors.joining(","));
    }

    public void writeToFile(String path, List<String> data) throws IOException {
        File destinatie = new File(path);
        destinatie.createNewFile(); // if file already exists will do nothing

        FileWriter w = new FileWriter(destinatie,true);
        String toWrite = getCommaSeparatedString(data);
        w.write(toWrite + "\n");
        w.flush();
        w.close();
    }
}
