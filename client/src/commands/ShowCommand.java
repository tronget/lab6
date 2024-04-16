package commands;

public class ShowCommand extends Command {
    public ShowCommand() {
        name = "show";
        description = "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    /**
     * Метод, показывающий последние 10 команд без их аргументов.
     */
    @Override
    public void execute() {
    }
}
