package stateManager;

import exceptions.ExtraArgumentException;
import exceptions.NecessaryArgumentException;
import network.ResponseBuilder;
import utility.Program;

import java.util.List;


/**
 * Класс, отвечающий за определение команд и дальнейшее их исполнение.
 */
public class CommandsManager {
    public static final List<String> commands = Program.getInstance().getCommands();
    private static final CollectionManager collectionManager = CollectionManager.getInstance();

    /**
     * Метод определяет команду для последующего исполнения.
     *
     * @param command строка считанная из консоли, в которой содержится команда
     */
    public static void defineCommand(String[] command) {
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        String keyWord = command[0];
        if (commands.contains(keyWord)) {
            Program.getInstance().updateHistory(keyWord);
            if (command.length > 2) {
                responseBuilder.add("Too much arguments!");
            } else if (command.length == 2) {
                try {
                    collectionManager.executeCommand(keyWord, command[1]);
                } catch (ExtraArgumentException e) {
                    responseBuilder.add(e.getMessage());
                }
            } else {
                try {
                    collectionManager.executeCommand(keyWord);
                } catch (NecessaryArgumentException e) {
                    responseBuilder.add(e.getMessage());
                }
            }
        } else if (keyWord.isEmpty()) {
            responseBuilder.add("Пустая команда!");
        } else {
            responseBuilder.add("Такой команды нет!");
        }
    }
}
