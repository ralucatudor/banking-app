package services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class for logging events.
 */
public class AuditService {
    private static AuditService instance = null;
    private CsvWriterService csvWriterService;

    private AuditService() { this.csvWriterService = CsvWriterService.getInstance(); }

    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void logEvent(String eventName) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        List<String> toWrite =new ArrayList<>();
        toWrite.add(dtf.format(now));
        toWrite.add(eventName);

        try {
            csvWriterService.write("data\\audit.csv", toWrite, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
