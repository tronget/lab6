package commands;

public class PrintAscendingCommand extends Command {
    public PrintAscendingCommand() {
        name = "print_ascending";
        description = "print_ascending : вывести элементы коллекции в порядке возрастания";
    }

    /**
     * Метод выводит элементы коллекции в порядке возрастания.
     */
    @Override
    public void execute() {
    }
}
