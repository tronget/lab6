package commands;

import network.ResponseBuilder;
import stateManager.CollectionManager;
import utility.Program;

import java.util.Hashtable;

public class ClearCommand extends Command {
    public ClearCommand() {
        name = "clear";
        description = "clear : очистить коллекцию";
    }

    /**
     * Метод, очищающий коллекцию.
     */
    @Override
    public void execute() {
        CollectionManager.getInstance().setCollection(new Hashtable<>());
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        responseBuilder.add("Коллекция очищена.");
    }
}
