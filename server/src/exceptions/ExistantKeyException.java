package exceptions;

public class ExistantKeyException extends KeyException {

    public ExistantKeyException(String id) {
        super(id);
    }

    @Override
    public String getMessage() {
        return String.format("Элемент с ключом %s уже существует в коллекции!", key);
    }
}
