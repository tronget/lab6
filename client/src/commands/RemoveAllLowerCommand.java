package commands;


public class RemoveAllLowerCommand extends CommandWithMB {
    public RemoveAllLowerCommand() {
        name = "remove_lower";
        description = "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

    /**
     * Метод удаляет из коллекции все элементы, меньшие, чем заданный.
     */
    @Override
    public void execute() {
    }
}