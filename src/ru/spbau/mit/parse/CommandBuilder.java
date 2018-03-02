package ru.spbau.mit.parse;

import ru.spbau.mit.command.*;
import ru.spbau.mit.execute.Scope;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CommandBuilder is responsible for building commands ready to be executed from the given user input
 */
public class CommandBuilder {

    private Parser par = new Parser();
    private Lexer lex = new Lexer();

    /**
     * This method takes a raw user input and converts it into a set of executable commands depending on command
     * names. Pipes ("|") are used to separate one command's arguments from other's.
     *
     * This also handles special "assignment" command for binding variables.
     *
     * Commands lookup: each command corresponds to a single class that inherits from Command. The name of this class
     * should match the capitalized name of the command (i.e. the first argument in a sequence). If such class can not
     * be found, we try to run this command via external process invocation (see ExternalProcess class).
     * @param scope the current CLI scope
     * @param proc the raw user input string
     * @return the list of commands that are ready to be executed
     * @throws Exception
     */
    public List<Command> buildChainOfCommand(Scope scope, String proc)throws Exception
    {
        List<String> tokens = par.parse(proc);
        tokens = lex.expand(scope, tokens);

        List<Command> chainOfCommand = new ArrayList<>();

        List<String> currentArguments = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            String item = tokens.get(i);

            // Case for variable assignments
            if (i == 0 && item.contains("=")){

                List<String> args = Arrays.asList(item.split("="));
                chainOfCommand.add((Command)new Equals(args));
                currentArguments.clear();
                break;
            }

            if (!item.equals("|") && i != tokens.size()-1) {
                currentArguments.add(item);
                continue;
            }

            if (i == tokens.size() -1 ) {
                currentArguments.add(item);
            }

            chainOfCommand.add(makeCommand(currentArguments.get(0), currentArguments));
            currentArguments.clear();
        }

        return chainOfCommand;
    }


    private Command makeCommand(String name, List<String> arguments) throws Exception {

        String className = "ru.spbau.mit.command." + name.substring(0, 1).toUpperCase() + name.substring(1);
        Command cmd;
        try{
            Class  newClass = Class.forName(className);
            Constructor cons = newClass.getConstructor(List.class);
            arguments.remove(0);
            cmd = (Command)cons.newInstance(arguments);
        }catch (ClassNotFoundException exc){

            className = "ru.spbau.mit.command.ExternalProcess";
            Class  newClass = Class.forName(className);
            Constructor cons = newClass.getConstructor(List.class);
            cmd = (Command)cons.newInstance(arguments);
        }

        return cmd;
    }
}
