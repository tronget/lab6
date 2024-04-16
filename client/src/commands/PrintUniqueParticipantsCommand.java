package commands;

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
    }
}
