package labs.lab6.client.commands;


import labs.lab6.client.utility.Client;
import labs.lab6.common.exceptions.ServerConnectionException;
import labs.lab6.common.network.requests.ClearRequest;
import labs.lab6.common.network.responses.ClearResponse;
import labs.lab6.common.utility.Console;

import java.util.Objects;

/**
 * Команда 'clear'. Очищает коллекцию.
 */
public class Clear extends Command {
    private final Console console;
    private final Client client;

    public Clear(Console console, Client client) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.client = client;
    }

    /**
     * Выполняет команду при заданных аргументах.
     * @param args аргументы команды
     * @return Успешность выполнения команды
     */
    @Override
    public boolean apply(String[] args) {
        try {
            if (args.length > 0) throw new IllegalArgumentException("Неверное количество аргументов");

            var response = client.sendRequest(new ClearRequest());
            if (!response.getErrorMessage().isEmpty()) {
                console.printError(response.getErrorMessage());
                return false;
            }
            if (response instanceof ClearResponse) {
                console.println("Коллекция очищена");
                return true;
            }
            console.printError("Получен неверный ответ на запрос");
            return false;
        } catch (IllegalArgumentException e) {
            console.println(e.getMessage());
            console.println("Использование: '" + getName() + "'");
            return false;
        } catch (ServerConnectionException e) {
            console.println(e.getMessage());
            return false;
        }
    }

}