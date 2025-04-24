package log;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FichierLog {

    private static final String FILE_PATH = "patient.txt";

    public static void logArriveePatient(int idPatient) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            LocalDateTime now = LocalDateTime.now();
            String dateHeure = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("Patient ID " + idPatient + " est arrivé à : " + dateHeure + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

