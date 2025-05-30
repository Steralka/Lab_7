package labs.lab6.client.commands;


import labs.lab6.client.utility.Client;
import labs.lab6.common.exceptions.ServerConnectionException;
import labs.lab6.common.network.requests.RemoveByIdRequest;
import labs.lab6.common.network.responses.RemoveByIdResponse;
import labs.lab6.common.utility.Console;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции по {@code id}.
 */
public class RemoveById extends Command {
    private final Console console;
    private final Client client;

    public RemoveById(Console console, Client client) {
        super("remove_by_id", "удалить элемент из коллекции по id");
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
            if (args.length != 1) throw new IllegalArgumentException("Неверное количество аргументов");

            long id = Long.parseLong(args[0]);

            var response = client.sendRequest(new RemoveByIdRequest(id));
            if (!response.getErrorMessage().isEmpty()) {
                console.printError(response.getErrorMessage());
                return false;
            }

            if (response instanceof RemoveByIdResponse) {
                console.println("Элемент с id = " + id + " успешно удалён");
                return true;
            }
            console.printError("Получен неверный ответ на запрос");
            return false;
        } catch (NumberFormatException e) {
            console.println("Аргумент '" + args[0] + "' не является типом Long");
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
