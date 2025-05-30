package labs.lab6.server.commands;

import labs.lab6.common.network.requests.Request;
import labs.lab6.common.network.responses.Response;

/**
 * Абстрактная команда с именем и описанием.
 */
public abstract class Command {
    private final String name;
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return Название и использование команды
     */
    public String getName() {
        return name;
    }

    /**
     * @return Описание команды
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Command command = (Command) obj;
        return name.equals(command.name) && description.equals(command.description);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + description.hashCode();
    }

    @Override
    public String toString() {
        return "Command{" + "name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }

    /**
     * Выполняет команду при заданных аргументах.
     * @param request запрос на выполнение команды
     * @return Ответ с результатом выполнения команды
     */
    public abstract Response apply(Request request);
}
