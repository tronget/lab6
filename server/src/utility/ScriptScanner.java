package utility;

import commands.ExitCommand;
import commands.ScriptCommand;
import stateManager.CommandsManager;

import java.io.File;
import java.util.Scanner;

/**
 * Класс, позволяющий сканировать данные из скрипта.
 */
public class ScriptScanner {
    private final Scanner scanner;
    public ScriptScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Метод считывает строку, используя сканер, определенный в конструкторе.
     * Затем определяет команду, которая будет выполняться.
     */
    public void scan() {
        String[] userInput = scanner.nextLine().trim().split("\\s+");
        if (Program.getInstance().isWorkingStatus()) {
            if (userInput[0].equals(new ScriptCommand().getName()) && userInput.length == 2) {
                File file = new File(userInput[1]);
                // Если такого файла еще не было,
                // то сохранить оставшийся текст из скрипта в resursionScripts;
                if (!ScriptExecutor.getInstance().getFiles().contains(file)) {
                    StringBuilder scriptText = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        scriptText.append(scanner.nextLine()).append("\n");
                    }
                    ScriptExecutor.getInstance().updateRecursionScripts(scriptText.toString());
                }
            } else if(userInput[0].equals(new ExitCommand().getName())) {
                Program.getInstance().getResponseBuilder().add(
                        "Команда exit не выполняется в скрипте!"
                );
                return;
            }
            CommandsManager.defineCommand(userInput);
        }
    }
}
