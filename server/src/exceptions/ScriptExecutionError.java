package exceptions;

public class ScriptExecutionError extends Exception {
    @Override
    public String getMessage() {
        return "Ошибка при выполнении скрипта!";
    }
}
