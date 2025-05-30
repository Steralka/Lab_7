package labs.lab6.server.utility;

import labs.lab6.common.exceptions.ServerConnectionException;
import labs.lab6.common.network.requests.Request;
import labs.lab6.common.utility.Console;
import labs.lab6.server.exceptions.OpenServerException;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int port;
    private final RequestHandler requestHandler;
    private final Console console;

    public Server(int port, RequestHandler requestHandler, Console console) {
        this.port = port;
        this.requestHandler = requestHandler;
        this.console = console;
    }

    public void run() throws OpenServerException, ServerConnectionException {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                handleClient(serverSocket);
            }
        } catch (IllegalArgumentException e) {
            throw new OpenServerException("Невалидный порт");
        } catch (IOException e) {
            console.printError("Ошибка подключения");
        }
    }

    private void handleClient(ServerSocket serverSocket) {
        while (!serverSocket.isClosed()) {
            try(Socket socket = serverSocket.accept()) {
                handleRequests(socket);
            } catch (IOException e) {
                console.printError("Ошибка подключения");
            }
        }
    }

    private void handleRequests(Socket socket) {
        while (!socket.isClosed()) {
            try {
                ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) reader.readObject();
                var response = requestHandler.handle(request);
                writer.writeObject(response);
                writer.flush();
            } catch (EOFException ignored) {}
            catch (IOException e) {
                break;
            } catch (ClassNotFoundException e) {
                console.printError("Ошибка при чтении запроса");
            }
        }
    }
}
