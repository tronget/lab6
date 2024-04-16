package stateManager;

import commands.*;
import models.MusicBand;
import shared.Request;
import utility.ConsoleWriter;
import utility.MusicBandScanner;
import utility.Program;

import java.util.Arrays;
import java.util.Map;


/**
 * Класс, отвечающий за определение команд и дальнейшее их исполнение.
 */
public class CommandsManager {
    private static final Map<String, Command> commands = Map.ofEntries(
            Map.entry(new HelpCommand().getName(), new HelpCommand()),
            Map.entry(new InfoCommand().getName(), new InfoCommand()),
            Map.entry(new ShowCommand().getName(), new ShowCommand()),
            Map.entry(new InsertCommand().getName(), new InsertCommand()),
            Map.entry(new UpdateCommand().getName(), new UpdateCommand()),
            Map.entry(new RemoveCommand().getName(), new RemoveCommand()),
            Map.entry(new ClearCommand().getName(), new ClearCommand()),
            Map.entry(new ScriptCommand().getName(), new ScriptCommand()),
            Map.entry(new ExitCommand().getName(), new ExitCommand()),
            Map.entry(new RemoveAllGreaterCommand().getName(), new RemoveAllGreaterCommand()),
            Map.entry(new RemoveAllLowerCommand().getName(), new RemoveAllLowerCommand()),
            Map.entry(new HistoryCommand().getName(), new HistoryCommand()),
            Map.entry(new MinByCreationDateCommand().getName(), new MinByCreationDateCommand()),
            Map.entry(new PrintAscendingCommand().getName(), new PrintAscendingCommand()),
            Map.entry(new PrintUniqueParticipantsCommand().getName(), new PrintUniqueParticipantsCommand())
    );

    /**
     * Метод определяет команду для последующего исполнения.
     *
     * @param userInput строка считанная из консоли, в которой содержится команда
     */
    public static void defineCommand(String[] userInput) {
        String keyWord = userInput[0];
        if (commands.containsKey(keyWord)) {
            if (userInput.length > 2) {
                ConsoleWriter.getInstance().alert("Too much arguments!");
            } else if (userInput.length == 2) {
                sendCommand(keyWord, userInput[1]);
                try {
                    System.out.println(getResponse().trim());
                } catch (NullPointerException ignored) {}

            } else {
                if (keyWord.equals(new ExitCommand().getName())) {
                    new ExitCommand().execute();
                } else {
                    sendCommand(keyWord);
                    String response = getResponse();
                    try {
                        System.out.println(response.trim());
                    } catch (NullPointerException ignored) {}
                }
            }
        } else if (keyWord.isEmpty()) {
            ConsoleWriter.getInstance().alert("Пустая команда!");
        } else {
            ConsoleWriter.getInstance().alert("Такой команды нет!");
        }
    }

    private static void sendCommand(String commandName, String arg) {
        Command command = commands.get(commandName);
        Request request;
        MusicBand musicBand;
        if (Arrays.asList(new InsertCommand().getName(), new UpdateCommand().getName()).contains(commandName)) {
            musicBand = MusicBandScanner.scan();
            request = new Request(command, arg, musicBand);
        } else {
            request = new Request(command, arg);
        }
        Program.getInstance().getRequestLogic().send(request);
    }

    private static void sendCommand(String commandName) {
        Command command = commands.get(commandName);
        Request request;
        MusicBand musicBand;
        if (Arrays.asList(new RemoveAllGreaterCommand().getName(), new RemoveAllLowerCommand().getName()).contains(commandName)) {
            musicBand = MusicBandScanner.scan();
            request = new Request(command, musicBand);
        } else {
            request = new Request(command);
        }

        Program.getInstance().getRequestLogic().send(request);
    }

    private static String getResponse() {
        return Program.getInstance().getResponseLogic().receive();
    }
}
