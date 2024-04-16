package commands;


public class RemoveCommand extends Command {
    public RemoveCommand() {
        name = "remove_key";
        description = "remove_key null : удалить элемент из коллекции по его ключу";
    }

    /**
     * Метод, удаляющий элемент из коллекции по переданному ключу.
     * @param key ключ элемента, который надо удалить
     */
    @Override
    public void execute(String key) {
    }
}
