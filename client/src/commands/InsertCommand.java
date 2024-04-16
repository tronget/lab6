package commands;

public class InsertCommand extends CommandWithMB {
    public InsertCommand() {
        name = "insert";
        description = "insert null {element} : добавить новый элемент с заданным ключом";
    }

    /**
     * Метод добавляет новый элемент с заданным ключом.
     * @param key ключ по которому элемент запишется в коллекцию
     */
    @Override
    public void execute(String key) {}
}
