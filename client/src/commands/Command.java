package commands;

import java.io.Serializable;

public abstract class Command implements Serializable {
    protected String name;
    protected String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public void execute() {

    }
    public void execute(String arg) {

    }
}
