package labs.lab6.server.commands;


import labs.lab6.common.network.requests.AddIfMaxRequest;
import labs.lab6.common.network.requests.Request;
import labs.lab6.common.network.responses.AddIfMaxResponse;
import labs.lab6.common.network.responses.Response;
import labs.lab6.server.managers.CollectionManager;

import java.util.Objects;

/**
 * Команда 'add_if_max'. Добавляет новый элемент в коллекцию, если его значение превышает значение
 * наибольшего элемента этой коллекции.
 */
public class AddIfMax extends Command {
    private final CollectionManager collectionManager;

    public AddIfMax(CollectionManager collectionManager) {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение " +
                "превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду при заданных аргументах.
     * @param request запрос на выполнение команды
     * @return Ответ с результатом выполнения команды
     */
    @Override
    public Response apply(Request request) {
        if (Objects.isNull(request) || !(request instanceof AddIfMaxRequest)) return new AddIfMaxResponse(
                -1L, false, "Неверный аргумент комманды"
        );
        var labWork = ((AddIfMaxRequest) request).getLabWork();
        if (!collectionManager.isMaxElement(labWork)) return new AddIfMaxResponse(-1L, false, "");

        labWork.setId(collectionManager.getFreeId());
        if (collectionManager.add(labWork)) {
            return new AddIfMaxResponse(labWork.getId(), true, "");
        }
        return new AddIfMaxResponse(-1L, false, "Ошибка при добавлении объекта");
    }
}
