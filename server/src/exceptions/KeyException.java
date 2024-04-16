package exceptions;

public abstract class KeyException extends Exception {
    protected final String key;

    public KeyException(String key) {
        this.key = key;
    }

    @Override
    public String getMessage() {
        return String.format("Ошибка с ключом %s.", key);
    }
}
