package commands;

import network.ResponseBuilder;
import stateManager.CollectionManager;
import utility.Program;

import java.util.HashSet;
import java.util.Set;

public class PrintUniqueParticipantsCommand extends Command {
    public PrintUniqueParticipantsCommand() {
        name = "print_unique_number_of_participants";
        description = "print_unique_number_of_participants : вывести уникальные значения поля numberOfParticipants всех элементов в коллекции";
    }

    /**
     * Метод выводит уникальные значения поля numberOfParticipants всех элементов в коллекции.
     */
    @Override
    public void execute() {
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        Set<Integer> set = new HashSet<>();
        CollectionManager.getInstance().getCollection().values().forEach(el -> set.add(el.getNumberOfParticipants()));
        responseBuilder.add(set.toString());
    }
}
