package exceptions;

public class NecessaryArgumentException extends CommandInputException {
    public NecessaryArgumentException(String command) {
        super(command);
    }

    @Override
    public String getMessage() {
        return String.format("Команда %s требует аргумент!", command);
    }
}
