package fileManager;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import models.Coordinates;
import models.MusicBand;
import models.MusicGenre;
import models.Studio;
import utility.ConsoleWriter;
import utility.Program;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;

/**
 * Класс считывающий файлы.
 */
public class FileReader {

    /**
     * Метод считывает данные из файла.
     * @param file файл, из которого считываются данные
     * @return данные из файла в виде String
     * @throws IOException возникает в случае ошибки при чтении
     */
    public String readFromFile(File file) throws IOException {
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            int temp;
            StringBuilder stringBuilder = new StringBuilder();
            while ((temp = reader.read()) != -1) {
                stringBuilder.append((char) temp);
            }
            return stringBuilder.toString();
        }
    }

    public Hashtable<String, MusicBand> readFromCsv(File file) throws IOException {
        try (CSVReader csvReader = new CSVReader(new java.io.FileReader(file))) {
            return convertCsvToMap(csvReader.readAll());
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    private Hashtable<String, MusicBand> convertCsvToMap(List<String[]> csvList) {
        Hashtable<String, MusicBand> table = new Hashtable<>();
        if (csvList.isEmpty()) {
            return table;
        }
        try {
            for (String[] line : csvList) {
                try {
                    String key = line[0];
                    Long ID = Long.valueOf(line[1]);
                    String name = line[2];
                    int x = Integer.parseInt(line[3]);
                    double y = Double.parseDouble(line[4]);
                    Coordinates coordinates = new Coordinates(x, y);
                    Date CREATION_DATE = new Date(Long.parseLong(line[5]));
                    int numberOfParticipants = Integer.parseInt(line[6]);
                    LocalDateTime establishmentDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(line[7])), TimeZone.getDefault().toZoneId());
                    MusicGenre genre = MusicGenre.valueOf(line[8]);
                    String studioName = line[9], address = line[10];
                    Studio studio = new Studio(studioName, address);
                    table.put(key, new MusicBand(ID, name, coordinates, CREATION_DATE, numberOfParticipants, establishmentDate, genre, studio));
                } catch (Exception e) {
                    System.out.println("Ошибка при чтении строки файла!");
                }
            }
        } catch (Exception e) {
            ConsoleWriter.getInstance().alert("Ошибка при чтении файла!");
            Program.getInstance().stop();
        }
        return table;
    }
}
