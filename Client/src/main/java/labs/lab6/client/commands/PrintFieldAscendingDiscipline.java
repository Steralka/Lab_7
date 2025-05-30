package labs.lab6.client.commands;


import labs.lab6.client.utility.Client;
import labs.lab6.common.exceptions.ServerConnectionException;
import labs.lab6.common.models.Discipline;
import labs.lab6.common.network.requests.PrintFieldAscendingDisciplineRequest;
import labs.lab6.common.network.responses.PrintFieldAscendingDisciplineResponse;
import labs.lab6.common.utility.Console;

import java.util.List;

/**
 * Команда 'print_field_ascending_discipline'. Выводит значение поля {@code discipline} всех элементов в порядке возрастания.
 */
public class PrintFieldAscendingDiscipline extends Command {
    private final Console console;
    private final Client client;

    public PrintFieldAscendingDiscipline(Console console, Client client) {
        super("print_field_ascending_discipline", "вывести значение поля discipline всех элементов " +
                "в порядке возрастания");
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

            var response = client.sendRequest(new PrintFieldAscendingDisciplineRequest());
            if (!response.getErrorMessage().isEmpty()) {
                console.println(response.getErrorMessage());
                return false;
            }

            if (response instanceof PrintFieldAscendingDisciplineResponse printResponse) {
                List<Discipline> disciplines = printResponse.getDisciplines();
                console.println(disciplines.isEmpty() ? "Список пуст" : "Результат:");
                disciplines.forEach(console::println);
                return true;
            }
            console.printError("Получен неверный ответ на запрос");
            return false;
        } catch (IllegalArgumentException e) {
            console.println(e.getMessage());
            console.println("использование: '" + getName() + "'");
            return false;
        } catch (ServerConnectionException e) {
            console.println(e.getMessage());
            return false;
        }
    }
}
