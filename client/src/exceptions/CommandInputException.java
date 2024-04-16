package exceptions;

public abstract class CommandInputException extends Exception {
    protected final String command;

    public CommandInputException(String command) {
        super();
        this.command = command;
    }

    @Override
    public String getMessage() {
        return String.format("Ошибка с командой %s!", command);
    }
}
