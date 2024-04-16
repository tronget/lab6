package commands;


public class InfoCommand extends Command {
    public InfoCommand() {
        name = "info";
        description = "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    /**
     * Метод выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     */
    @Override
    public void execute() {
    }
}
