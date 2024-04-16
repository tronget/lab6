package fileManager;

import com.opencsv.CSVWriter;
import models.MusicBand;
import utility.ConsoleWriter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс, позволяющий считывать файлы
 */
public class FileWriter {
    public boolean writeToFile(File file, Map<? extends String, ? extends MusicBand> table) {
        try (CSVWriter writer = new CSVWriter(new java.io.FileWriter(file, StandardCharsets.UTF_8))) {
            List<String[]> list = convertMapToArray(table);
            list.forEach(writer::writeNext);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ConsoleWriter.getInstance().alert("Ошибка в записи в файл!");
            return false;
        }
//        try (java.io.FileWriter fw = new java.io.FileWriter(file, StandardCharsets.UTF_8)) {
//            fw.write(convertMapToCSV(table));
//            return true;
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            ConsoleWriter.getInstance().alert("Ошибка в записи в файл!");
//            return false;
//        }
    }

    private List<String[]> convertMapToArray(Map<? extends String, ? extends MusicBand> table) {
        List<String[]> list = new ArrayList<>();
        table.forEach((k, v) -> {
            String[] arr = {k, v.getID().toString(), v.getName(), String.valueOf(v.getCoordinates().getX()), String.valueOf(v.getCoordinates().getY()), String.valueOf(v.getCREATION_DATE().getTime()), String.valueOf(v.getNumberOfParticipants()), String.valueOf(v.getEstablishmentDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()), v.getGenre().name(), v.getStudio().getName(), v.getStudio().getAddress()};
            list.add(arr);
        });
        return list;
    }
}
