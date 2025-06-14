package labs.lab6.common.utility;

import java.util.Scanner;

/**
 * Консоль для ввода команд и вывода результата.
 */
public interface Console {
    void print(Object obj);
    void println(Object obj);
    String readln();
    boolean canReadln();
    void ask(String question);
    void printError(Object obj);
    void printTable(Object obj1, Object obj2);
    void prompt();
    String getPrompt();
    void selectFileScanner(Scanner scanner);
    void selectConsoleScanner();
}
