package utility;

import exceptions.ScriptExecutionError;
import models.Coordinates;
import models.MusicBand;
import models.MusicGenre;
import models.Studio;
import network.ResponseBuilder;

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
    private final static ScriptExecutor scriptExecutor = ScriptExecutor.getInstance();

    /**
     * @return считанный объект класса {@link MusicBand} или Null в случае ошибки считывания
     */
    public static MusicBand scan() {
        if (scriptExecutor.isScriptExecution()) {
            try {
                return scanFromScript();
            } catch (ScriptExecutionError e) {
                scriptExecutor.setScriptErrorFlags();
                ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
                responseBuilder.add(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Метод считывает объект класса {@link MusicBand} из скрипта.
     * При считывании неверных данных метод выбрасывает исключение {@link ScriptExecutionError}.
     *
     * @return объект класса {@link MusicBand}
     * @throws ScriptExecutionError возникает при считывании неверных данных
     */
    private static MusicBand scanFromScript() throws ScriptExecutionError {
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        ConsoleWriter consoleWriter = ConsoleWriter.getInstance();
        Scanner scanner = scriptExecutor.getScannerForScript();
        String name = null;
        name = scanner.nextLine().trim();
        responseBuilder.add("Имя музыкальной группы: %s".formatted(name));
        if (name.isEmpty()) {
            throw new ScriptExecutionError();
        }

        Integer x = null;
        if (!scanner.hasNextInt()) {
            throw new ScriptExecutionError();
        }
        x = scanner.nextInt();
        responseBuilder.add("Координата по X целое число (-768 < X < 2147483648): %s".formatted(x));
        if (x <= -768) {
            throw new ScriptExecutionError();
        }

        Double y = null;
        if (!scanner.hasNextDouble()) {
            throw new ScriptExecutionError();
        }
        y = scanner.nextDouble();
        responseBuilder.add("Координата по Y: %s".formatted(y));

        Integer numberOfParticipants = null;
        if (!scanner.hasNextInt()) {
            throw new ScriptExecutionError();
        }
        numberOfParticipants = scanner.nextInt();
        responseBuilder.add("Кол-во участников: %s".formatted(numberOfParticipants));
        if (numberOfParticipants <= 0) {
            throw new ScriptExecutionError();
        }


        LocalDateTime dateTime = null;
        String unparsedDate = scanner.next().trim();
        responseBuilder.add("Дата основания через \"/\" (Год/Месяц/День/Час/Минута/Секунда): %s".formatted(unparsedDate));
        if (unparsedDate.contains("//") || unparsedDate.charAt(0) == '/' || unparsedDate.charAt(unparsedDate.length() - 1) == '/' || unparsedDate.length() - unparsedDate.replace("/", "").length() != 5) {
            throw new ScriptExecutionError();
        }
        List<Integer> parsedDateList;
        try {
            parsedDateList = Arrays.stream(unparsedDate.split("\\s*/\\s*")).map(Integer::parseInt).toList();
        } catch (NumberFormatException e) {
            throw new ScriptExecutionError();
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
            throw new ScriptExecutionError();
        }


        MusicGenre musicGenre = null;
        String musicGenreLine = scanner.next();
        responseBuilder.add("Жанр музыки (ROCK, PSYCHEDELIC_CLOUD_RAP, SOUL): %s".formatted(musicGenreLine));
        try {
            musicGenre = MusicGenre.valueOf(musicGenreLine.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ScriptExecutionError();
        }

        String studioName = null, address = null;
        studioName = scanner.next().trim();
        responseBuilder.add("Название студии: %s".formatted(studioName));
        if (studioName.isEmpty()) {
            throw new ScriptExecutionError();
        }

        address = scanner.next().trim();
        responseBuilder.add("Адрес: %s".formatted(address));
        if (address.isEmpty()) {
            throw new ScriptExecutionError();
        }

        responseBuilder.add("Ввод окончен успешно!");
        return new MusicBand(name, new Coordinates(x, y), numberOfParticipants, dateTime, musicGenre, new Studio(studioName, address));
    }
}
