package labs.lab6.client.commands;


import labs.lab6.client.forms.LabWorkForm;
import labs.lab6.client.utility.Client;
import labs.lab6.common.exceptions.InvalidFormException;
import labs.lab6.common.exceptions.ServerConnectionException;
import labs.lab6.common.models.LabWork;
import labs.lab6.common.network.requests.RemoveLowerRequest;
import labs.lab6.common.network.responses.RemoveLowerResponse;
import labs.lab6.common.utility.Console;

import java.util.Objects;

/**
 * Команда 'remove_lower'. Удаляет из коллекции все элементы, меньшие, чем заданный.
 */
public class RemoveLower extends Command {
    private final Console console;
    private final Client client;

    public RemoveLower(Console console, Client client) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
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

            console.println("* Создание... ");
            LabWork targetLab = new LabWorkForm(console).build();
            if (Objects.isNull(targetLab)) throw new InvalidFormException();

            var response = client.sendRequest(new RemoveLowerRequest(targetLab));
            if (!response.getErrorMessage().isEmpty()) {
                console.println(response.getErrorMessage());
                return false;
            }

            if (response instanceof RemoveLowerResponse removeLowerResponse) {
                console.println("Удалено " + removeLowerResponse.getRemoved() + " элементов");
                return true;
            }
            console.printError("Получен неверный ответ на запрос");
            return false;
        } catch (IllegalArgumentException e) {
            console.printError(e.getMessage());
            console.println("Использование: '" + getName() + "'");
            return false;
        } catch (InvalidFormException e) {
            console.println("Непредвиденная ошибка при создании элемента");
            return false;
        } catch (ServerConnectionException e) {
            console.printError(e.getMessage());
            return false;
        }
    }
}
