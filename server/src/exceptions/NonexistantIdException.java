package exceptions;

public class NonexistantIdException extends Exception {
    protected final String id;

    public NonexistantIdException(String id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("Элемента с id %s не существует.", id);
    }
}
