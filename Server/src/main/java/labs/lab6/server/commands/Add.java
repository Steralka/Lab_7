package labs.lab6.server.commands;


import labs.lab6.common.network.requests.AddRequest;
import labs.lab6.common.network.requests.Request;
import labs.lab6.common.network.responses.AddResponse;
import labs.lab6.common.network.responses.Response;
import labs.lab6.server.managers.CollectionManager;

import java.util.Objects;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 */
public class Add extends Command {

    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду при заданных аргументах.
     * @param request запрос на выполнение команды
     * @return Ответ с результатом выполнения команды
     */
    @Override
    public Response apply(Request request) {
        if (Objects.isNull(request) || !(request instanceof AddRequest)) return new AddResponse(
                -1L, "Неверный аргумент комманды"
        );
        var labWork = ((AddRequest) request).getLabWork();
        labWork.setId(collectionManager.getFreeId());
        if (collectionManager.add(labWork)) {
            return new AddResponse(labWork.getId(), "");
        }
        return new AddResponse(-1L, "Ошибка при добавлении объекта");
    }
}
