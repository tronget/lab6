package commands;

import fileManager.FileWriter;
import network.ResponseBuilder;
import stateManager.CollectionManager;
import utility.Program;
import utility.ScriptExecutor;

import java.io.File;

public class ExitCommand extends Command {

    public ExitCommand() {
        name = "exit";
        description = "exit : завершить программу (с сохранением в файл)";
    }

    /**
     * Метод, предлагающий завершить программу.
     */
    @Override
    public void execute() {
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        if (ScriptExecutor.getInstance().isScriptExecution()) {
            responseBuilder.add("Завершение работы.");
            Program.getInstance().getResponseLogic().send(Program.getInstance().getRequestLogic().getClientAddress(), responseBuilder.get());
            return;
        }
        File file = new File(System.getenv(Program.SYSTEM_VAR));
        if (new FileWriter().writeToFile(file, CollectionManager.getInstance().getCollection())) {
            responseBuilder.add("Коллекция сохранена!");
        } else {
            responseBuilder.add("Коллекцию не получилось сохранить!");
        }
    }

    @Override
    public void execute(String arg) {
        super.execute(arg);
    }
}
