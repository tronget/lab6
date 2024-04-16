package utility;


/**
 * Класс, генерирующий Id.
 */
public class IdGenerator {
    /**
     * Генерит Id с помощью времени в миллисекундах прошедших с 1 января 1970 года.
     *
     * @return сгенерированное Id
     */
    public static long generate() {
        return System.currentTimeMillis();
    }

    /**
     * Генерит Id, при помощи функции {@link #hashCode()}.
     * @param obj объект, для которого необходимо сгенерировать Id.
     * @return сгенерированное Id
     */
    public static <T> long generate(T obj) {
        return obj.hashCode();
    }
}
