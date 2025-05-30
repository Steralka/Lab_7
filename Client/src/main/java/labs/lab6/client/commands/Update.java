package labs.lab6.client.commands;


import labs.lab6.client.forms.LabWorkForm;
import labs.lab6.client.utility.Client;
import labs.lab6.common.exceptions.InvalidFormException;
import labs.lab6.common.exceptions.ServerConnectionException;
import labs.lab6.common.models.LabWork;
import labs.lab6.common.network.requests.UpdateRequest;
import labs.lab6.common.network.responses.UpdateResponse;
import labs.lab6.common.utility.Console;

import java.util.Objects;

/**
 * Команда 'update'. Обновляет значение элемента по {@code id}.
 */
public class Update extends Command {
    private final Console console;
    private final Client client;

    public Update(Console console, Client client) {
        super("update {id}", "обновить значение элемента по id");
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

            console.println("* Создание ... ");
            LabWork labWork = new LabWorkForm(console).build();
            if (Objects.isNull(labWork)) throw new InvalidFormException();
            labWork.setId(id);

            var response = client.sendRequest(new UpdateRequest(labWork));
            if (!response.getErrorMessage().isEmpty()) {
                console.println(response.getErrorMessage());
                return false;
            }
            if (response instanceof UpdateResponse) {
                console.println("Элемент с id = " + id + " успешно обновлён");
                return true;
            }
            console.printError("Получен неверный ответ на запрос");
            return false;
        } catch (NumberFormatException e) {
            console.println("Аргумент '" + args[0] + "' не является типом Long");
            return false;
        } catch (IllegalArgumentException e) {
            console.printError(e.getMessage());
            console.println("Использование: '" + getName() + "'");
            return false;
        } catch (InvalidFormException | ServerConnectionException e) {
            console.printError(e.getMessage());
            return false;
        }
    }
}

