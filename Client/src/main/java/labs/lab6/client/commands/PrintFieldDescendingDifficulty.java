package labs.lab6.client.commands;


import labs.lab6.client.utility.Client;
import labs.lab6.common.exceptions.ServerConnectionException;
import labs.lab6.common.models.Difficulty;
import labs.lab6.common.network.requests.PrintFieldDescendingDifficultyRequest;
import labs.lab6.common.network.responses.PrintFieldDescendingDifficultyResponse;
import labs.lab6.common.utility.Console;

import java.util.List;

/**
 * Команда 'print_field_descending_difficulty'. Выводит значение поля {@code difficulty} всех элементов в порядке убывания.
 */
public class PrintFieldDescendingDifficulty extends Command {
    private final Console console;
    private final Client client;

    public PrintFieldDescendingDifficulty(Console console, Client client) {
        super("print_field_descending_difficulty", "вывести значение поля difficulty всех элементов " +
                "в порядке убывания");
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

            var response = client.sendRequest(new PrintFieldDescendingDifficultyRequest());
            if (!response.getErrorMessage().isEmpty()) {
                console.print(response.getErrorMessage());
                return false;
            }

            if (response instanceof PrintFieldDescendingDifficultyResponse printResponse) {
                List<Difficulty> difficulties = printResponse.getDifficulties();
                console.println(difficulties.isEmpty() ? "Список пуст" : "Результат:");
                difficulties.forEach(console::println);
                return true;
            }
            console.printError("Получен неверный ответ на запрос");
            return false;
        } catch (IllegalArgumentException e) {
            console.printError(e.getMessage());
            console.println("использование: '" + getName() + "'");
            return false;
        } catch (ServerConnectionException e) {
            console.printError(e.getMessage());
            return false;
        }
    }
}
