package commands;

import models.MusicBand;
import network.ResponseBuilder;
import stateManager.CollectionManager;
import utility.MusicBandScanner;
import utility.Program;
import utility.ScriptExecutor;

import java.util.Hashtable;

public class RemoveAllGreaterCommand extends CommandWithMB {
    public RemoveAllGreaterCommand() {
        name = "remove_greater";
        description = "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }

    /**
     * Метод Удаляет из коллекции все элементы, меньшие, чем заданный.
     */
    @Override
    public void execute() {
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        if (ScriptExecutor.getInstance().isScriptExecution()) {
            musicBand = MusicBandScanner.scan();
            if (musicBand == null) {
                return;
            }
        }
        Hashtable<String, MusicBand> collection = CollectionManager.getInstance().getCollection();
        collection.entrySet().removeIf(
                (e) -> e.getValue().compareTo(musicBand) > 0
        );
        CollectionManager.getInstance().setCollection(collection);
        responseBuilder.add("Из коллекции удалены все элементы, превышающие заданный.");
    }
}
