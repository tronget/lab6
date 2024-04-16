package utility;

import models.Coordinates;
import models.MusicBand;
import models.MusicGenre;
import models.Studio;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Класс, реализующий считывание объекта класса {@link MusicBand}.
 */
public class MusicBandScanner {
    /**
     * @return считанный объект класса {@link MusicBand} или Null в случае ошибки считывания
     */
    public static MusicBand scan() {
        ConsoleWriter consoleWriter = ConsoleWriter.getInstance();
        String name = null;
        while (name == null) {
            Scanner scanner = new Scanner(System.in);
            consoleWriter.print("Имя музыкальной группы: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                name = null;
            }
        }

        Integer x = null;
        while (x == null) {
            Scanner scanner = new Scanner(System.in);
            consoleWriter.print("Координата по X целое число (-768 < X < 2147483648): ");
            if (!scanner.hasNextInt()) {
                consoleWriter.alert("Ограничение: Целое число (-768 < X < 2147483648)!");
            } else {
                x = scanner.nextInt();
                if (x <= -768) {
                    consoleWriter.alert("Ограничение: Целое число (-768 < X < 2147483648)!");
                    x = null;
                }
            }
        }

        Double y = null;
        while (y == null) {
            Scanner scanner = new Scanner(System.in);
            consoleWriter.print("Координата по Y: ");
            if (!scanner.hasNextDouble()) {
                consoleWriter.alert("Ограничение: Вещественное число!");
            } else {
                y = scanner.nextDouble();
            }
        }

        Integer numberOfParticipants = null;
        while (numberOfParticipants == null || numberOfParticipants <= 0) {
            Scanner scanner = new Scanner(System.in);
            consoleWriter.print("Кол-во участников: ");
            if (!scanner.hasNextInt()) {
                consoleWriter.alert("Ограничение: Целое число > 0!");
            } else {
                numberOfParticipants = scanner.nextInt();
            }
        }

        LocalDateTime dateTime = null;
        while (dateTime == null) {
            Scanner scanner = new Scanner(System.in);
            consoleWriter.print("Дата основания через \"/\" (Год/Месяц/День/Час/Минута/Секунда): ");
            String unparsedDate = scanner.nextLine().trim();
            if (unparsedDate.contains("//") || unparsedDate.charAt(0) == '/' || unparsedDate.charAt(unparsedDate.length() - 1) == '/' || unparsedDate.length() - unparsedDate.replace("/", "").length() != 5) {
                consoleWriter.alert("Неправильный формат ввода данных!");
                continue;
            }
            List<Integer> parsedDateList;
            try {
                parsedDateList = Arrays.stream(unparsedDate.split("\\s*/\\s*")).map(Integer::parseInt).toList();
            } catch (NumberFormatException e) {
                consoleWriter.alert("Неправильный формат ввода данных!");
                continue;
            }

            try {
                int
                        year = parsedDateList.get(0),
                        month = parsedDateList.get(1),
                        day = parsedDateList.get(2),
                        hour = parsedDateList.get(3),
                        minute = parsedDateList.get(4),
                        second = parsedDateList.get(5);

                dateTime = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, minute, second));
            } catch (DateTimeException e) {
                consoleWriter.alert("Неправильный формат ввода данных!");
            }
        }

        MusicGenre musicGenre = null;
        while (musicGenre == null) {
            Scanner scanner = new Scanner(System.in);
            consoleWriter.print("Жанр музыки (ROCK, PSYCHEDELIC_CLOUD_RAP, SOUL): ");
            String musicGenreLine = scanner.nextLine();
            try {
                musicGenre = MusicGenre.valueOf(musicGenreLine.toUpperCase());
            } catch (IllegalArgumentException e) {
                consoleWriter.alert("Неправильный формат ввода данных!");
            }
        }

        String studioName = null, address = null;
        while (studioName == null) {
            Scanner scanner = new Scanner(System.in);
            consoleWriter.print("Название студии: ");
            studioName = scanner.nextLine().trim();
            if (studioName.isEmpty()) {
                studioName = null;
            }
        }

        while (address == null) {
            Scanner scanner = new Scanner(System.in);
            consoleWriter.print("Адрес: ");
            address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                address = null;
            }
        }
        System.out.println("Ввод окончен успешно!");
        return new MusicBand(name, new Coordinates(x, y), numberOfParticipants, dateTime, musicGenre, new Studio(studioName, address));
    }
}
