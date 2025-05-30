package labs.lab6.server.commands;


import labs.lab6.common.models.LabWork;
import labs.lab6.common.network.requests.Request;
import labs.lab6.common.network.requests.UpdateRequest;
import labs.lab6.common.network.responses.Response;
import labs.lab6.common.network.responses.UpdateResponse;
import labs.lab6.server.managers.CollectionManager;

import java.util.Objects;

/**
 * Команда 'update'. Обновляет значение элемента по {@code id}.
 */
public class Update extends Command {
    private final CollectionManager collectionManager;

    public Update(CollectionManager collectionManager) {
        super("update {id}", "обновить значение элемента по id");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду при заданных аргументах.
     * @param request запрос на выполнение команды
     * @return Ответ с результатом выполнения команды
     */
    @Override
    public Response apply(Request request) {
        if (Objects.isNull(request) || !(request instanceof UpdateRequest)) return new UpdateResponse(
                "Неверный аргумент комманды"
        );

        var labWork = ((UpdateRequest) request).getLabWork();

        if (Objects.isNull(labWork)) {
            return new UpdateResponse("Пустой объект");
        }

        if (!collectionManager.contains(labWork.getId())) {
            return new UpdateResponse("Элемента с id = " + labWork.getId() + " не найдено");
        }

        if (collectionManager.remove(labWork.getId()) && collectionManager.add(labWork)) {
            return new UpdateResponse("");
        }

        return new UpdateResponse("Ошибка при обновлении объекта");
    }
}

