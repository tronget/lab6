package commands;

import exceptions.ExistantKeyException;
import models.MusicBand;
import network.ResponseBuilder;
import stateManager.CollectionManager;
import utility.IdGenerator;
import utility.MusicBandScanner;
import utility.Program;
import utility.ScriptExecutor;

import java.util.Date;
import java.util.Hashtable;

public class InsertCommand extends CommandWithMB {
    public InsertCommand() {
        name = "insert";
        description = "insert null {element} : добавить новый элемент с заданным ключом";
    }

    /**
     * Метод добавляет новый элемент с заданным ключом.
     *
     * @param key ключ по которому элемент запишется в коллекцию
     */
    @Override
    public void execute(String key) {
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        Hashtable<String, MusicBand> collection = CollectionManager.getInstance().getCollection();
        try {
            if (CollectionManager.getInstance().hasSuchKey(key)) {
                throw new ExistantKeyException(key);
            }
            if (ScriptExecutor.getInstance().isScriptExecution()) {
                musicBand = MusicBandScanner.scan();
                if (musicBand == null) {
                    return;
                }
            } else {
                musicBand.setID(IdGenerator.generate());
                musicBand.setCREATION_DATE(new Date());
            }

            collection.put(key, musicBand);
            CollectionManager.getInstance().setCollection(collection);
            responseBuilder.add("Элемент сохранен в коллекции.");

        } catch (ExistantKeyException e) {
            responseBuilder.add(e.getMessage());
        }
    }
}
