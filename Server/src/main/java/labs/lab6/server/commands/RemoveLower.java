package labs.lab6.server.commands;


import labs.lab6.common.models.LabWork;
import labs.lab6.common.network.requests.RemoveLowerRequest;
import labs.lab6.common.network.requests.Request;
import labs.lab6.common.network.responses.RemoveLowerResponse;
import labs.lab6.common.network.responses.Response;
import labs.lab6.server.managers.CollectionManager;

import java.util.Objects;

/**
 * Команда 'remove_lower'. Удаляет из коллекции все элементы, меньшие, чем заданный.
 */
public class RemoveLower extends Command {
    private final CollectionManager collectionManager;

    public RemoveLower(CollectionManager collectionManager) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду при заданных аргументах.
     * @param request запрос на выполнение команды
     * @return Ответ с результатом выполнения команды
     */
    @Override
    public Response apply(Request request) {
        if (Objects.isNull(request) || !(request instanceof RemoveLowerRequest)) return new RemoveLowerResponse(
                0, "Неверный аргумент комманды"
        );

        LabWork targetLab = ((RemoveLowerRequest) request).getLabWork();
        if (Objects.isNull(targetLab)) {
            return new RemoveLowerResponse(0, "Пустой объект");
        }

        int sizeBefore = collectionManager.size();
        collectionManager.removeLower(targetLab);
        int sizeAfter = collectionManager.size();

        return new RemoveLowerResponse(sizeBefore - sizeAfter, "");
    }
}
