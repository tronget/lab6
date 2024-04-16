package exceptions;

public class NonexistentKeyException extends KeyException {

    public NonexistentKeyException(String id) {
        super(id);
    }

    @Override
    public String getMessage() {
        return String.format("Элемента с ключом %s в коллекции нет!", key);
    }
}
