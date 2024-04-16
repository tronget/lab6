package commands;

import models.MusicBand;
import network.ResponseBuilder;
import stateManager.CollectionManager;
import utility.Program;

import java.util.Hashtable;

public class InfoCommand extends Command {
    public InfoCommand() {
        name = "info";
        description = "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    /**
     * Метод выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     */
    @Override
    public void execute() {
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        Hashtable<String, MusicBand> collection = CollectionManager.getInstance().getCollection();
        String collectionName = collection.getClass().getSimpleName();
        responseBuilder.add("Коллекция %s: \"Музыкальные группы\"".formatted(collectionName));
        responseBuilder.add("Кол-во элементов: %s".formatted(collection.size()));
    }
}
