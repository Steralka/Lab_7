package labs.lab6.client.commands;


import labs.lab6.client.utility.Client;
import labs.lab6.common.exceptions.ServerConnectionException;
import labs.lab6.common.network.requests.HelpRequest;
import labs.lab6.common.network.responses.HelpResponse;
import labs.lab6.common.utility.Console;

/**
 * Команда 'help'. Выводит справку по доступным командам.
 */
public class Help extends Command {
    private final Console console;
    private final Client client;

    public Help(Console console, Client client) {
        super("help", "вывести справку по доступным командам");
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

            var response = client.sendRequest(new HelpRequest());
            if (!response.getErrorMessage().isEmpty()) {
                console.printError(response.getErrorMessage());
                return false;
            }
            if (response instanceof HelpResponse helpResponse) {
                helpResponse.getHelps().forEach(help ->
                        console.printTable(help.getName(), help.getDescription())
                );
                return true;
            }
            console.printError("Получен неверный ответ на запрос");
            return false;
        } catch (IllegalArgumentException e) {
            console.println("Неверное количество аргументов");
            console.println("Использование: '" + getName() + "'");
            return false;
        } catch (ServerConnectionException e) {
            console.printError(e.getMessage());
            return false;
        }
    }
}
