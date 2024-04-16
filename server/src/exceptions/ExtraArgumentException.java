package exceptions;

public class ExtraArgumentException extends CommandInputException {
    public ExtraArgumentException(String command) {
        super(command);
    }

    @Override
    public String getMessage() {
        return String.format("Команда %s не должна иметь аргументов!", command);
    }
}
