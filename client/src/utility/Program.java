package utility;

import commands.ExitCommand;
import network.RequestLogic;
import network.ResponseLogic;
import network.ServerConnection;
import shared.Request;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.Arrays;
import java.util.List;


/**
 * Класс-синглтон программы.
 * Позволяет запускать и останавливать работу программы.
 * Хранит в себе историю команд, список команд, название системной переменной,
 * индикатор работы программы в виде булева значения,
 * индикатор наличия ошибки в виде булева значения.
 */
public class Program implements ProgramInterface {
    private static Program instance;
    private boolean workingStatus;
    private boolean isError;
    private DatagramSocket socket;
    private ResponseLogic responseLogic;
    private RequestLogic requestLogic;

    public final List<String> commands = Arrays.asList(
            "help",
            "info",
            "show",
            "insert",
            "update",
            "remove_key",
            "clear",
            "execute_script",
            "exit",
            "remove_greater",
            "remove_lower",
            "history",
            "min_by_creation_date",
            "print_ascending",
            "print_unique_number_of_participants"
    );


    private Program() {
        try {
            socket = new DatagramSocket();
            ServerConnection serverConnection = new ServerConnection("localhost", socket);
            serverConnection.connect();
            responseLogic = new ResponseLogic(socket);
            requestLogic = new RequestLogic(socket);
        } catch (IOException e) {
            System.out.println("Ошибка при подключении к серверу");
            isError = true;
            stop();
        }
    }

    public ResponseLogic getResponseLogic() {
        return responseLogic;
    }

    public RequestLogic getRequestLogic() {
        return requestLogic;
    }

    /**
     * Возвращает список команд.
     *
     * @return список команд.
     */
    public List<String> getCommands() {
        return commands;
    }

    /**
     * Возвращает статус работы программы в булевом значении.
     *
     * @return статус работы программы в булевом значении
     */
    public boolean isWorkingStatus() {
        return workingStatus;
    }

    /**
     * Метод, отвечающий за запуск программы.
     * Запуск происходит, если нет ошибок и статус программы в выключенном состоянии.
     */
    @Override
    public void start() {
        if (!workingStatus && !isError) {
            workingStatus = true;
            ConsoleScanner scanner = new ConsoleScanner();
            while (workingStatus) {
                scanner.scan();
            }
        }
    }

    /**
     * Метод, отвечающий за остановку программы.
     */
    @Override
    public void stop() {
        Request request = new Request(new ExitCommand());
        Program.getInstance().getRequestLogic().send(request);
        workingStatus = false;
        System.out.println("Завершение работы.");
        System.out.close();
    }


    public static synchronized Program getInstance() {
        if (instance == null) {
            instance = new Program();
        }
        return instance;
    }
}
