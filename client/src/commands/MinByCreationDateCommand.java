package commands;


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
    }
}
