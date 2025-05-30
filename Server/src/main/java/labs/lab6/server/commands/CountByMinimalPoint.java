package labs.lab6.server.commands;


import labs.lab6.common.network.requests.CountByMinimalPointRequest;
import labs.lab6.common.network.requests.Request;
import labs.lab6.common.network.responses.CountByMinimalPointResponse;
import labs.lab6.common.network.responses.Response;
import labs.lab6.server.managers.CollectionManager;

import java.util.Objects;

/**
 * Команда 'count_by_minimal_point'. Выводит количество элементов, значение поля {@code minimalPoint} которых равно заданному.
 */
public class CountByMinimalPoint extends Command {
    private final CollectionManager collectionManager;

    public CountByMinimalPoint(CollectionManager collectionManager) {
        super("count_by_minimal_point minimalPoint", "вывести количество элементов, значение поля " +
                "minimalPoint которых равно заданному");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду при заданных аргументах.
     * @param request запрос на выполнение команды
     * @return Ответ с результатом выполнения команды
     */
    @Override
    public Response apply(Request request) {
        if (Objects.isNull(request) || !(request instanceof CountByMinimalPointRequest)) {
            return new CountByMinimalPointResponse(0, "Неверный аргумент комманды");
        }

        Double targetMinimalPoint = ((CountByMinimalPointRequest) request).getMinimalPoint();

        return new CountByMinimalPointResponse(
                collectionManager.countEqualMinimalPoint(targetMinimalPoint), ""
        );
    }
}
