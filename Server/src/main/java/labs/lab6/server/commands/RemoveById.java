package labs.lab6.server.commands;


import labs.lab6.common.network.requests.RemoveByIdRequest;
import labs.lab6.common.network.requests.Request;
import labs.lab6.common.network.responses.RemoveByIdResponse;
import labs.lab6.common.network.responses.Response;
import labs.lab6.server.managers.CollectionManager;

import java.util.Objects;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции по {@code id}.
 */
public class RemoveById extends Command {
    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по id");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду при заданных аргументах.
     * @param request запрос на выполнение команды
     * @return Ответ с результатом выполнения команды
     */
    @Override
    public Response apply(Request request) {
        if (Objects.isNull(request) || !(request instanceof RemoveByIdRequest)) return new RemoveByIdResponse(
                "Неверный аргумент комманды"
        );

        long id = ((RemoveByIdRequest) request).getId();

        if (!collectionManager.contains(id)) {
            return new RemoveByIdResponse("Элемента с id = " + id + " не найдено");
        }

        if (collectionManager.remove(id)) return new RemoveByIdResponse("");

        return new RemoveByIdResponse("Ошибка при удалении объекта");
    }
}
