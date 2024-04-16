package utility;

import stateManager.CommandsManager;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, позволяющий сканировать данные с консоли.
 */
public class ConsoleScanner implements Scannable {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Метод считывает строку, используя стандартный поток ввода.
     * Затем определяет команду, которая будет выполняться.
     */
    @Override
    public void scan() {
        try {
            String[] userInput = scanner.nextLine().trim().split("\\s+");
            if (Program.getInstance().isWorkingStatus()) {
                CommandsManager.defineCommand(userInput);
            }
        } catch (NoSuchElementException e) {
            Program.getInstance().stop();
        }
    }
}
