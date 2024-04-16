package commands;


public class UpdateCommand extends CommandWithMB {
    public UpdateCommand() {
        name = "update";
        description = "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    /**
     * Метод, обновляющий элемент из коллекции по переданному ключу.
     * @param id id элемента, который надо удалить
     */
    @Override
    public void execute(String id) {
    }
}
