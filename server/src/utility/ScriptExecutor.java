package utility;

import fileManager.FileReader;
import network.ResponseBuilder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

/**
 * Класс, отвечающий за исполнение скрипта.
 */
public class ScriptExecutor {
    private boolean scriptExecution = false;
    private Scanner scannerForScript;
    private final Stack<String> recursionScripts = new Stack<>();
    private final Stack<Boolean> scriptErrorFlags = new Stack<>();
    private int recursionDepth;
    private final InputStream originalInputStream = System.in;
    private final HashSet<File> files = new HashSet<>();
    private static ScriptExecutor instance;

    private ScriptExecutor() {
    }

    public static ScriptExecutor getInstance() {
        if (instance == null) {
            instance = new ScriptExecutor();
        }
        return instance;
    }


    /**
     * Устанавливает булево значение наличия ошибки, которая могла
     * возникнуть во время исполнения скрипта.
     * Ошибка устанавливается в последнем элементе стека scriptErrorFlags.
     */
    public void setScriptErrorFlags() {
        scriptErrorFlags.set(scriptErrorFlags.size() - 1, true);
    }


    /**
     * Возвращает булево значение статуса выполнения скрипта.
     *
     * @return булево значение статуса выполнения скрипта
     */
    public boolean isScriptExecution() {
        return scriptExecution;
    }


    /**
     * Возвращает сканер, который используется для исполнения скрипта.
     *
     * @return сканер, который используется для исполнения скрипта
     */
    public Scanner getScannerForScript() {
        return scannerForScript;
    }


    /**
     * Возвращает множество файлов скриптов, которые уже выполнялись/выполняются в сессии.
     *
     * @return множество файлов скриптов, которые уже выполнялись/выполняются в сессии
     */
    public HashSet<File> getFiles() {
        return new HashSet<>(files);
    }


    /**
     * Возвращает стек, содержащий информацию об оставшихся командах из скриптов,
     * в которых были команды execute_script
     *
     * @return стек, содержащий информацию об оставшихся командах из скриптов
     */
    public Stack<String> getRecursionScripts() {
        Stack<String> newStack = new Stack<>();
        Collections.copy(newStack, recursionScripts);
        return newStack;
    }

    /**
     * Метод помещает в стек recursionScripts строку, содержащую оставшиеся команды.
     * Эти команды необходимо выполнить позже, когда рекурсивные скрипты завершатся.
     *
     * @param scriptText строка, в которой содержатся команды
     */
    public void updateRecursionScripts(String scriptText) {
        recursionScripts.push(scriptText);
    }


    /**
     * Метод, выполняющий скрипт.
     *
     * @param fileName имя файла скрипта
     */
    public void executeScript(String fileName) {
        ResponseBuilder responseBuilder = Program.getInstance().getResponseBuilder();
        scriptExecution = true;
        File f = new File(fileName);
        if (files.contains(f)) {
            responseBuilder.add("Execute_script не выполняется повторно!");
            return;
        }
        // добавление в стек информации об индикаторе ошибки во время исполнения определенного скрипта;
        scriptErrorFlags.push(false);
        files.add(f);
        recursionDepth++;
        try {
            String s = new FileReader().readFromFile(f);
            executeCommands(s);
            if (scriptErrorFlags.pop()) {
                responseBuilder.add("Скрипт выполнился с ошибкой!");
            } else {
                responseBuilder.add("Скрипт успешно выполнился!");
            }
        } catch (IOException e) {
            responseBuilder.add("Ошибка при чтении скрипта!");
            endOfExecution();
        }
    }

    /**
     * Метод определяет и исполняет команды скрипта.
     *
     * @param script строка, содержащая команды скрипта
     */
    public void executeCommands(String script) {
        InputStream inputStream = new ByteArrayInputStream(script.getBytes());
        System.setIn(inputStream);
        scannerForScript = new Scanner(System.in);
        ScriptScanner scriptScanner = new ScriptScanner(scannerForScript);

        while (scannerForScript.hasNextLine()) {
            scriptScanner.scan();
        }

        endOfExecution();
    }

    /**
     * Метод, который необходимо выполнять после выполнения команд скрипта
     * и после возникновения исключений во время исполнения скрипта.
     */
    private void endOfExecution() {
        scriptExecution = false;
        recursionDepth--;
        if (!recursionScripts.isEmpty()) {
            scriptExecution = true;
            recursionDepth++;
            executeCommands(recursionScripts.pop());
        }
        if (recursionDepth == 0) {
            System.setIn(originalInputStream);
            files.clear();
        }
    }
}
