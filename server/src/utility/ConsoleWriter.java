package utility;

/**
 * Класс, реализующий вывод в консоль.
 */
public class ConsoleWriter {
    private static ConsoleWriter instance;
    private final ColoredText coloredText = new ColoredText();

    /**
     * Класс, реализующий цветной вывод в консоль.
     */
    private class ColoredText {
        private static final String ANSI_RESET = "\u001B[0m";
        private static final String ANSI_BLACK = "\u001B[30m";
        private static final String ANSI_RED = "\u001B[31m";

        /**
         * Метод окрашивает сообщение в определенный цвет.
         *
         * @param message сообщение, которое надо окрасить
         * @param color   цвет, в который сообщение будет окрашено
         * @return строка, окрашенная в определенный цвет
         */
        public String colorizeText(String message, String color) {
            String colorOfString = switch (color) {
                case "red" -> ANSI_RED;
                case "black" -> ANSI_BLACK;
                default -> throw new IllegalStateException("Unexpected value: " + color);
            };
            return colorOfString + message + ANSI_RESET;
        }
    }

    /**
     * Метод выводит в консоль переданное сообщение, окрашенное в красный цвет.
     *
     * @param message сообщение, которое будет выведено
     */
    public void alert(String message) {
        System.out.println(coloredText.colorizeText(message, "red"));
    }

    /**
     * Метод выводит в консоль переданное сообщение.
     *
     * @param message сообщение, которое будет выведено
     */
    public void print(String message) {
        System.out.print(message);
    }

    public static synchronized ConsoleWriter getInstance() {
        if (instance == null) {
            instance = new ConsoleWriter();
        }
        return instance;
    }
}
