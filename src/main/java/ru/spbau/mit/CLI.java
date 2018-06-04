package ru.spbau.mit;

import org.apache.commons.cli.*;
import ru.spbau.mit.command.Command;
import ru.spbau.mit.exceptions.BadArgumentsException;
import ru.spbau.mit.exceptions.BadCommandException;
import ru.spbau.mit.exceptions.UnbalancedQuotesException;
import ru.spbau.mit.execute.Executor;
import ru.spbau.mit.execute.Scope;
import ru.spbau.mit.parse.*;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class that parses user input, maps it into commands and executes them.
 */
public class CLI {

    /**
     * The main workflow method of this utility
     */
    public static void main(String[] args) throws ParseException {

        Scanner scanner = new Scanner(System.in);
        CommandBuilder cb = new CommandBuilder();
        Executor ex = new Executor();
        Scope scope = new Scope();

        while (true) {
            String command = scanner.nextLine();
            try {
                List<Command> lc = cb.buildChainOfCommand(scope, command);
                String result = ex.exec(scope, lc);
                System.out.println(result);
            } catch (IOException | BadCommandException | UnbalancedQuotesException | BadArgumentsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
