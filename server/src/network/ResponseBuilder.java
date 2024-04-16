package network;

public class ResponseBuilder {
    private final StringBuilder response = new StringBuilder();
    public String get() {
        String s = response.toString();
        clear();
        return s;
    }

    public void add(String s) {
        response.append(s);
        response.append('\n');
    }

    private void clear() {
        response.setLength(0);
    }
}
