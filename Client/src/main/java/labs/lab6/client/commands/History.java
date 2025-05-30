package labs.lab6.client.commands;


import labs.lab6.client.utility.Client;
import labs.lab6.common.exceptions.ServerConnectionException;
import labs.lab6.common.network.requests.HistoryRequest;
import labs.lab6.common.network.responses.HistoryResponse;
import labs.lab6.common.utility.Console;

import java.util.List;
import java.util.Objects;

/**
 * Команда 'history'. Выводит историю команд.
 */
public class History extends Command {
    private static final int COUNT_DISPLAY_COMMAND = 15;
    private final Console console;
    private final Client client;

    public History(Console console, Client client) {
        super("history", "выводит историю команд");
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
            if (args.length > 0) throw new IllegalArgumentException("Неправильное количество аргументов");

            var response = client.sendRequest(new HistoryRequest(COUNT_DISPLAY_COMMAND));
            if (!response.getErrorMessage().isEmpty()) {
                console.printError(response.getErrorMessage());
                return false;
            }

            if (response instanceof HistoryResponse historyResponse) {
                List<String> commandHistory = historyResponse.getHistoryMessages();
                if (commandHistory.isEmpty()) {
                    console.println("История команд пуста");
                    return true;
                }
                console.println("Последние " + commandHistory.size() + " команд:");
                commandHistory.forEach(console::println);
                return true;
            }
            console.printError("Получен неверный ответ на запрос");
            return false;
        } catch (IllegalArgumentException e) {
            console.println(e.getMessage());
            console.println("Использование: '" + getName() + "'");
            return false;
        } catch (ServerConnectionException e) {
            console.printError(e.getMessage());
            return false;
        }
    }
}
