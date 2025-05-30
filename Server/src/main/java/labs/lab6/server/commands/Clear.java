package labs.lab6.server.commands;


import labs.lab6.common.network.requests.ClearRequest;
import labs.lab6.common.network.requests.Request;
import labs.lab6.common.network.responses.ClearResponse;
import labs.lab6.common.network.responses.Response;
import labs.lab6.server.managers.CollectionManager;

import java.util.Objects;

/**
 * Команда 'clear'. Очищает коллекцию.
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду при заданных аргументах.
     * @param request запрос на выполнение команды
     * @return Ответ с результатом выполнения команды
     */
    @Override
    public Response apply(Request request) {
        if (Objects.isNull(request) || !(request instanceof ClearRequest)) return new ClearResponse(
                "Неверный аргумент комманды"
        );

        collectionManager.clear();
        return new ClearResponse("");
    }

}