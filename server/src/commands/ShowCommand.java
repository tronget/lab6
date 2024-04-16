package commands;

import models.MusicBand;
import network.ResponseBuilder;
import stateManager.CollectionManager;
import utility.Program;

import java.util.Hashtable;

public class ShowCommand extends Command{
    public ShowCommand() {
        name = "show";
        description = "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    /**
     * Метод, показывающий последние 10 команд без их аргументов.
     */
    @Override
    public void execute() {
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        Hashtable<String, MusicBand> collection = CollectionManager.getInstance().getCollection();
        if (!collection.isEmpty()) {
            collection.forEach((k, v) -> responseBuilder.add("%s : %s".formatted(k, v)));
            return;
        }
        responseBuilder.add("Пустая коллекция.");
    }
}
