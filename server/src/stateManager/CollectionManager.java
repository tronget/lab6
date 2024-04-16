package stateManager;

import commands.*;
import exceptions.ExtraArgumentException;
import exceptions.NecessaryArgumentException;
import fileManager.FileReader;
import models.MusicBand;
import utility.ConsoleWriter;
import utility.Program;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.function.Consumer;

/**
 * Класс-Синглтон, хранящий в себе коллекцию и реализацию команд, которые управляют самой коллекцией.
 */
public class CollectionManager {
    private Hashtable<String, MusicBand> collection;
    private final HashMap<String, Runnable> commandsMapWithoutArgs = new HashMap<>();
    private final HashMap<String, Consumer<String>> commandsMapWithArg = new HashMap<>();
    private static CollectionManager instance;

    /**
     * При создании CollectionManager создается объект File,
     * который принимает путь к файлу через системную переменную.
     * Если файл не существует, он создается; если файл не является файлом, программа останавливается.
     * Если все прошло успешно, то загружаются данные из файла в поле collection.
     * Затем добавляются все команды в hashmap-у commandsMapWithoutArgs или commandsMapWithArg.
     */
    private CollectionManager() {
        try {
            File file = new File(System.getenv(Program.SYSTEM_VAR));
            if (!file.exists()) {
                file.createNewFile();
            } else if (!file.isFile()) {
                throw new IOException();
            }
            collection = loadData(file);
        } catch (IOException e) {
            ConsoleWriter.getInstance().alert("Ошибка в чтении данных с файла!");
            Program.getInstance().stop();
        }
        commandsMapWithoutArgs.put(new HelpCommand().getName(), new HelpCommand()::execute);
        commandsMapWithoutArgs.put(new InfoCommand().getName(), new InfoCommand()::execute);
        commandsMapWithoutArgs.put(new ShowCommand().getName(), new ShowCommand()::execute);
        commandsMapWithArg.put(new InsertCommand().getName(), new InsertCommand()::execute);
        commandsMapWithArg.put(new UpdateCommand().getName(), new UpdateCommand()::execute);
        commandsMapWithArg.put(new RemoveCommand().getName(), new RemoveCommand()::execute);
        commandsMapWithoutArgs.put(new ClearCommand().getName(), new ClearCommand()::execute);
        commandsMapWithArg.put(new ScriptCommand().getName(), new ScriptCommand()::execute);
        commandsMapWithoutArgs.put(new ExitCommand().getName(), new ExitCommand()::execute);
        commandsMapWithoutArgs.put(new RemoveAllGreaterCommand().getName(), new RemoveAllGreaterCommand()::execute);
        commandsMapWithoutArgs.put(new RemoveAllLowerCommand().getName(), new RemoveAllLowerCommand()::execute);
        commandsMapWithoutArgs.put(new HistoryCommand().getName(), new HistoryCommand()::execute);
        commandsMapWithoutArgs.put(new MinByCreationDateCommand().getName(), new MinByCreationDateCommand()::execute);
        commandsMapWithoutArgs.put(new PrintAscendingCommand().getName(), new PrintAscendingCommand()::execute);
        commandsMapWithoutArgs.put(new PrintUniqueParticipantsCommand().getName(), new PrintUniqueParticipantsCommand()::execute);
    }

    /**
     * Возвращает исходную коллекцию.
     * @return исходная коллекция
     */
    public Hashtable<String, MusicBand> getCollection() {
        return new Hashtable<>(collection);
    }

    /**
     * Устанавливает коллекцию.
     * @param ht новая коллекция
     */
    public void setCollection(Hashtable<? extends String, ? extends MusicBand> ht) {
        collection = new Hashtable<>(ht);
    }

    /**
     * Метод, вызывающий исполнение команды без аргумента.
     *
     * @param command команда для исполнения
     * @throws NecessaryArgumentException возникает исключение, если переданная команда требует аргумент
     */
    protected void executeCommand(String command) throws NecessaryArgumentException {
        try {
            commandsMapWithoutArgs.get(command).run();
        } catch (NullPointerException e) {
            NecessaryArgumentException e1 = new NecessaryArgumentException(command);
            e1.initCause(e);
            throw e1;
        }
    }

    /**
     * Метод, вызывающий исполнение команды с необходимым аргументом.
     *
     * @param command         команда для исполнения
     * @param commandArgument аргумент команды
     * @throws ExtraArgumentException возникает исключение, если переданная команда требует аргумент
     */
    protected void executeCommand(String command, String commandArgument) throws ExtraArgumentException {
        try {
            commandsMapWithArg.get(command).accept(commandArgument);
        } catch (NullPointerException e) {
            ExtraArgumentException e1 = new ExtraArgumentException(command);
            e1.initCause(e);
            throw e1;
        }
    }

    /**
     * Метод проверяет, есть ли в коллекции элемент с заданным ключом.
     *
     * @param key ключ для проверки
     * @return boolean значение: true, если в коллекции есть элемент с таким ключом, иначе false
     */
    public boolean hasSuchKey(String key) {
        return collection.containsKey(key);
    }

    /**
     * Метод, возвращающий данные с файла, представленные в виде Hashtable
     *
     * @param file путь к файлу с информацией о коллекции
     * @return Данные с файла, представленные в виде Hashtable
     * @throws IOException ошибка при считывании несуществующего файла
     */
    private Hashtable<String, MusicBand> loadData(File file) throws IOException {
        return new FileReader().readFromCsv(file);
    }

    public static CollectionManager getInstance() {
        if (instance == null) {
            instance = new CollectionManager();
        }
        return instance;
    }
}
