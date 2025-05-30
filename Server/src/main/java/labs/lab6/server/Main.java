package labs.lab6.server;

import labs.lab6.common.exceptions.ServerConnectionException;
import labs.lab6.common.managers.CSVParser;
import labs.lab6.common.utility.CommandType;
import labs.lab6.common.utility.Console;
import labs.lab6.common.utility.StandardConsole;
import labs.lab6.server.commands.*;
import labs.lab6.server.exceptions.OpenServerException;
import labs.lab6.server.managers.CollectionManager;
import labs.lab6.server.managers.CommandManager;
import labs.lab6.server.utility.RequestHandler;
import labs.lab6.server.utility.Server;

public class Main {

    private static int port = 8080;
    private static String fileName = "info";

    public static void main(String[] args) {
        if (args.length != 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {}
        }

        if (args.length > 1) {
                fileName = args[1];
        }

        Console console = new StandardConsole();
        var collectionManager = new CollectionManager(new CSVParser(fileName, console));
        collectionManager.loadCollection();

        var commandManager = new CommandManager();
        commandManager.register(CommandType.ADD.name(), new Add(collectionManager));
        commandManager.register(CommandType.ADD_IF_MAX.name(), new AddIfMax(collectionManager));
        commandManager.register(CommandType.CLEAR.name(), new Clear(collectionManager));
        commandManager.register(
                CommandType.COUNT_BY_MINIMAL_POINT.name(), new CountByMinimalPoint(collectionManager)
        );
        commandManager.register(CommandType.HELP.name(), new Help(commandManager));
        commandManager.register(CommandType.HISTORY.name(), new History(commandManager));
        commandManager.register(CommandType.INFO.name(), new Info(collectionManager));
        commandManager.register(
                CommandType.PRINT_FIELD_ASCENDING_DISCIPLINE.name(),
                new PrintFieldAscendingDiscipline(collectionManager)
        );
        commandManager.register(
                CommandType.PRINT_FIELD_DESCENDING_DIFFICULTY.name(),
                new PrintFieldDescendingDifficulty(collectionManager)
        );
        commandManager.register(CommandType.REMOVE_BY_ID.name(), new RemoveById(collectionManager));
        commandManager.register(CommandType.REMOVE_LOWER.name(), new RemoveLower(collectionManager));
        commandManager.register(CommandType.SHOW.name(), new Show(collectionManager));
        commandManager.register(CommandType.UPDATE.name(), new Update(collectionManager));

        Runtime.getRuntime().addShutdownHook(new Thread(collectionManager::saveCollection));

        var requestHandler = new RequestHandler(commandManager);
        try {
            var server = new Server(port, requestHandler, console);
            server.run();
        } catch (ServerConnectionException | OpenServerException e) {
            console.printError(e.getMessage());
        }
    }
}