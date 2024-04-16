package commands;

import network.ResponseBuilder;
import utility.Program;

public class HelpCommand extends Command {
    public HelpCommand() {
        name = "help";
        description = "help : вывести справку по доступным командам";
    }
    /**
     * Метод выводит справку по доступным командам.
     */
    @Override
    public void execute() {
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        responseBuilder.add(this.description);
        responseBuilder.add(new InfoCommand().description);
        responseBuilder.add(new ShowCommand().description);
        responseBuilder.add(new InsertCommand().description);
        responseBuilder.add(new UpdateCommand().description);
        responseBuilder.add(new RemoveCommand().description);
        responseBuilder.add(new ClearCommand().description);
        responseBuilder.add(new ScriptCommand().description);
        responseBuilder.add(new ExitCommand().description);
        responseBuilder.add(new RemoveAllGreaterCommand().description);
        responseBuilder.add(new RemoveAllLowerCommand().description);
        responseBuilder.add(new HistoryCommand().description);
        responseBuilder.add(new MinByCreationDateCommand().description);
        responseBuilder.add(new PrintAscendingCommand().description);
        responseBuilder.add(new PrintUniqueParticipantsCommand().description);
    }
}
