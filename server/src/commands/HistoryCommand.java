package commands;

import network.ResponseBuilder;
import utility.Program;

public class HistoryCommand extends Command {
    public HistoryCommand() {
        name = "history";
        description = "history : вывести последние 10 команд (без их аргументов)";
    }
    /**
     * Метод, показывающий последние 10 команд без их аргументов.
     */
    @Override
    public void execute() {
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        responseBuilder.add(Program.getInstance().printHistory());
    }
}
