package commands;

import models.MusicBand;
import network.ResponseBuilder;
import stateManager.CollectionManager;
import utility.Program;

import java.util.Comparator;
import java.util.Hashtable;

public class MinByCreationDateCommand extends Command {
    public MinByCreationDateCommand() {
        name = "min_by_creation_date";
        description = "min_by_creation_date : вывести любой объект из коллекции, значение поля creationDate которого является минимальным";
    }

    /**
     * Выводит любой объект из коллекции, значение поля creationDate которого является минимальным.
     */
    @Override
    public void execute() {
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        Hashtable<String, MusicBand> collection = CollectionManager.getInstance().getCollection();
        if (collection.isEmpty()) {
            responseBuilder.add("Пустая коллекция.");
            return;
        }
        MusicBand min = collection.values().stream().sorted(Comparator.comparing(MusicBand::getCREATION_DATE)).toList().get(0);
        responseBuilder.add(min.toString());
    }
}
