package labs.lab6.server.commands;

import labs.lab6.common.models.LabWork;
import labs.lab6.common.network.requests.Request;
import labs.lab6.common.network.requests.ShowRequest;
import labs.lab6.common.network.responses.Response;
import labs.lab6.common.network.responses.ShowResponse;
import labs.lab6.server.managers.CollectionManager;

import java.util.Comparator;
import java.util.Objects;

/**
 * Команда 'show'. Выводит в стандартный поток вывода все элементы коллекции в строковом представлении.
 */
public class Show extends Command {
    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду при заданных аргументах.
     * @param request запрос на выполнение команды
     * @return Ответ с результатом выполнения команды
     */
    @Override
    public Response apply(Request request) {
        if (Objects.isNull(request) || !(request instanceof ShowRequest)) return new ShowResponse(
                null, "Неверный аргумент комманды"
        );
        var labs = collectionManager.getCollection().stream()
                .sorted(Comparator.comparing(LabWork::getName).thenComparing(LabWork::compareTo))
                .toList();
        return new ShowResponse(labs, "");
    }
}
