package commands;


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
    }
}
