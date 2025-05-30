package labs.lab6.common.utility;

import java.io.Serializable;

public class CommandInfo implements Serializable {

    private final String name;
    private final String description;

    public CommandInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
