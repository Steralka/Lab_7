package labs.lab6.server.utility;

import labs.lab6.common.network.requests.Request;
import labs.lab6.common.network.responses.ErrorResponse;
import labs.lab6.common.network.responses.Response;
import labs.lab6.server.managers.CommandManager;

public class RequestHandler {

    private final CommandManager commandManager;

    public RequestHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response handle(Request request) {
        var command = commandManager.getCommandByName(request.getName());
        if (command == null) return new ErrorResponse("Такой команды нет");
        commandManager.addToHistory(command.getName());
        return command.apply(request);
    }
}
